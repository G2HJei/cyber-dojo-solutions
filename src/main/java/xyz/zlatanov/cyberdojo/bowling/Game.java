package xyz.zlatanov.cyberdojo.bowling;

public class Game {

	private final Frame[]	frames				= new Frame[] {
			new DummyFrame(), // match frames index with frame number
			new Frame(),
			new Frame(),
			new Frame(),
			new Frame(),
			new Frame(),
			new Frame(),
			new Frame(),
			new Frame(),
			new Frame(),
			new LastFrame()
	};
	private int				currentFrameIndex	= 1;
	private int				currentRollIndex	= 1;

	void roll(int pins) {
		var frame = frames[currentFrameIndex];
		frame.roll(pins);
		addBonus(pins);
		prepareNextRoll(frame);
	}

	private void addBonus(int pins) {
		var prevFrame = frames[currentFrameIndex - 1];
		if (prevFrame.isSpare() && currentRollIndex == 1) {
			prevFrame.bonus(pins);
		}
		if (prevFrame.isStrike() && currentRollIndex != 3) {
			prevFrame.bonus(pins);
			if (frames[currentFrameIndex - 2].isStrike() && currentRollIndex == 1) {
				frames[currentFrameIndex - 2].bonus(pins);
			}
		}
	}

	private void prepareNextRoll(Frame frame) {
		if (frame.isComplete()) {
			currentFrameIndex++;
			currentRollIndex = 1;
		} else {
			currentRollIndex++;
		}
	}

	int score() {
		var score = 0;
		for (int i = 1; i < frames.length; i++) {
			score += frames[i].score();
		}
		return score;
	}
}
