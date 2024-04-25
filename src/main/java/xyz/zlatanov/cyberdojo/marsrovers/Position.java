package xyz.zlatanov.cyberdojo.marsrovers;

public class Position {

	private final Grid			grid;
	private final Coordinates	coordinates;
	private final Direction		direction;

	public Position(Grid grid, Coordinates coordinates, Direction direction) {
		this.grid = grid;
		this.coordinates = coordinates;
		this.direction = direction;
	}

	@Override
	public String toString() {
		return coordinates + ":" + direction;
	}

	public Position left() {
		return new Position(grid, coordinates, direction.left());
	}

	public Position right() {
		return new Position(grid, coordinates, direction.right());
	}

	public Position forward() throws PositionObstructedException {
		int x = coordinates.x();
		int y = coordinates.y();
		Coordinates newCoordinates = switch (direction) {
			case NORTH -> new Coordinates(x, (y + 1) % grid.maxY());
			case EAST -> new Coordinates((x + 1) % grid.maxX(), y);
			case SOUTH -> new Coordinates(x, (y - 1 + grid.maxY()) % grid.maxY());
			case WEST -> new Coordinates((x - 1 + grid.maxX()) % grid.maxX(), y);
		};
		if (grid.isOccupied(newCoordinates)) {
			throw new PositionObstructedException();
		}
		return new Position(grid, newCoordinates, direction);
	}

}
