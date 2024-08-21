package xyz.zlatanov.cyberdojo.bowling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class GameTest {

	Game game = new Game();

	static Object[][] testScenarios() {
		return new Object[][] {
				{ "Should start with zero score", 0, new int[] {} },
				{ "Should track score", 1, new int[] { 1 } },
				{ "Should track score thought the game", 6, new int[] { 1, 2, 3 } },
				{ "Should track bonus spare score", 16, new int[] { 1, 9, 3 } },
				{ "Should track bonus strike score", 22, new int[] { 10, 2, 4 } },
				{ "Should track bonus double score", 37, new int[] { 10, 10, 1, 2 } },
				{ "Should track perfect score", 300, new int[] { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 } },
				{ "Should track bonus strikes and spares", 187, new int[] { 10, 9, 1, 5, 5, 7, 2, 10, 10, 10, 9, 0, 8, 2, 9, 1, 10 } },
		};
	}

	@ParameterizedTest
	@MethodSource("testScenarios")
	void shouldPassTestScenarios(String scenarioName, int score, int... rolls) {
		Arrays.stream(rolls).forEach(game::roll);
		assertEquals(score, game.score());
	}
}