package com.yocto.yoclib.imap.tests.protocol;

import com.yocto.yoclib.imap.protocol.ProtocolObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProtocolObjectTest {

    @Test
    public void testToString(){
        assertEquals("ProtocolObject{}", new ProtocolObject() {
            @Override
            public String toProtocolString() {
                return null;
            }
        }.toString());
    }

}