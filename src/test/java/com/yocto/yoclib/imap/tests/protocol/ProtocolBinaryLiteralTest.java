package com.yocto.yoclib.imap.tests.protocol;

import com.yocto.yoclib.imap.protocol.ProtocolBinaryLiteral;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProtocolBinaryLiteralTest {

    @Test
    public void testEquals(){
        assertEquals(new ProtocolBinaryLiteral("\u0000a\u0000"),new ProtocolBinaryLiteral("\u0000a\u0000"));
        assertEquals(new ProtocolBinaryLiteral("\u0000ab\u0000"),new ProtocolBinaryLiteral("\u0000ab\u0000"));
        assertEquals(new ProtocolBinaryLiteral("\u0000abc\u0000"),new ProtocolBinaryLiteral("\u0000abc\u0000"));
        assertEquals(new ProtocolBinaryLiteral("\u0000a\u0000",true),new ProtocolBinaryLiteral("\u0000a\u0000",true));
        assertEquals(new ProtocolBinaryLiteral("\u0000ab\u0000",true),new ProtocolBinaryLiteral("\u0000ab\u0000",true));
        assertEquals(new ProtocolBinaryLiteral("\u0000abc\u0000",true),new ProtocolBinaryLiteral("\u0000abc\u0000",true));

        assertNotEquals(new ProtocolBinaryLiteral("\u0000a\u0000"),null);
        assertNotEquals(null,new ProtocolBinaryLiteral("\u0000a\u0000"));

        assertNotEquals(new ProtocolBinaryLiteral("\u0000ab\u0000"),"\u0000ab\u0000");
        assertNotEquals("\u0000ab\u0000",new ProtocolBinaryLiteral("\u0000ab\u0000"));

        assertNotEquals(new ProtocolBinaryLiteral("\u0000def\u0000"),new ProtocolBinaryLiteral("\u0000abc\u0000"));
        assertNotEquals(new ProtocolBinaryLiteral("\u0000abc\u0000",true),new ProtocolBinaryLiteral("\u0000abc\u0000"));
    }

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