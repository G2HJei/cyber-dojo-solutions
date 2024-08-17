package xyz.zlatanov.cyberdojo.tennisscoring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

class GameScoreTest {

	GameScore gameScore = new GameScore();

	@Test
	void shouldStartWithZeroPoints() {
		assertScoreDescription("love-love");
	}

	@Test
	void shouldTrackServingPlayerScore() {
		gameScore.servingPlayerPoint();
		assertScoreDescription("fifteen-love");
	}

	@Test
	void shouldTrackReceivingPlayerScore() {
		gameScore.receivingPlayerPoint();
		assertScoreDescription("love-fifteen");
	}

	@Test
	void shouldDescribeDeuce() {
		IntStream.range(0, 3).forEach(i -> {
			gameScore.servingPlayerPoint();
			gameScore.receivingPlayerPoint();
		});
		assertScoreDescription("deuce");
	}

	@Test
	void shouldDescribeServingPlayerAdvantage() {
		IntStream.range(0, 3).forEach(i -> {
			gameScore.servingPlayerPoint();
			gameScore.receivingPlayerPoint();
		});
		gameScore.servingPlayerPoint();
		assertScoreDescription("advantage serving player");
	}

	@Test
	void shouldDescribeReceivingPlayerAdvantage() {
		IntStream.range(0, 3).forEach(i -> {
			gameScore.servingPlayerPoint();
			gameScore.receivingPlayerPoint();
		});
		gameScore.receivingPlayerPoint();
		assertScoreDescription("advantage receiving player");
	}

	@Test
	void shouldDescribeServingPlayerGame() {
		IntStream.range(0, 4).forEach(i -> gameScore.servingPlayerPoint());
		assertScoreDescription("game serving player");
	}

	@Test
	void shouldDescribeReceivingPlayerGame() {
		IntStream.range(0, 4).forEach(i -> gameScore.receivingPlayerPoint());
		assertScoreDescription("game receiving player");
	}

	private void assertScoreDescription(String description) {
		assertEquals(description, gameScore.describe());
	}
}