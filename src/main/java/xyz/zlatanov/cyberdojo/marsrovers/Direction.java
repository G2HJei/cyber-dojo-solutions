package xyz.zlatanov.cyberdojo.marsrovers;

public enum Direction {

	NORTH, EAST, SOUTH, WEST;

	@Override
	public String toString() {
		return name().substring(0, 1);
	}

	public Direction left() {
		return values()[(ordinal() - 1 + values().length) % values().length];
	}

	public Direction right() {
		return values()[(ordinal() + 1) % values().length];
	}
}
