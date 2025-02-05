package xyz.zlatanov.cyberdojo.lifts;

public record FloorRequest(int floor, Type type, Direction direction) implements Comparable<FloorRequest> {

	@Override
	public int compareTo(FloorRequest other) {
		var floorCompare = Integer.compare(floor, other.floor);
		var typeCompare = Integer.compare(type.ordinal(), other.type.ordinal());
		var directionCompare = Integer.compare(direction.ordinal(), other.direction.ordinal());
		return floorCompare != 0 ? floorCompare
				: typeCompare != 0 ? typeCompare
						: directionCompare;
	}

	public enum Type {
		CALL, FLOOR
	}
}
