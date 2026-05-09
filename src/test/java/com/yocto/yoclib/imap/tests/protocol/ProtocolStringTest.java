package com.yocto.yoclib.imap.tests.protocol;

import com.yocto.yoclib.imap.protocol.ProtocolAtom;
import com.yocto.yoclib.imap.protocol.ProtocolLiteral;
import com.yocto.yoclib.imap.protocol.ProtocolQuoted;
import com.yocto.yoclib.imap.protocol.ProtocolString;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProtocolStringTest {

    @Test
    public void testEquals(){
        assertEquals(new ProtocolString("a") {
            @Override
            public String toProtocolString() {
                return null;
            }
        }, new ProtocolString("a"){
            @Override
            public String toProtocolString() {
                return null;
            }
        });
        assertEquals(new ProtocolString("ab"){
            @Override
            public String toProtocolString() {
                return null;
            }
        },new ProtocolString("ab"){
            @Override
            public String toProtocolString() {
                return null;
            }
        });
        assertEquals(new ProtocolString("abc"){
            @Override
            public String toProtocolString() {
                return null;
            }
        },new ProtocolString("abc"){
            @Override
            public String toProtocolString() {
                return null;
            }
        });

        assertNotEquals(new ProtocolString("a"){
            @Override
            public String toProtocolString() {
                return null;
            }
        },null);
        assertNotEquals(null,new ProtocolString("a"){
            @Override
            public String toProtocolString() {
                return null;
            }
        });

        assertNotEquals(new ProtocolString("ab"){
            @Override
            public String toProtocolString() {
                return null;
            }
        },"ab");
        assertNotEquals("ab",new ProtocolString("ab"){
            @Override
            public String toProtocolString() {
                return null;
            }
        });
    }

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

    @Test
    public void testStaticCreate(){
        assertInstanceOf(ProtocolLiteral.class,ProtocolString.create("\r"));
        assertInstanceOf(ProtocolLiteral.class,ProtocolString.create("\n"));
        assertInstanceOf(ProtocolQuoted.class,ProtocolString.create(" "));
        assertInstanceOf(ProtocolQuoted.class,ProtocolString.create("\t"));
        assertInstanceOf(ProtocolQuoted.class,ProtocolString.create(""));
        assertInstanceOf(ProtocolAtom.class,ProtocolString.create("a"));
        assertInstanceOf(ProtocolAtom.class,ProtocolString.create("ab"));
        assertInstanceOf(ProtocolAtom.class,ProtocolString.create("abc"));
        assertInstanceOf(ProtocolQuoted.class,ProtocolString.create("a",true));
        assertInstanceOf(ProtocolQuoted.class,ProtocolString.create("ab",true));
        assertInstanceOf(ProtocolQuoted.class,ProtocolString.create("abc",true));
    }

}