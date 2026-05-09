package com.yocto.yoclib.imap.tests.protocol;

import com.yocto.yoclib.imap.protocol.ProtocolAtom;
import com.yocto.yoclib.imap.protocol.ProtocolLiteral;
import com.yocto.yoclib.imap.protocol.ProtocolObject;
import com.yocto.yoclib.imap.protocol.ProtocolQuoted;
import com.yocto.yoclib.imap.protocol.ProtocolSubordinate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProtocolSubordinateTest {

    @Test
    public void testGetValue(){
        ProtocolAtom a = new ProtocolAtom("a");
        ProtocolQuoted ab = new ProtocolQuoted("ab");
        ProtocolLiteral abc = new ProtocolLiteral("abc");

        assertArrayEquals(new ProtocolObject[]{a},new ProtocolSubordinate(new ProtocolObject[]{a}).getObjects());
        assertArrayEquals(new ProtocolObject[]{a,ab},new ProtocolSubordinate(new ProtocolObject[]{a,ab}).getObjects());
        assertArrayEquals(new ProtocolObject[]{a,ab,abc},new ProtocolSubordinate(new ProtocolObject[]{a,ab,abc}).getObjects());
    }

    @Test
    public void testToProtocolString(){
        ProtocolAtom a = new ProtocolAtom("a");
        ProtocolQuoted ab = new ProtocolQuoted("ab");
        ProtocolLiteral abc = new ProtocolLiteral("abc");

        assertEquals("[a]",new ProtocolSubordinate(new ProtocolObject[]{a}).toProtocolString());
        assertEquals("[a \"ab\"]",new ProtocolSubordinate(new ProtocolObject[]{a,ab}).toProtocolString());
        assertEquals("[a \"ab\" {3}\r\nabc]",new ProtocolSubordinate(new ProtocolObject[]{a,ab,abc}).toProtocolString());
    }

    @Test
    public void testToString(){
        ProtocolAtom a = new ProtocolAtom("a");
        ProtocolQuoted ab = new ProtocolQuoted("ab");
        ProtocolLiteral abc = new ProtocolLiteral("abc");

        assertEquals("ProtocolSubordinate{objects=[ProtocolAtom{value='a'}]}",new ProtocolSubordinate(new ProtocolObject[]{a}).toString());
        assertEquals("ProtocolSubordinate{objects=[ProtocolAtom{value='a'}, ProtocolQuoted{value='ab'}]}",new ProtocolSubordinate(new ProtocolObject[]{a,ab}).toString());
        assertEquals("ProtocolSubordinate{objects=[ProtocolAtom{value='a'}, ProtocolQuoted{value='ab'}, ProtocolLiteral{value='abc'}]}",new ProtocolSubordinate(new ProtocolObject[]{a,ab,abc}).toString());
    }

}