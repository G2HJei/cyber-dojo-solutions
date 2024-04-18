package xyz.zlatanov.cyberdojo.haiku;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HaikuValidatorTest {

	HaikuValidator haikuValidator = new HaikuValidator();

	@Test
	void shouldValidateSingleHaiku() {
		assertEquals("5,7,5,Y",
				haikuValidator.validate("happy purple frog/eating bugs in the marshes/get indigestion"));
	}

	@Test
	void shouldValidateMultipleHaiku() {
		assertEquals("""
				5,7,5,Y
				5,8,5,N""",
				haikuValidator.validate("""
						happy purple frog/eating bugs in the marshes/get indigestion
						computer programs/the bugs try to eat my code/i will not let them"""));
	}

}