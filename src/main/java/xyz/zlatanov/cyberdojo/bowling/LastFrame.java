package xyz.zlatanov.cyberdojo.bowling;

public class LastFrame extends Frame {

	@Override
	public void bonus(int extraPoints) {
		throw new RuntimeException("Cannot add bonus");
	}

	@Override
	public boolean isComplete() {
		if (isSpare() || isStrike()) {
			return rolls.size() == 3;
		} else {
			return rolls.size() == 2;
		}
	}
}
