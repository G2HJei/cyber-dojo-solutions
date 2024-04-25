package xyz.zlatanov.cyberdojo.marsrovers;

public record Coordinates(int x, int y) {

	@Override
	public String toString() {
		return x + ":" + y;
	}

}
