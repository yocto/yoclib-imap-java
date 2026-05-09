package com.yocto.yoclib.imap.tests.protocol;

import com.yocto.yoclib.imap.protocol.ProtocolAtom;
import com.yocto.yoclib.imap.protocol.ProtocolObject;
import com.yocto.yoclib.imap.protocol.ProtocolSectionPartial;
import com.yocto.yoclib.imap.protocol.ProtocolSubordinate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProtocolSectionPartialTest{

	@Test
	public void testEquals(){
		ProtocolAtom atom = new ProtocolAtom("a");

		assertEquals(new ProtocolSectionPartial(atom),new ProtocolSectionPartial(atom));

		assertNotEquals(new ProtocolSectionPartial(atom),null);
		assertNotEquals(null,new ProtocolSectionPartial(atom));

		assertNotEquals(new ProtocolSectionPartial(atom),atom);
		assertNotEquals(atom,new ProtocolSectionPartial(atom));
	}

	@Test
	public void testGetAtom(){
		assertEquals(new ProtocolAtom("a"),new ProtocolSectionPartial(new ProtocolAtom("a")).getAtom());
	}

	@Test
	public void testGetSubordinate(){
		assertNull(new ProtocolSectionPartial(new ProtocolAtom("a")).getSubordinate());
		assertEquals(new ProtocolSubordinate(new ProtocolObject[]{
				new ProtocolAtom("ab")
		}),new ProtocolSectionPartial(new ProtocolAtom("a"),new ProtocolSubordinate(new ProtocolObject[]{
				new ProtocolAtom("ab")
		})).getSubordinate());
	}

	@Test
	public void testGetPartialOffset(){
		assertNull(new ProtocolSectionPartial(new ProtocolAtom("a"),new ProtocolSubordinate(new ProtocolObject[]{
				new ProtocolAtom("ab")
		})).getPartialOffset());
		assertEquals(123,new ProtocolSectionPartial(new ProtocolAtom("a"),new ProtocolSubordinate(new ProtocolObject[]{
				new ProtocolAtom("ab")
		}),123).getPartialOffset());
	}

	@Test
	public void testGetPartialLength(){
		assertNull(new ProtocolSectionPartial(new ProtocolAtom("a"),123).getPartialLength());
		assertEquals(456,new ProtocolSectionPartial(new ProtocolAtom("a"),123,456).getPartialLength());

		assertNull(new ProtocolSectionPartial(new ProtocolAtom("a"),new ProtocolSubordinate(new ProtocolObject[]{
				new ProtocolAtom("ab")
		}),123).getPartialLength());
		assertEquals(456,new ProtocolSectionPartial(new ProtocolAtom("a"),new ProtocolSubordinate(new ProtocolObject[]{
				new ProtocolAtom("ab")
		}),123,456).getPartialLength());
	}

}