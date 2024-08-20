package xyz.zlatanov.cyberdojo.tennisscoring2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class GameTest {

	@Test
	void shouldStartWithZeroPoints() {
		assertJargonScore(0, 0,
				"love - love");
	}

	@Test
	void shouldCountServingPlayerScore() {
		assertJargonScore(1, 0,
				"fifteen - love");
	}

	@Test
	void shouldCountReceivingPlayerScore() {
		assertJargonScore(0, 1,
				"love - fifteen");
	}

	@Test
	void shouldCountMultiplePoints_30_0() {
		assertJargonScore(2, 0,
				"thirty - love");
	}

	@Test
	void shouldCountMultiplePoints_40_0() {
		assertJargonScore(3, 0,
				"forty - love");
	}

	@Test
	void shouldCountMultiplePoints_0_30() {
		assertJargonScore(0, 2,
				"love - thirty");
	}

	@Test
	void shouldCountMultiplePoints_0_40() {
		assertJargonScore(0, 3,
				"love - forty");
	}

	@Test
	void shouldDeclareWinner() {
		assertJargonScore(4, 0,
				"game serving player");
	}

	@Test
	void shouldAccountForAdvantageOfServingPlayer() {
		assertJargonScore(4, 3,
				"advantage serving player");
	}

	@Test
	void shouldAccountForAdvantageOfReceivingPlayer() {
		assertJargonScore(3, 4,
				"advantage receiving player");
	}

	@Test
	void shouldAccountForDeuce() {
		assertJargonScore(3, 3,
				"deuce");
	}

	@Test
	void shouldNotAllowScoringIfGameOver() {
		final var game = playGame(4, 0);
		assertThrows(GameAlreadyOverException.class, game::scoreServingPlayer);
	}

	@Test
	void shouldNotAllowScoringIfGameOver2() {
		final var game = playGame(0, 4);
		assertThrows(GameAlreadyOverException.class, game::scoreReceivingPlayer);
	}

	private void assertJargonScore(final int servingPts, final int receivingPts, String jargonScore) {
		final var game = playGame(servingPts, receivingPts);
		assertEquals(jargonScore, game.score());
	}

	private Game playGame(int servingPts, int receivingPts) {
		final var game = new Game();
		var scoreForServing = true; // simulate back and forth action that can lead to deuce/advantage if long enough
		while (servingPts > 0 || receivingPts > 0) {
			if (receivingPts == 0 || (scoreForServing && servingPts > 0)) {
				game.scoreServingPlayer();
				servingPts--;
				scoreForServing = false;
			} else {
				game.scoreReceivingPlayer();
				receivingPts--;
				scoreForServing = true;
			}
		}
		return game;
	}

}