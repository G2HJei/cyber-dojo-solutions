package xyz.zlatanov.cyberdojo.tennisscoring3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class GameTest {

	public static Object[][] testScenarios() {
		return new Object[][] {
				// ties
				{ 0, 0, "LOVE-ALL" },
				{ 1, 1, "FIFTEEN-ALL" },
				{ 2, 2, "THIRTY-ALL" },
				{ 3, 3, "DEUCE" },
				{ 4, 4, "DEUCE" },
				{ 10, 10, "DEUCE" },

				// regular
				{ 1, 0, "FIFTEEN-LOVE" },
				{ 2, 0, "THIRTY-LOVE" },
				{ 3, 0, "FORTY-LOVE" },
				{ 2, 1, "THIRTY-FIFTEEN" },
				{ 3, 1, "FORTY-FIFTEEN" },
				{ 3, 2, "FORTY-THIRTY" },
				{ 0, 1, "LOVE-FIFTEEN" },
				{ 0, 2, "LOVE-THIRTY" },
				{ 0, 3, "LOVE-FORTY" },
				{ 1, 2, "FIFTEEN-THIRTY" },
				{ 2, 3, "THIRTY-FORTY" },

				// advantage
				{ 4, 3, "ADVANTAGE, FEDERER" },
				{ 3, 4, "ADVANTAGE, DIMITROV" },
				{ 9, 8, "ADVANTAGE, FEDERER" },
				{ 8, 9, "ADVANTAGE, DIMITROV" },

				// game over
				{ 4, 0, "GAME, FEDERER" },
				{ 4, 1, "GAME, FEDERER" },
				{ 4, 2, "GAME, FEDERER" },
				{ 0, 4, "GAME, DIMITROV" },
				{ 1, 4, "GAME, DIMITROV" },
				{ 2, 4, "GAME, DIMITROV" },
				{ 7, 5, "GAME, FEDERER" },
				{ 7, 9, "GAME, DIMITROV" }
		};
	}

	@ParameterizedTest
	@MethodSource("testScenarios")
	void shouldCompleteAllTestScenarios(int federerPts, int dimitrovPts, String score) {
		var game = playGame(federerPts, dimitrovPts);
		assertEquals(score, game.score());
	}

	private Game playGame(int ptsFederer, int ptsDimitrov) {
		var game = new Game("FEDERER", "DIMITROV");
		var ptsMax = Math.max(ptsFederer, ptsDimitrov);
		for (int i = 0; i < ptsMax; i++) { // simulate back and forth action
			if (i < ptsFederer) {
				game.point("FEDERER");
			}
			if (i < ptsDimitrov) {
				game.point("DIMITROV");
			}
		}
		return game;
	}

}