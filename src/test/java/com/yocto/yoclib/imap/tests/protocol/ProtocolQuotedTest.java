package com.yocto.yoclib.imap.tests.protocol;

import com.yocto.yoclib.imap.protocol.ProtocolQuoted;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProtocolQuotedTest {

    @Test
    public void testGetValue(){
        assertEquals("a",new ProtocolQuoted("a").getValue());
        assertEquals("ab",new ProtocolQuoted("ab").getValue());
        assertEquals("abc",new ProtocolQuoted("abc").getValue());
    }

    @Test
    public void testToProtocolString(){
        assertEquals("\"a\"",new ProtocolQuoted("a").toProtocolString());
        assertEquals("\"ab\"",new ProtocolQuoted("ab").toProtocolString());
        assertEquals("\"abc\"",new ProtocolQuoted("abc").toProtocolString());
    }

}