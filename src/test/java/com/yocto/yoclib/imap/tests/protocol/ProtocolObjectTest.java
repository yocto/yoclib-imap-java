package com.yocto.yoclib.imap.tests.protocol;

import com.yocto.yoclib.imap.protocol.ProtocolAtom;
import com.yocto.yoclib.imap.protocol.ProtocolList;
import com.yocto.yoclib.imap.protocol.ProtocolObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProtocolObjectTest {

    @Test
    public void testAsList(){
        ProtocolAtom atom = new ProtocolAtom("abc");

        assertEquals(new ProtocolList(new ProtocolObject[]{atom}),atom.asList(false));
        assertEquals(new ProtocolList(new ProtocolObject[]{atom}),atom.asList(true));

        ProtocolList list = new ProtocolList(new ProtocolObject[]{atom});

        assertEquals(list,list.asList(false));
        assertEquals(new ProtocolList(new ProtocolObject[]{list}),list.asList(true));
    }

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