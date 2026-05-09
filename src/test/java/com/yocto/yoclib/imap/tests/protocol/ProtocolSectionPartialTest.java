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

		Object unknown = new ProtocolAtom("ab");
		assertNotEquals(new ProtocolSectionPartial(atom),unknown);
		assertNotEquals(unknown,new ProtocolSectionPartial(atom));
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

	@Test
	public void testToProtocolString(){
		ProtocolAtom atom = new ProtocolAtom("a");
		ProtocolSubordinate subordinate = new ProtocolSubordinate(new ProtocolObject[]{
				new ProtocolAtom("ab"),
				new ProtocolAtom("abc"),
		});

		assertEquals("a[ab abc]<123.456>",new ProtocolSectionPartial(atom,subordinate,123,456).toProtocolString());
		assertEquals("a[ab abc]<123>",new ProtocolSectionPartial(atom,subordinate,123).toProtocolString());
		assertEquals("a[ab abc]",new ProtocolSectionPartial(atom,subordinate).toProtocolString());
		assertEquals("a<123.456>",new ProtocolSectionPartial(atom,123,456).toProtocolString());
		assertEquals("a<123>",new ProtocolSectionPartial(atom,123).toProtocolString());
		assertEquals("a",new ProtocolSectionPartial(atom).toProtocolString());
	}

	@Test
	public void testToString(){
		ProtocolAtom atom = new ProtocolAtom("a");
		ProtocolSubordinate subordinate = new ProtocolSubordinate(new ProtocolObject[]{
				new ProtocolAtom("ab"),
				new ProtocolAtom("abc"),
		});

		assertEquals("ProtocolSectionPartial{atom=ProtocolAtom{value='a'}, subordinate=ProtocolSubordinate{objects=[ProtocolAtom{value='ab'}, ProtocolAtom{value='abc'}]}, partialOffset=123, partialLength=456}",new ProtocolSectionPartial(atom,subordinate,123,456).toString());
		assertEquals("ProtocolSectionPartial{atom=ProtocolAtom{value='a'}, subordinate=ProtocolSubordinate{objects=[ProtocolAtom{value='ab'}, ProtocolAtom{value='abc'}]}, partialOffset=123, partialLength=null}",new ProtocolSectionPartial(atom,subordinate,123).toString());
		assertEquals("ProtocolSectionPartial{atom=ProtocolAtom{value='a'}, subordinate=ProtocolSubordinate{objects=[ProtocolAtom{value='ab'}, ProtocolAtom{value='abc'}]}, partialOffset=null, partialLength=null}",new ProtocolSectionPartial(atom,subordinate).toString());
		assertEquals("ProtocolSectionPartial{atom=ProtocolAtom{value='a'}, subordinate=null, partialOffset=123, partialLength=456}",new ProtocolSectionPartial(atom,123,456).toString());
		assertEquals("ProtocolSectionPartial{atom=ProtocolAtom{value='a'}, subordinate=null, partialOffset=123, partialLength=null}",new ProtocolSectionPartial(atom,123).toString());
		assertEquals("ProtocolSectionPartial{atom=ProtocolAtom{value='a'}, subordinate=null, partialOffset=null, partialLength=null}",new ProtocolSectionPartial(atom).toString());
	}

}