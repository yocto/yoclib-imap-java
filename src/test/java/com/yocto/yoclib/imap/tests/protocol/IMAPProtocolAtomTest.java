package com.yocto.yoclib.imap.tests.protocol;

import com.yocto.yoclib.imap.protocol.IMAPProtocolAtom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IMAPProtocolAtomTest {

    @Test
    public void testGetValue(){
        assertEquals("a",new IMAPProtocolAtom("a").getValue());
        assertEquals("ab",new IMAPProtocolAtom("ab").getValue());
        assertEquals("abc",new IMAPProtocolAtom("abc").getValue());
    }

    @Test
    public void testToProtocolString(){
        assertEquals("a",new IMAPProtocolAtom("a").toProtocolString());
        assertEquals("ab",new IMAPProtocolAtom("ab").toProtocolString());
        assertEquals("abc",new IMAPProtocolAtom("abc").toProtocolString());
    }

}