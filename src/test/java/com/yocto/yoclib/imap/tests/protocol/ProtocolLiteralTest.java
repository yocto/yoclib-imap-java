package com.yocto.yoclib.imap.tests.protocol;

import com.yocto.yoclib.imap.protocol.ProtocolLiteral;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProtocolLiteralTest {

    @Test
    public void testGetValue(){
        assertEquals("a",new ProtocolLiteral("a").getValue());
        assertEquals("ab",new ProtocolLiteral("ab").getValue());
        assertEquals("abc",new ProtocolLiteral("abc").getValue());
    }

    @Test
    public void testIsNonSynchronizing(){
        assertFalse(new ProtocolLiteral("a").isNonSynchronizing());
        assertFalse(new ProtocolLiteral("ab").isNonSynchronizing());
        assertFalse(new ProtocolLiteral("abc").isNonSynchronizing());
        assertTrue(new ProtocolLiteral("a",true).isNonSynchronizing());
        assertTrue(new ProtocolLiteral("ab",true).isNonSynchronizing());
        assertTrue(new ProtocolLiteral("abc",true).isNonSynchronizing());
    }

    @Test
    public void testToProtocolString(){
        assertEquals("{1}\r\na",new ProtocolLiteral("a").toProtocolString());
        assertEquals("{2}\r\nab",new ProtocolLiteral("ab").toProtocolString());
        assertEquals("{3}\r\nabc",new ProtocolLiteral("abc").toProtocolString());
        assertEquals("{1+}\r\na",new ProtocolLiteral("a",true).toProtocolString());
        assertEquals("{2+}\r\nab",new ProtocolLiteral("ab",true).toProtocolString());
        assertEquals("{3+}\r\nabc",new ProtocolLiteral("abc",true).toProtocolString());
    }

    @Test
    public void testToString(){
        assertEquals("ProtocolLiteral{value='a'}",new ProtocolLiteral("a").toString());
        assertEquals("ProtocolLiteral{value='ab'}",new ProtocolLiteral("ab").toString());
        assertEquals("ProtocolLiteral{value='abc'}",new ProtocolLiteral("abc").toString());
    }

}