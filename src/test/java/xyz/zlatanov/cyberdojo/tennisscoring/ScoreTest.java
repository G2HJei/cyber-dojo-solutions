package xyz.zlatanov.cyberdojo.tennisscoring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

class ScoreTest {

	Score score = new Score();

	@Test
	void shouldStartWithZeroPoints() {
		assertScoreDescription("love-love");
	}

	@Test
	void shouldTrackServingPlayerScore() {
		score.servingPlayerPoint();
		assertScoreDescription("fifteen-love");
	}

	@Test
	void shouldTrackReceivingPlayerScore() {
		score.receivingPlayerPoint();
		assertScoreDescription("love-fifteen");
	}

	@Test
	void shouldDescribeDeuce() {
		IntStream.range(0, 3).forEach(i -> {
			score.servingPlayerPoint();
			score.receivingPlayerPoint();
		});
		assertScoreDescription("deuce");
	}

	@Test
	void shouldDescribeServingPlayerAdvantage() {
		IntStream.range(0, 3).forEach(i -> {
			score.servingPlayerPoint();
			score.receivingPlayerPoint();
		});
		score.servingPlayerPoint();
		assertScoreDescription("advantage serving player");
	}

	@Test
	void shouldDescribeReceivingPlayerAdvantage() {
		IntStream.range(0, 3).forEach(i -> {
			score.servingPlayerPoint();
			score.receivingPlayerPoint();
		});
		score.receivingPlayerPoint();
		assertScoreDescription("advantage receiving player");
	}

	@Test
	void shouldDescribeServingPlayerGame() {
		IntStream.range(0, 4).forEach(i -> score.servingPlayerPoint());
		assertScoreDescription("game serving player");
	}

	@Test
	void shouldDescribeReceivingPlayerGame() {
		IntStream.range(0, 4).forEach(i -> score.receivingPlayerPoint());
		assertScoreDescription("game receiving player");
	}

	private void assertScoreDescription(String description) {
		assertEquals(description, score.describe());
	}
}