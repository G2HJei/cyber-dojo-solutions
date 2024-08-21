package xyz.zlatanov.cyberdojo.bowling;

public class DummyFrame extends Frame {

	@Override
	public void bonus(int extraPoints) {
	}

	@Override
	public void roll(int pins) {
	}

	@Override
	public int score() {
		return 0;
	}

	@Override
	public boolean isSpare() {
		return false;
	}

	@Override
	public boolean isStrike() {
		return false;
	}

	@Override
	public boolean isComplete() {
		return true;
	}

	@Override
	public String toString() {
		return "Dummy frame";
	}
}
