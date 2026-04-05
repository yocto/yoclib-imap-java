package com.yocto.yoclib.imap.tests.protocol;

import com.yocto.yoclib.imap.protocol.ProtocolAtom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProtocolAtomTest {

    @Test
    public void testGetValue(){
        assertEquals("a",new ProtocolAtom("a").getValue());
        assertEquals("ab",new ProtocolAtom("ab").getValue());
        assertEquals("abc",new ProtocolAtom("abc").getValue());
    }

    @Test
    public void testToProtocolString(){
        assertEquals("a",new ProtocolAtom("a").toProtocolString());
        assertEquals("ab",new ProtocolAtom("ab").toProtocolString());
        assertEquals("abc",new ProtocolAtom("abc").toProtocolString());
    }

}