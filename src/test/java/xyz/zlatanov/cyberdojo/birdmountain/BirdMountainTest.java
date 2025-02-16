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
}