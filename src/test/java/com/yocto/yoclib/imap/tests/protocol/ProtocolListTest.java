package com.yocto.yoclib.imap.tests.protocol;

import com.yocto.yoclib.imap.protocol.ProtocolAtom;
import com.yocto.yoclib.imap.protocol.ProtocolList;
import com.yocto.yoclib.imap.protocol.ProtocolLiteral;
import com.yocto.yoclib.imap.protocol.ProtocolObject;
import com.yocto.yoclib.imap.protocol.ProtocolQuoted;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProtocolListTest {

    @Test
    public void testEquals(){
        ProtocolAtom a = new ProtocolAtom("a");
        ProtocolQuoted ab = new ProtocolQuoted("ab");
        ProtocolLiteral abc = new ProtocolLiteral("abc");

        assertEquals(new ProtocolList(new ProtocolObject[]{a}),new ProtocolList(new ProtocolObject[]{a}));
        assertEquals(new ProtocolList(new ProtocolObject[]{a,ab}),new ProtocolList(new ProtocolObject[]{a,ab}));
        assertEquals(new ProtocolList(new ProtocolObject[]{a,ab,abc}),new ProtocolList(new ProtocolObject[]{a,ab,abc}));

        Object unknown = new ProtocolObject[]{a,ab};
        assertNotEquals(new ProtocolList(new ProtocolObject[]{a,ab}),unknown);
        assertNotEquals(unknown,new ProtocolList(new ProtocolObject[]{a,ab}));
    }

    @Test
    public void testGetValue(){
        ProtocolAtom a = new ProtocolAtom("a");
        ProtocolQuoted ab = new ProtocolQuoted("ab");
        ProtocolLiteral abc = new ProtocolLiteral("abc");

        assertArrayEquals(new ProtocolObject[]{a},new ProtocolList(new ProtocolObject[]{a}).getObjects());
        assertArrayEquals(new ProtocolObject[]{a,ab},new ProtocolList(new ProtocolObject[]{a,ab}).getObjects());
        assertArrayEquals(new ProtocolObject[]{a,ab,abc},new ProtocolList(new ProtocolObject[]{a,ab,abc}).getObjects());
    }

    @Test
    public void testToProtocolString(){
        ProtocolAtom a = new ProtocolAtom("a");
        ProtocolQuoted ab = new ProtocolQuoted("ab");
        ProtocolLiteral abc = new ProtocolLiteral("abc");

        assertEquals("(a)",new ProtocolList(new ProtocolObject[]{a}).toProtocolString());
        assertEquals("(a \"ab\")",new ProtocolList(new ProtocolObject[]{a,ab}).toProtocolString());
        assertEquals("(a \"ab\" {3}\r\nabc)",new ProtocolList(new ProtocolObject[]{a,ab,abc}).toProtocolString());
    }

    @Test
    public void testToString(){
        ProtocolAtom a = new ProtocolAtom("a");
        ProtocolQuoted ab = new ProtocolQuoted("ab");
        ProtocolLiteral abc = new ProtocolLiteral("abc");

        assertEquals("ProtocolList{objects=[ProtocolAtom{value='a'}]}",new ProtocolList(new ProtocolObject[]{a}).toString());
        assertEquals("ProtocolList{objects=[ProtocolAtom{value='a'}, ProtocolQuoted{value='ab'}]}",new ProtocolList(new ProtocolObject[]{a,ab}).toString());
        assertEquals("ProtocolList{objects=[ProtocolAtom{value='a'}, ProtocolQuoted{value='ab'}, ProtocolLiteral{isNonSynchronizing=false, value='abc'}]}",new ProtocolList(new ProtocolObject[]{a,ab,abc}).toString());
    }

}