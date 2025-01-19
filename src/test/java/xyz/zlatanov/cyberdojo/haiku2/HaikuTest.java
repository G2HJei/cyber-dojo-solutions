package xyz.zlatanov.cyberdojo.haiku2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HaikuTest {

	String	validHaikuText					= "computer programs/the bugs ate my code/i must not let them";
	String	validHaikuWithJoinedVowelsText	= "computer desktop/the bugs do eat my bureau/i must not let them";
	String	invalidHaikuText				= "developers/developers/developers";

	@Test
	void shouldCreateValidHaiku() {
		var haiku = new Haiku(validHaikuText);
		assertEquals(validHaikuText, haiku.toString());
	}

	@Test
	void shouldCreateInvalidHaiku() {
		var haiku = new Haiku(invalidHaikuText);
		assertEquals(invalidHaikuText, haiku.toString());
	}

	@Test
	void shouldReportValidHaikuValidity() {
		var haiku = new Haiku(validHaikuText);
		assertTrue(haiku.isValid());
	}

	@Test
	void shouldReportJoinedVowelsValidHaikuValidity() {
		var haiku = new Haiku(validHaikuWithJoinedVowelsText);
		assertTrue(haiku.isValid());
	}

	@Test
	void shouldReportInvalidHaikuValidity() {
		var haiku = new Haiku(invalidHaikuText);
		assertFalse(haiku.isValid());
	}

	@Test
	void shouldReportSyllableCount() {
		var haiku = new Haiku(validHaikuText);
		var syllableCounts = haiku.syllableCount();
		assertEquals(5, syllableCounts[0]);
		assertEquals(7, syllableCounts[1]);
		assertEquals(5, syllableCounts[2]);
	}
}