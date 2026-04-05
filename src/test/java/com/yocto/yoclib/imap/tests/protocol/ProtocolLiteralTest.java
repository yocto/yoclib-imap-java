package com.yocto.yoclib.imap.tests.protocol;

import com.yocto.yoclib.imap.protocol.ProtocolLiteral;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProtocolLiteralTest {

    @Test
    public void testGetValue(){
        assertEquals("a",new ProtocolLiteral("a").getValue());
        assertEquals("ab",new ProtocolLiteral("ab").getValue());
        assertEquals("abc",new ProtocolLiteral("abc").getValue());
    }

    @Test
    public void testToProtocolString(){
        assertEquals("{1}\r\na",new ProtocolLiteral("a").toProtocolString());
        assertEquals("{2}\r\nab",new ProtocolLiteral("ab").toProtocolString());
        assertEquals("{3}\r\nabc",new ProtocolLiteral("abc").toProtocolString());
    }

}