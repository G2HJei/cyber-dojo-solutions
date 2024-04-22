package xyz.zlatanov.cyberdojo.romannumerals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static xyz.zlatanov.cyberdojo.romannumerals.RomanNumerals.toRomanNumeral;

import org.junit.jupiter.api.Test;

class RomanNumeralsTest {

	@Test
	void shouldDetermineSingleDigitNumerals() {
		assertEquals("I", toRomanNumeral(1));
	}

	@Test
	void shouldDetermineSimpleDoubleDigitNumerals() {
		assertEquals("XX", toRomanNumeral(20));
	}

	@Test
	void shouldDetermineDoubleDigitNumeralsWithZero() {
		assertEquals("XC", toRomanNumeral(90));
	}

	@Test
	void shouldDetermineSimpleTripleDigitNumerals() {
		assertEquals("CD", toRomanNumeral(400));
	}

	@Test
	void shouldDetermineComplexTripleDigitNumerals() {
		assertEquals("CIX", toRomanNumeral(109));
	}

	@Test
	void shouldDetermineSimpleFourDigitNumerals() {
		assertEquals("MM", toRomanNumeral(2000));
	}

	@Test
	void shouldDetermineComplexFourDigitNumerals() {
		assertEquals("MMVIII", toRomanNumeral(2008));

	}
}