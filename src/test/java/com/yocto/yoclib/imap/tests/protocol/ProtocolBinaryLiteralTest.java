package com.yocto.yoclib.imap.tests.protocol;

import com.yocto.yoclib.imap.protocol.ProtocolBinaryLiteral;
import com.yocto.yoclib.imap.protocol.ProtocolLiteral;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProtocolBinaryLiteralTest {

    @Test
    public void testGetValue(){
        assertArrayEquals("\u0000a\u0000".getBytes(),new ProtocolBinaryLiteral("\u0000a\u0000").getValue());
        assertArrayEquals("\u0000ab\u0000".getBytes(),new ProtocolBinaryLiteral("\u0000ab\u0000").getValue());
        assertArrayEquals("\u0000abc\u0000".getBytes(),new ProtocolBinaryLiteral("\u0000abc\u0000").getValue());
    }

    @Test
    public void testIsNonSynchronizing(){
        assertFalse(new ProtocolBinaryLiteral("\u0000a\u0000").isNonSynchronizing());
        assertFalse(new ProtocolBinaryLiteral("\u0000ab\u0000").isNonSynchronizing());
        assertFalse(new ProtocolBinaryLiteral("\u0000abc\u0000").isNonSynchronizing());
        assertTrue(new ProtocolBinaryLiteral("\u0000a\u0000",true).isNonSynchronizing());
        assertTrue(new ProtocolBinaryLiteral("\u0000ab\u0000",true).isNonSynchronizing());
        assertTrue(new ProtocolBinaryLiteral("\u0000abc\u0000",true).isNonSynchronizing());
    }

    @Test
    public void testToProtocolString(){
        assertEquals("~{3}\r\n\u0000a\u0000",new ProtocolBinaryLiteral("\u0000a\u0000").toProtocolString());
        assertEquals("~{4}\r\n\u0000ab\u0000",new ProtocolBinaryLiteral("\u0000ab\u0000").toProtocolString());
        assertEquals("~{5}\r\n\u0000abc\u0000",new ProtocolBinaryLiteral("\u0000abc\u0000").toProtocolString());
        assertEquals("~{3+}\r\n\u0000a\u0000",new ProtocolBinaryLiteral("\u0000a\u0000",true).toProtocolString());
        assertEquals("~{4+}\r\n\u0000ab\u0000",new ProtocolBinaryLiteral("\u0000ab\u0000",true).toProtocolString());
        assertEquals("~{5+}\r\n\u0000abc\u0000",new ProtocolBinaryLiteral("\u0000abc\u0000",true).toProtocolString());
    }

    @Test
    public void testToString(){
        assertEquals("ProtocolBinaryLiteral{value='AGEA', isNonSynchronizing=false}",new ProtocolBinaryLiteral("\u0000a\u0000").toString());
        assertEquals("ProtocolBinaryLiteral{value='AGFiAA==', isNonSynchronizing=false}",new ProtocolBinaryLiteral("\u0000ab\u0000").toString());
        assertEquals("ProtocolBinaryLiteral{value='AGFiYwA=', isNonSynchronizing=false}",new ProtocolBinaryLiteral("\u0000abc\u0000").toString());
    }

}