package com.yocto.yoclib.imap.tests.protocol;

import com.yocto.yoclib.imap.protocol.ProtocolAtom;
import com.yocto.yoclib.imap.protocol.ProtocolObject;
import com.yocto.yoclib.imap.protocol.ProtocolParser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ProtocolParserTest{

	@Test
	public void testParsingComplexAtom(){
		ProtocolObject[] objects = ProtocolParser.parse("!#$$&'+,-.0123456789:;<=>?@^_`|[}");

		assertEquals(1,objects.length);
		assertInstanceOf(ProtocolAtom.class,objects[0]);

		ProtocolAtom atom = (ProtocolAtom) objects[0];
		assertEquals("!#$$&'+,-.0123456789:;<=>?@^_`|[}",atom.getValue());
	}

}