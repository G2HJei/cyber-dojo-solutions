package xyz.zlatanov.cyberdojo.haiku;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class HaikuTest {

	@Test
	void shouldCreateValidHaiku() {
		assertTrue(new Haiku("computer programs/bugs try to eat my code/i must not let them").isValid());
	}

	@Test
	void shouldCreateInvalidHaiku() {
		assertFalse(new Haiku("this is not/a valid/haiku").isValid());
	}

	@Test
	void shouldCountSyllables() {
		var syllableCount = new Haiku("cant/see/me").syllables();
		assertEquals(0, Arrays.compare(new long[] { 1, 1, 1 }, syllableCount));
	}

	@Test
	void shouldCountManySyllables() {
		var syllableCount = new Haiku("to be the man/you got to beat/the man").syllables();
		assertEquals(0, Arrays.compare(new long[] { 4, 4, 2 }, syllableCount));
	}
}