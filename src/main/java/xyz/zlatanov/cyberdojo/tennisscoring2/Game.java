package xyz.zlatanov.cyberdojo.tennisscoring2;

public class Game {

	private int	servingPlayerScore		= 0;
	private int	receivingPlayerScore	= 0;

	public void scoreServingPlayer() {
		if (isOver()) {
			throw new GameAlreadyOverException();
		}
		servingPlayerScore++;
	}

	public void scoreReceivingPlayer() {
		if (isOver()) {
			throw new GameAlreadyOverException();
		}
		receivingPlayerScore++;
	}

	public String score() {
		if (isOver()) {
			return declareWinner();
		}
		if (isDeuce()) {
			return "deuce";
		}
		if (isAdvantage()) {
			return describeAdvantage();
		} else {
			return toScoreJargon(servingPlayerScore) + " - " + toScoreJargon(receivingPlayerScore);
		}
	}

	private boolean isOver() {
		return (servingPlayerScore > 3 || receivingPlayerScore > 3)
			   && Math.abs(servingPlayerScore - receivingPlayerScore) >= 2;
	}

	private boolean isDeuce() {
		return servingPlayerScore == receivingPlayerScore
				&& servingPlayerScore >= 3;
	}

	private boolean isAdvantage() {
		return servingPlayerScore >= 3 && receivingPlayerScore >= 3
				&& Math.abs(servingPlayerScore - receivingPlayerScore) < 2;
	}

	private String declareWinner() {
		return servingPlayerScore >= 4
				? "game serving player"
				: "game receiving player";
	}

	private String describeAdvantage() {
		return servingPlayerScore > receivingPlayerScore
				? "advantage serving player"
				: "advantage receiving player";
	}

	private String toScoreJargon(int score) {
		return switch (score) {
			case 0 -> "love";
			case 1 -> "fifteen";
			case 2 -> "thirty";
			case 3 -> "forty";
			default -> throw new IllegalStateException("Unexpected value: " + score);
		};
	}
}
