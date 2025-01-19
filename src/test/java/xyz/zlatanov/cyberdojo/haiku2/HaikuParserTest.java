package xyz.zlatanov.cyberdojo.haiku2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class HaikuParserTest {

	String		validHaikuText		= "computer programs/the bugs ate my code/i must not let them";
	String		invalidHaikuText	= "developers/developers/developers";
	Haiku		validHaiku			= new Haiku(validHaikuText);
	Haiku		invalidHaiku		= new Haiku(invalidHaikuText);
	HaikuParser	parser				= new HaikuParser();

	@Test
	void shouldParseSingleHaiku() {
		var haikus = parser.parse(validHaikuText);
		assertEquals(1, haikus.length);
		assertEquals(validHaiku, haikus[0]);
	}

	@Test
	void shouldParseMultipleHaikus() {
		var haikus = parser.parse(validHaikuText + "\n" + invalidHaikuText);
		assertEquals(2, haikus.length);
		assertTrue(Arrays.stream(haikus).toList().contains(validHaiku));
		assertTrue(Arrays.stream(haikus).toList().contains(invalidHaiku));
	}
}