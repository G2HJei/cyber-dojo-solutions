package xyz.zlatanov.cyberdojo.bowling;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FrameTest {

	Frame frame = new Frame();

	@Test
	void shouldCountScoreWhenPinsAreKnockedDown() {
		frame.roll(1);
		assertEquals(1, frame.score());
	}

	@Test
	void shouldCountBonusScoreOnSpare() {
		frame.roll(5);
		frame.roll(5);
		frame.bonus(1);
		assertEquals(11, frame.score());
	}

	@Test
	void shouldCountFirstBonusScoreOnStrike() {
		frame.roll(10);
		frame.bonus(1);
		assertEquals(11, frame.score());
	}

	@Test
	void shouldCountSecondBonusScoreOnStrike() {
		frame.roll(10);
		frame.bonus(1);
		frame.bonus(2);
		assertEquals(13, frame.score());
	}

	@Test
	void shouldDetermineSpare() {
		frame.roll(1);
		frame.roll(9);
		assertTrue(frame.isSpare());
	}

	@Test
	void shouldDetermineSpareNegative() {
		frame.roll(0);
		frame.roll(0);
		assertFalse(frame.isSpare());
	}

	@Test
	void shouldDetermineStrike() {
		frame.roll(10);
		assertTrue(frame.isStrike());
	}

	@Test
	void shouldDetermineStrikeNegative() {
		frame.roll(4);
		frame.roll(5);
		assertFalse(frame.isStrike());
	}

	@Test
	void shouldDetermineWhenCompleteWhenStrike() {
		frame.roll(10);
		assertTrue(frame.isComplete());
	}

	@Test
	void shouldDetermineWhenCompleteWhenSpare() {
		frame.roll(1);
		frame.roll(9);
		assertTrue(frame.isComplete());
	}

	@Test
	void shouldDetermineWhenCompleteWhenRegular() {
		frame.roll(0);
		frame.roll(0);
		assertTrue(frame.isComplete());
	}

	@Test
	void shouldDetermineNotCompleteSingleRoll() {
		frame.roll(1);
		assertFalse(frame.isComplete());
	}

	@Test
	void shouldDetermineNotCompleteNoRolls() {
		assertFalse(frame.isComplete());
	}
}