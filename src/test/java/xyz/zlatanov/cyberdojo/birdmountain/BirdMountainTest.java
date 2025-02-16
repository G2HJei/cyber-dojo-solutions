package xyz.zlatanov.cyberdojo.birdmountain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BirdMountainTest {

	@Test
	void shouldEstimatePlainsHeight() {
		assertEquals(0, new BirdMountain(" ").height());
	}

	@Test
	void shouldEstimateSingleHill() {
		assertEquals(1, new BirdMountain(" ^ ").height());
	}

	@Test
	void shouldEstimateDoubleHill() {
		assertEquals(2, new BirdMountain("""
				^^^
				^^^
				^^^""").height());
	}

	@Test
	void shouldPassKataExample() {
		assertEquals(3, new BirdMountain("" +
				"^^^^^^       \n" +
				" ^^^^^^^^    \n" +
				"  ^^^^^^^    \n" +
				"  ^^^^^      \n" +
				"  ^^^^^^^^^^^\n" +
				"  ^^^^^^     \n" +
				"  ^^^^       \n").height());
	}

	@Test
	void shouldEstimateX() {
		assertEquals(2, new BirdMountain("" +
				"xxx   xxx\n" +
				" xxx xxx \n" +
				"  xxxxx  \n" +
				"   xxx   \n" +
				"  xxxxx  \n" +
				" xxx xxx \n" +
				"xxx   xxx\n").height());
	}
}