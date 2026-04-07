package com.yocto.yoclib.imap.tests.protocol;

import com.yocto.yoclib.imap.protocol.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProtocolListTest {

    @Test
    public void testGetValue(){
        ProtocolAtom a = new ProtocolAtom("a");
        ProtocolQuoted ab = new ProtocolQuoted("ab");
        ProtocolLiteral abc = new ProtocolLiteral("abc");

        assertArrayEquals(new ProtocolObject[]{a},new ProtocolList(new ProtocolObject[]{a}).getValue());
        assertArrayEquals(new ProtocolObject[]{a,ab},new ProtocolList(new ProtocolObject[]{a,ab}).getValue());
        assertArrayEquals(new ProtocolObject[]{a,ab,abc},new ProtocolList(new ProtocolObject[]{a,ab,abc}).getValue());
    }

    @Test
    public void testToProtocolString(){
        ProtocolAtom a = new ProtocolAtom("a");
        ProtocolQuoted ab = new ProtocolQuoted("ab");
        ProtocolLiteral abc = new ProtocolLiteral("abc");

        assertEquals("(a)",new ProtocolList(new ProtocolObject[]{a}).toProtocolString());
        assertEquals("(a \"ab\")",new ProtocolList(new ProtocolObject[]{a,ab}).toProtocolString());
        assertEquals("(a \"ab\" {3}\r\nabc)",new ProtocolList(new ProtocolObject[]{a,ab,abc}).toProtocolString());
    }

}