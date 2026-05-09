package com.yocto.yoclib.imap.tests.protocol;

import com.yocto.yoclib.imap.protocol.ProtocolAtom;
import com.yocto.yoclib.imap.protocol.ProtocolBinaryLiteral;
import com.yocto.yoclib.imap.protocol.ProtocolLiteral;
import com.yocto.yoclib.imap.protocol.ProtocolObject;
import com.yocto.yoclib.imap.protocol.ProtocolParser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ProtocolParserTest{

	@Test
	public void testParsingBinaryLiteral(){
		assertArrayEquals(new ProtocolObject[]{
				new ProtocolAtom("~"),
		},ProtocolParser.parse("~\r\n"));

		assertArrayEquals(new ProtocolObject[]{
				new ProtocolAtom("~/Mail/foo"),
		},ProtocolParser.parse("~/Mail/foo\r\n"));

		assertArrayEquals(new ProtocolObject[]{
				new ProtocolBinaryLiteral(""),
				new ProtocolAtom("abc"),
		},ProtocolParser.parse("~{0}\r\n abc\r\n"));

		assertArrayEquals(new ProtocolObject[]{
				new ProtocolBinaryLiteral("def"),
				new ProtocolAtom("abc"),
		},ProtocolParser.parse("~{3}\r\ndef abc\r\n"));

		assertArrayEquals(new ProtocolObject[]{
				new ProtocolBinaryLiteral("",true),
				new ProtocolAtom("abc"),
		},ProtocolParser.parse("~{0+}\r\n abc\r\n"));

		assertArrayEquals(new ProtocolObject[]{
				new ProtocolBinaryLiteral("def",true),
				new ProtocolAtom("abc"),
		},ProtocolParser.parse("~{3+}\r\ndef abc\r\n"));
	}

	@Test
	public void testParsingLiteral(){
		assertArrayEquals(new ProtocolObject[]{
				new ProtocolLiteral(""),
				new ProtocolAtom("abc"),
		},ProtocolParser.parse("{0}\r\n abc\r\n"));

		assertArrayEquals(new ProtocolObject[]{
				new ProtocolLiteral("def"),
				new ProtocolAtom("abc"),
		},ProtocolParser.parse("{3}\r\ndef abc\r\n"));

		assertArrayEquals(new ProtocolObject[]{
				new ProtocolLiteral("",true),
				new ProtocolAtom("abc"),
		},ProtocolParser.parse("{0+}\r\n abc\r\n"));

		assertArrayEquals(new ProtocolObject[]{
				new ProtocolLiteral("def",true),
				new ProtocolAtom("abc"),
		},ProtocolParser.parse("{3+}\r\ndef abc\r\n"));
	}

	@Test
	public void testParsingComplexAtom(){
		//ProtocolObject[] objects = ProtocolParser.parse("!#$$&'+,-.0123456789:;<=>?@^_`|[}");

		//assertEquals(1,objects.length);
		//assertInstanceOf(ProtocolAtom.class,objects[0]);

		//ProtocolAtom atom = (ProtocolAtom) objects[0];
		//assertEquals("!#$$&'+,-.0123456789:;<=>?@^_`|[}",atom.getValue());
	}

}