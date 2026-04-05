package com.yocto.yoclib.imap.tests.protocol;

import com.yocto.yoclib.imap.protocol.IMAPProtocolLiteral;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IMAPProtocolLiteralTest {

    @Test
    public void testGetValue(){
        assertEquals("a",new IMAPProtocolLiteral("a").getValue());
        assertEquals("ab",new IMAPProtocolLiteral("ab").getValue());
        assertEquals("abc",new IMAPProtocolLiteral("abc").getValue());
    }

    @Test
    public void testToProtocolString(){
        assertEquals("{1}\r\na",new IMAPProtocolLiteral("a").toProtocolString());
        assertEquals("{2}\r\nab",new IMAPProtocolLiteral("ab").toProtocolString());
        assertEquals("{3}\r\nabc",new IMAPProtocolLiteral("abc").toProtocolString());
    }

}