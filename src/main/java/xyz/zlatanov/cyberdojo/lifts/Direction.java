package xyz.zlatanov.cyberdojo.lifts;

public enum Direction {

	UP, DOWN;

	public Direction reverse() {
		if (this == UP) {
			return DOWN;
		} else {
			return UP;
		}
	}
}
