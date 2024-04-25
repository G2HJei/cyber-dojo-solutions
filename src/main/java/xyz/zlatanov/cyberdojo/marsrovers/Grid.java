package xyz.zlatanov.cyberdojo.marsrovers;

import java.util.List;
import java.util.Objects;

public class Grid {

	private static final int		MAX_X	= 10;
	private static final int		MAX_Y	= 10;

	private final List<Coordinates>	obstacles;

	public Grid(Coordinates... obstacles) {
		this.obstacles = List.of(obstacles);
	}

	public int maxX() {
		return MAX_X;
	}

	public int maxY() {
		return MAX_Y;
	}

	public boolean isOccupied(Coordinates coordinates) {
		return obstacles.stream().anyMatch(o -> Objects.equals(coordinates, o));
	}
}
