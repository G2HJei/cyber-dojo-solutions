package xyz.zlatanov.cyberdojo.isograms;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class IsogramValidatorTest {

	IsogramValidator validator = new IsogramValidator();

	@Test
	void shouldValidateEmptyString() {
		assertTrue(validator.isValid(""));
	}

	@Test
	void shouldValidateNonIsogram() {
		assertFalse(validator.isValid("aa"));
	}

	@Test
	void shouldValidateIsogram() {
		assertTrue(validator.isValid("mouse"));
	}

	@Test
	void shouldIgnoreCase() {
		assertFalse(validator.isValid("moOse"));
	}

}