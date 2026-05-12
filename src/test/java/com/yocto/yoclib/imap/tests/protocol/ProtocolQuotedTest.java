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
        assertEquals(new ProtocolQuoted("ab cd"),new ProtocolQuoted("ab cd"));

        Object unknown = "ab";
        assertNotEquals(new ProtocolQuoted("ab"),unknown);
        assertNotEquals(unknown,new ProtocolQuoted("ab"));
    }

    @Test
    public void testGetValue(){
        assertEquals("a",new ProtocolQuoted("a").getValue());
        assertEquals("ab",new ProtocolQuoted("ab").getValue());
        assertEquals("abc",new ProtocolQuoted("abc").getValue());
        assertEquals("ab cd",new ProtocolQuoted("ab cd").getValue());
    }

    @Test
    public void testToProtocolString(){
        assertEquals("\"a\"",new ProtocolQuoted("a").toProtocolString());
        assertEquals("\"ab\"",new ProtocolQuoted("ab").toProtocolString());
        assertEquals("\"abc\"",new ProtocolQuoted("abc").toProtocolString());
        assertEquals("\"ab cd\"",new ProtocolQuoted("ab cd").toProtocolString());
    }

    @Test
    public void testToString(){
        assertEquals("ProtocolQuoted{value='a'}",new ProtocolQuoted("a").toString());
        assertEquals("ProtocolQuoted{value='ab'}",new ProtocolQuoted("ab").toString());
        assertEquals("ProtocolQuoted{value='abc'}",new ProtocolQuoted("abc").toString());
        assertEquals("ProtocolQuoted{value='ab cd'}",new ProtocolQuoted("ab cd").toString());
    }

}