package xyz.zlatanov.cyberdojo.haiku;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class WordTest {

	@Test
	void shouldCountSyllablesOfSimpleWord() {
		assertEquals(1, new Word("hulk").syllables());
	}

	@Test
	void shouldCountSyllablesOfLongWord() {
		assertEquals(4, new Word("honkytonkman").syllables());
	}

	@Test
	void shouldCountVowelSequenceAsASingleSyllable() {
		assertEquals(1, new Word("beast").syllables());
	}

	@Test
	void shouldCountVowelAtTheStart() {
		assertEquals(2, new Word("easter").syllables());
	}

	@Test
	void shouldCountVowelAtTheEnd() {
		assertEquals(2, new Word("coffee").syllables());
	}
}