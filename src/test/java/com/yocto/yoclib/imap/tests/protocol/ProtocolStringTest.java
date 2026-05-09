package com.yocto.yoclib.imap.tests.protocol;

import com.yocto.yoclib.imap.protocol.ProtocolString;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProtocolStringTest {

    @Test
    public void testToString(){
        assertEquals("ProtocolString{value='a'}",new ProtocolString("a") {
            @Override
            public String toProtocolString() {
                return null;
            }
        }.toString());
        assertEquals("ProtocolString{value='ab'}", new ProtocolString("ab") {
            @Override
            public String toProtocolString() {
                return null;
            }
        }.toString());
        assertEquals("ProtocolString{value='abc'}", new ProtocolString("abc") {
            @Override
            public String toProtocolString() {
                return null;
            }
        }.toString());
    }

}