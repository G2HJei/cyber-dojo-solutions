package xyz.zlatanov.cyberdojo.bowling;

import java.util.ArrayList;
import java.util.List;

public class Frame {

	protected final List<Integer>	rolls		= new ArrayList<>();
	private int						points		= 0;
	private int						extraPoints	= 0;

	public void roll(int pins) {
		rolls.add(pins);
		points += pins;
	}

	public int score() {
		return points + extraPoints;
	}

	public void bonus(int extraPoints) {
		this.extraPoints += extraPoints;
	}

	public boolean isSpare() {
		return rolls.size() > 1 && rolls.getFirst() + rolls.get(1) == 10;
	}

	public boolean isStrike() {
		return !rolls.isEmpty() && rolls.getFirst() == 10;
	}

	public boolean isComplete() {
		return isSpare() || isStrike() || rolls.size() == 2;
	}

	@Override
	public String toString() {
		return String.format("Rolls %s | Score %s | Points %s | Bonus %s ",
				rolls.stream().map(r -> r + "").reduce("", (aggr, s) -> aggr + ", " + s),
				score(), points, extraPoints);
	}
}
