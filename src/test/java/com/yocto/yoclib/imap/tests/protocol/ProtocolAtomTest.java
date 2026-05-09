package com.yocto.yoclib.imap.tests.protocol;

import com.yocto.yoclib.imap.protocol.ProtocolAtom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProtocolAtomTest {

    @Test
    public void testEquals(){
        assertEquals(new ProtocolAtom("a"),new ProtocolAtom("a"));
        assertEquals(new ProtocolAtom("ab"),new ProtocolAtom("ab"));
        assertEquals(new ProtocolAtom("abc"),new ProtocolAtom("abc"));

        Object unknown = "ab";
        assertNotEquals(new ProtocolAtom("ab"),unknown);
        assertNotEquals(unknown,new ProtocolAtom("ab"));
    }

    @Test
    public void testGetValue(){
        assertEquals("a",new ProtocolAtom("a").getValue());
        assertEquals("ab",new ProtocolAtom("ab").getValue());
        assertEquals("abc",new ProtocolAtom("abc").getValue());
    }

    @Test
    public void testIsNIL(){
        assertFalse(new ProtocolAtom("a").isNIL());
        assertFalse(new ProtocolAtom("ab").isNIL());
        assertFalse(new ProtocolAtom("abc").isNIL());
        assertTrue(new ProtocolAtom("NIL").isNIL());
        assertTrue(new ProtocolAtom("nil").isNIL());
        assertTrue(new ProtocolAtom("Nil").isNIL());
        assertTrue(new ProtocolAtom("niL").isNIL());
    }

    @Test
    public void testToProtocolString(){
        assertEquals("a",new ProtocolAtom("a").toProtocolString());
        assertEquals("ab",new ProtocolAtom("ab").toProtocolString());
        assertEquals("abc",new ProtocolAtom("abc").toProtocolString());
    }

    @Test
    public void testToString(){
        assertEquals("ProtocolAtom{value='a'}",new ProtocolAtom("a").toString());
        assertEquals("ProtocolAtom{value='ab'}",new ProtocolAtom("ab").toString());
        assertEquals("ProtocolAtom{value='abc'}",new ProtocolAtom("abc").toString());
    }

}