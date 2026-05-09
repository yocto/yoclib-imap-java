package com.yocto.yoclib.imap.tests.protocol;

import com.yocto.yoclib.imap.protocol.ProtocolQuoted;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProtocolQuotedTest {

    @Test
    public void testEquals(){
        assertEquals(new ProtocolQuoted("a"),new ProtocolQuoted("a"));
        assertEquals(new ProtocolQuoted("ab"),new ProtocolQuoted("ab"));
        assertEquals(new ProtocolQuoted("abc"),new ProtocolQuoted("abc"));

        assertNotEquals(new ProtocolQuoted("a"),null);
        assertNotEquals(null,new ProtocolQuoted("a"));

        assertNotEquals(new ProtocolQuoted("ab"),"ab");
        assertNotEquals("ab",new ProtocolQuoted("ab"));
    }

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

    @Test
    public void testToString(){
        assertEquals("ProtocolQuoted{value='a'}",new ProtocolQuoted("a").toString());
        assertEquals("ProtocolQuoted{value='ab'}",new ProtocolQuoted("ab").toString());
        assertEquals("ProtocolQuoted{value='abc'}",new ProtocolQuoted("abc").toString());
    }

}