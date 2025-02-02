package xyz.zlatanov.cyberdojo.lifts;

public record Status(int floor, Direction direction) {

	public static Status of(int floor, Direction direction) {
		return new Status(floor, direction);
	}
}