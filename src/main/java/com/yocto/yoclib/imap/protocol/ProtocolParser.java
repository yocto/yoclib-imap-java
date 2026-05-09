package com.yocto.yoclib.imap.protocol;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class ProtocolParser {

    public static ProtocolObject[] parse(byte[] input) {
        if (input == null || input.length == 0) {
            return new ProtocolObject[0];
        }

        List<ProtocolObject> result = new ArrayList<>();
        Tokenizer t = new Tokenizer(input);

        while (t.hasMore()) {
            ProtocolObject token = parseToken(t);
            if (token != null) {
                result.add(token);
            }
        }
        return result.toArray(new ProtocolObject[0]);
    }

    private static ProtocolObject parseToken(Tokenizer t) {
        t.skipWhitespace();
        if (!t.hasMore()) return null;

        byte b = t.peekByteNoSkip();

        // Binary literal: ~{n} or ~{n+}
        if (b == '~' && t.isNextByteAfter((byte) '~', (byte) '{')) {
            t.consumeNoSkip((byte) '~');
            return parseBinaryLiteral(t);
        }

        // Regular literal: {n} or {n+}
        if (b == '{') {
            return parseRegularLiteral(t);
        }

        if (b == '(') return parseList(t);
        if (b == '[') return parseSubordinate(t);
        if (b == '"') return parseQuoted(t);

        // Atom
        String atomValue = t.readAtom();
        ProtocolAtom atom = new ProtocolAtom(atomValue);

        if (t.hasMore() &&
                (t.isNextByteImmediate((byte) '[') || t.isNextByteImmediate((byte) '<'))) {
            return parseSectionPartial(atom, t);
        }

        return atom;
    }

    // ====================== Literal Parsers ======================

    private static ProtocolLiteral parseRegularLiteral(Tokenizer t) {
        LiteralHeader header = parseLiteralHeader(t);
        t.skipCRLF();
        byte[] data = t.readExactly(header.size);
        return new ProtocolLiteral(new String(data, StandardCharsets.ISO_8859_1), header.nonSynchronizing);
    }

    private static ProtocolBinaryLiteral parseBinaryLiteral(Tokenizer t) {
        LiteralHeader header = parseLiteralHeader(t);
        t.skipCRLF();
        byte[] data = t.readExactly(header.size);
        return new ProtocolBinaryLiteral(data, header.nonSynchronizing);
    }

    private static LiteralHeader parseLiteralHeader(Tokenizer t) {
        t.consumeNoSkip((byte) '{');

        StringBuilder sizeStr = new StringBuilder();
        boolean nonSynchronizing = false;

        while (t.hasMore()) {
            byte b = t.peekByteNoSkip();
            if (b >= '0' && b <= '9') {
                sizeStr.append((char) t.nextByteNoSkip());
            } else if (b == '+') {
                nonSynchronizing = true;
                t.consumeNoSkip((byte) '+');
            } else if (b == '}') {
                break;
            } else {
                break;
            }
        }

        t.consumeNoSkip((byte) '}');

        int size = 0;
        try {
            if (sizeStr.length() > 0) {
                size = Integer.parseInt(sizeStr.toString());
            }
        } catch (Exception ignored) {}

        return new LiteralHeader(size, nonSynchronizing);
    }

    // ====================== Other Parsers ======================

    private static ProtocolList parseList(Tokenizer t) {
        t.consume((byte) '(');
        List<ProtocolObject> elements = new ArrayList<>();
        while (t.hasMore()) {
            t.skipWhitespace();
            if (t.peekByte() == ')') {
                t.consume((byte) ')');
                break;
            }
            elements.add(parseToken(t));
        }
        return new ProtocolList(elements.toArray(new ProtocolObject[0]));
    }

    private static ProtocolSubordinate parseSubordinate(Tokenizer t) {
        t.consume((byte) '[');
        List<ProtocolObject> elements = new ArrayList<>();
        while (t.hasMore()) {
            t.skipWhitespace();
            if (t.peekByte() == ']') {
                t.consume((byte) ']');
                break;
            }
            elements.add(parseToken(t));
        }
        return new ProtocolSubordinate(elements.toArray(new ProtocolObject[0]));
    }

    private static ProtocolSectionPartial parseSectionPartial(ProtocolAtom baseAtom, Tokenizer t) {
        ProtocolSubordinate section = null;
        Integer offset = null;
        Integer length = null;

        if (t.isNextByteImmediate((byte) '[')) {
            section = parseSubordinate(t);
        }

        if (t.hasMore() && t.isNextByteImmediate((byte) '<')) {
            PartialData pd = parsePartialData(t);
            offset = pd.offset;
            length = pd.length;
        }

        return new ProtocolSectionPartial(baseAtom, section, offset, length);
    }

    private static PartialData parsePartialData(Tokenizer t) {
        t.consumeNoSkip((byte) '<');
        StringBuilder sb = new StringBuilder();

        while (t.hasMore()) {
            byte b = t.nextByteNoSkip();
            if (b == '>') {
                t.consumeNoSkip((byte) '>');
                break;
            }
            sb.append((char) b);
        }

        String content = sb.toString().trim();
        Integer offset = null;
        Integer length = null;

        if (!content.isEmpty()) {
            try {
                if (content.contains(".")) {
                    String[] parts = content.split("\\.");
                    offset = Integer.parseInt(parts[0].trim());
                    if (parts.length > 1) length = Integer.parseInt(parts[1].trim());
                } else {
                    offset = Integer.parseInt(content);
                }
            } catch (Exception ignored) {
                offset = 0;
            }
        }
        if (offset == null) offset = 0;

        return new PartialData(offset, length);
    }

    private static ProtocolQuoted parseQuoted(Tokenizer t) {
        t.consumeNoSkip((byte) '"');

        StringBuilder sb = new StringBuilder();
        boolean escaped = false;

        while (t.hasMore()) {
            byte b = t.nextByteNoSkip();
            char c = (char) b;

            if (escaped) {
                sb.append(c);
                escaped = false;
            } else if (c == '\\') {
                escaped = true;
            } else if (c == '"') {
                break;
            } else {
                sb.append(c);
            }
        }
        return new ProtocolQuoted(sb.toString());
    }

    // ====================== Tokenizer ======================

    private static class Tokenizer {
        final byte[] input;
        int pos = 0;

        Tokenizer(byte[] input) {
            this.input = input;
        }

        public boolean hasMore() {
            skipWhitespace();
            return pos < input.length;
        }

        public void skipWhitespace() {
            while (pos < input.length && Character.isWhitespace(input[pos] & 0xFF)) {
                pos++;
            }
        }

        public void skipCRLF() {
            if (pos < input.length && input[pos] == '\r') pos++;
            if (pos < input.length && input[pos] == '\n') pos++;
        }

        public byte peekByte() {
            skipWhitespace();
            return pos < input.length ? input[pos] : 0;
        }

        public byte peekByteNoSkip() {
            return pos < input.length ? input[pos] : 0;
        }

        public boolean isNextByteImmediate(byte expected) {
            return pos < input.length && input[pos] == expected;
        }

        public boolean isNextByteAfter(byte current, byte expected) {
            if (pos >= input.length || input[pos] != current) {
                return false;
            }
            return pos + 1 < input.length && input[pos + 1] == expected;
        }

        public String readAtom() {
            skipWhitespace();
            int start = pos;
            while (pos < input.length) {
                byte b = input[pos];
                if (Character.isWhitespace(b & 0xFF) || b == '(' || b == ')' || b == '[' || b == ']' ||
                        b == '"' || b == '{' || b == '}' || b == '<' || b == '>') {
                    break;
                }
                pos++;
            }
            return new String(input, start, pos - start, StandardCharsets.US_ASCII);
        }

        public byte[] readExactly(int count) {
            if (count <= 0) return new byte[0];
            int toRead = Math.min(count, input.length - pos);
            byte[] data = new byte[toRead];
            System.arraycopy(input, pos, data, 0, toRead);
            pos += toRead;
            return data;
        }

        public void consume(byte expected) {
            skipWhitespace();
            if (pos < input.length && input[pos] == expected) pos++;
        }

        public void consumeNoSkip(byte expected) {
            if (pos < input.length && input[pos] == expected) pos++;
        }

        public byte nextByteNoSkip() {
            return pos < input.length ? input[pos++] : 0;
        }
    }

    // ====================== Helper Classes ======================

    private static class LiteralHeader {
        final int size;
        final boolean nonSynchronizing;

        LiteralHeader(int size, boolean nonSynchronizing) {
            this.size = size;
            this.nonSynchronizing = nonSynchronizing;
        }
    }

    private static class PartialData {
        final Integer offset;
        final Integer length;

        PartialData(Integer offset, Integer length) {
            this.offset = offset != null ? offset : 0;
            this.length = length;
        }
    }
}