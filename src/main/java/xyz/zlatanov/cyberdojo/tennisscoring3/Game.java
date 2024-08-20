package xyz.zlatanov.cyberdojo.tennisscoring3;

import java.util.Objects;

public class Game {

	private final String	player1;
	private final String	player2;
	private int				score1	= 0;
	private int				score2	= 0;

	public Game(String player1, String player2) {
		this.player1 = player1;
		this.player2 = player2;
	}

	public void point(String playerName) {
		if (Objects.equals(playerName, player1)) {
			score1++;
		} else {
			score2++;
		}
	}

	public String score() {
		if (score1 == score2) {
			return tieScore();
		}
		if (playerAdvantage()) {
			return playerAdvantageScore();
		}
		if (gameOver()) {
			return gameOverScore();
		} else {
			return regularScore();
		}
	}

	private boolean playerAdvantage() {
		return score1 >= 3 && score2 >= 3
				&& Math.abs(score1 - score2) == 1;
	}

	private boolean gameOver() {
		return (score1 > 3 || score2 > 3)
				&& Math.abs(score1 - score2) > 1;
	}

	private String tieScore() {
		if (score1 < 3) {
			return jargonPts(score1) + "-ALL";
		} else {
			return "DEUCE";
		}
	}

	private String playerAdvantageScore() {
		return score1 > score2
				? "ADVANTAGE, " + player1
				: "ADVANTAGE, " + player2;
	}

	private String regularScore() {
		return jargonPts(score1) + "-" + jargonPts(score2);
	}

	private String gameOverScore() {
		return score1 > score2
				? "GAME, " + player1
				: "GAME, " + player2;
	}

	private String jargonPts(int scorePlayer2) {
		return switch (scorePlayer2) {
			case 0 -> "LOVE";
			case 1 -> "FIFTEEN";
			case 2 -> "THIRTY";
			case 3 -> "FORTY";
			default -> throw new IllegalArgumentException("Illegal jargon points");
		};
	}
}
