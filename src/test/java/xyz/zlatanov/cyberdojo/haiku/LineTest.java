package xyz.zlatanov.cyberdojo.haiku;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LineTest {

	@Test
	void shouldCountSyllables() {
		var line = new Line("computer programs");
		assertEquals(5, line.syllables());
	}
}