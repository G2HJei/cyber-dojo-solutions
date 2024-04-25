package xyz.zlatanov.cyberdojo.marsrovers;

public enum Direction {

	NORTH, EAST, SOUTH, WEST;

	@Override
	public String toString() {
		return name().substring(0, 1);
	}

	public Direction left() {
		return values()[ordinal() != 0 ? ordinal() - 1 : values().length - 1];
	}

	public Direction right() {
		return values()[ordinal() < values().length - 1 ? ordinal() + 1 : 0];
	}
}
