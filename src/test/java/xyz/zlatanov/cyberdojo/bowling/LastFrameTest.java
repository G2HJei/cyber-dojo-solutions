package xyz.zlatanov.cyberdojo.bowling;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class LastFrameTest {

	Frame lastFrame = new LastFrame();

	@Test
	void shouldCompleteRegular() {
		lastFrame.roll(1);
		lastFrame.roll(2);
		assertTrue(lastFrame.isComplete());
	}

	@Test
	void shouldCompleteSpare() {
		lastFrame.roll(1);
		lastFrame.roll(9);
		lastFrame.roll(1);
		assertTrue(lastFrame.isComplete());
	}

	@Test
	void shouldCompleteStrike() {
		lastFrame.roll(10);
		lastFrame.roll(1);
		lastFrame.roll(2);
		assertTrue(lastFrame.isComplete());
	}

	@Test
	void shouldNotCompleteRegular() {
		lastFrame.roll(1);
		assertFalse(lastFrame.isComplete());
	}

	@Test
	void shouldNotCompleteSpare() {
		lastFrame.roll(0);
		lastFrame.roll(10);
		assertFalse(lastFrame.isComplete());
	}

	@Test
	void shouldNotCompleteStrike() {
		lastFrame.roll(10);
		lastFrame.roll(1);
		assertFalse(lastFrame.isComplete());
	}

	@Test
	void shouldNotCompleteStrike2() {
		lastFrame.roll(10);
		assertFalse(lastFrame.isComplete());
	}
}