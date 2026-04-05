package com.yocto.yoclib.imap.tests.protocol;

import com.yocto.yoclib.imap.protocol.IMAPProtocolQuoted;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IMAPProtocolQuotedTest{

    @Test
    public void testGetValue(){
        assertEquals("a",new IMAPProtocolQuoted("a").getValue());
        assertEquals("ab",new IMAPProtocolQuoted("ab").getValue());
        assertEquals("abc",new IMAPProtocolQuoted("abc").getValue());
    }

    @Test
    public void testToProtocolString(){
        assertEquals("\"a\"",new IMAPProtocolQuoted("a").toProtocolString());
        assertEquals("\"ab\"",new IMAPProtocolQuoted("ab").toProtocolString());
        assertEquals("\"abc\"",new IMAPProtocolQuoted("abc").toProtocolString());
    }

}