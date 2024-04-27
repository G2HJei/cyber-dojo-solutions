package xyz.zlatanov.cyberdojo.tennisscoring;

public class Score {

	private int	servingPlayerScore		= 0;
	private int	receivingPlayerScore	= 0;

	public String describe() {
		return servingPlayerScore > 2 && receivingPlayerScore > 2
				? advantageDescription()
				: regularDescription();
	}

	public void servingPlayerPoint() {
		servingPlayerScore++;
	}

	public void receivingPlayerPoint() {
		receivingPlayerScore++;
	}

	private String regularDescription() {
		return pointDescription(servingPlayerScore) + "-" + pointDescription(receivingPlayerScore);
	}

	private String pointDescription(int p) {
		return switch (p) {
			case 0 -> "love";
			case 1 -> "fifteen";
			case 2 -> "thirty";
			case 3 -> "forty";
			default -> throw new IllegalArgumentException();
		};
	}

	private String advantageDescription() {
		if (servingPlayerScore == receivingPlayerScore) {
			return "deuce";
		} else if (servingPlayerScore > receivingPlayerScore) {
			return "advantage serving player";
		} else {
			return "advantage receiving player";
		}
	}
}
