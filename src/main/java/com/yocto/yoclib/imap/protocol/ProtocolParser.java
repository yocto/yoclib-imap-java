package com.yocto.yoclib.imap.protocol;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ProtocolParser {

    public static ProtocolObject[] parse(String input) {
        if (input == null || input.trim().isEmpty()) {
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

        char c = t.peekCharNoSkip();

        // Binary literal: ~{n} or ~{n+}
        if (c == '~') {
            t.consumeNoSkip('~');
            return parseBinaryLiteral(t);
        }

        // Regular literal: {n} or {n+}
        if (c == '{') {
            return parseRegularLiteral(t);
        }

        if (c == '(') return parseList(t);
        if (c == '[') return parseSubordinate(t);
        if (c == '"') return parseQuoted(t);

        // Atom
        String atomValue = t.readAtom();
        ProtocolAtom atom = new ProtocolAtom(atomValue);

        if (t.hasMore() && (t.isNextCharImmediate('[') || t.isNextCharImmediate('<'))) {
            return parseSectionPartial(atom, t);
        }

        return atom;
    }

    // ====================== Split Literal Parsers ======================

    /**
     * Parses regular literals: {n} or {n+}
     */
    private static ProtocolLiteral parseRegularLiteral(Tokenizer t) {
        LiteralHeader header = parseLiteralHeader(t, false);
        t.skipCRLF();

        String data = t.readExactly(header.size);
        return new ProtocolLiteral(data, header.nonSynchronizing);
    }

    /**
     * Parses binary literals: ~{n} or ~{n+}
     */
    private static ProtocolBinaryLiteral parseBinaryLiteral(Tokenizer t) {
        LiteralHeader header = parseLiteralHeader(t, true);
        t.skipCRLF();

        String rawData = t.readExactly(header.size);
        byte[] binaryData = rawData.getBytes(StandardCharsets.ISO_8859_1);

        return new ProtocolBinaryLiteral(binaryData, header.nonSynchronizing);
    }

    /**
     * Common helper: parses the header part {size} or {size+}
     * (tilde ~ is already consumed for binary case)
     */
    private static LiteralHeader parseLiteralHeader(Tokenizer t, boolean isBinary) {
        t.consumeNoSkip('{');

        StringBuilder sizeStr = new StringBuilder();
        boolean nonSynchronizing = false;

        while (t.hasMore()) {
            char c = t.peekCharNoSkip();
            if (Character.isDigit(c)) {
                sizeStr.append(t.nextCharNoSkip());
            } else if (c == '+') {
                nonSynchronizing = true;
                t.consumeNoSkip('+');
            } else if (c == '}') {
                break;
            } else {
                break; // malformed
            }
        }

        t.consumeNoSkip('}');

        int size = 0;
        try {
            if (sizeStr.length() > 0) {
                size = Integer.parseInt(sizeStr.toString());
            }
        } catch (Exception ignored) {}

        return new LiteralHeader(size, nonSynchronizing);
    }

    // ====================== Other Parsers (unchanged) ======================

    private static ProtocolList parseList(Tokenizer t) {
        t.consume('(');
        List<ProtocolObject> elements = new ArrayList<>();
        while (t.hasMore()) {
            t.skipWhitespace();
            if (t.peekChar() == ')') {
                t.consume(')');
                break;
            }
            elements.add(parseToken(t));
        }
        return new ProtocolList(elements.toArray(new ProtocolObject[0]));
    }

    private static ProtocolSubordinate parseSubordinate(Tokenizer t) {
        t.consume('[');
        List<ProtocolObject> elements = new ArrayList<>();
        while (t.hasMore()) {
            t.skipWhitespace();
            if (t.peekChar() == ']') {
                t.consume(']');
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

        if (t.isNextCharImmediate('[')) {
            section = parseSubordinate(t);
        }

        if (t.hasMore() && t.isNextCharImmediate('<')) {
            PartialData pd = parsePartialData(t);
            offset = pd.offset;
            length = pd.length;
        }

        return new ProtocolSectionPartial(baseAtom, section, offset, length);
    }

    private static PartialData parsePartialData(Tokenizer t) {
        t.consumeNoSkip('<');
        StringBuilder sb = new StringBuilder();

        while (t.hasMore()) {
            char c = t.nextCharNoSkip();
            if (c == '>') {
                t.consumeNoSkip('>');
                break;
            }
            sb.append(c);
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
        t.consumeNoSkip('"');

        StringBuilder sb = new StringBuilder();
        boolean escaped = false;

        while (t.pos < t.input.length()) {
            char c = t.input.charAt(t.pos++);
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

    // ====================== Tokenizer (unchanged) ======================

    private static class Tokenizer {
        final String input;
        int pos = 0;

        Tokenizer(String input) {
            this.input = input;
        }

        public boolean hasMore() {
            skipWhitespace();
            return pos < input.length();
        }

        public void skipWhitespace() {
            while (pos < input.length() && Character.isWhitespace(input.charAt(pos))) {
                pos++;
            }
        }

        public void skipCRLF() {
            if (pos < input.length() && input.charAt(pos) == '\r') pos++;
            if (pos < input.length() && input.charAt(pos) == '\n') pos++;
        }

        public char peekChar() {
            skipWhitespace();
            return pos < input.length() ? input.charAt(pos) : '\0';
        }

        public char peekCharNoSkip() {
            return pos < input.length() ? input.charAt(pos) : '\0';
        }

        public boolean isNextCharImmediate(char expected) {
            return pos < input.length() && input.charAt(pos) == expected;
        }

        public String readAtom() {
            skipWhitespace();
            int start = pos;
            while (pos < input.length()) {
                char c = input.charAt(pos);
                if (Character.isWhitespace(c) || c == '(' || c == ')' || c == '[' || c == ']' ||
                        c == '"' || c == '{' || c == '}' || c == '<' || c == '>') {
                    break;
                }
                pos++;
            }
            return input.substring(start, pos);
        }

        public String readExactly(int count) {
            if (count <= 0) return "";
            int toRead = Math.min(count, input.length() - pos);
            String data = input.substring(pos, pos + toRead);
            pos += toRead;
            return data;
        }

        public void consume(char expected) {
            skipWhitespace();
            if (pos < input.length() && input.charAt(pos) == expected) pos++;
        }

        public void consumeNoSkip(char expected) {
            if (pos < input.length() && input.charAt(pos) == expected) pos++;
        }

        public char nextCharNoSkip() {
            return pos < input.length() ? input.charAt(pos++) : '\0';
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