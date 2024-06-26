package xyz.zlatanov.cyberdojo.marsrovers;

import static xyz.zlatanov.cyberdojo.marsrovers.Direction.NORTH;

public class Rover {

	private Position	position;
	private boolean		obstructed	= false;

	public Rover() {
		this(new Grid());
	}

	public Rover(Grid grid) {
		this(new Coordinates(0, 0), NORTH, grid);
	}

	public Rover(Coordinates coordinates, Direction direction) {
		this(coordinates, direction, new Grid());
	}

	public Rover(Coordinates coordinates, Direction direction, Grid grid) {
		this.position = new Position(grid, coordinates, direction);
	}

	public String statusReport() {
		return (obstructed ? "O:" : "")
				+ position.toString();
	}

	public void execute(String command) {
		for (char instruction : command.toCharArray()) {
			switch (instruction) {
				case 'L' -> position = position.left();
				case 'R' -> position = position.right();
				case 'M' -> move();
				default -> throw new IllegalArgumentException("Unknown command");
			}
			if (obstructed) {
				break;
			}
		}
	}
	private void move() {
		try {
			position = position.forward();
		} catch (PositionObstructedException e) {
			obstructed = true;
		}
	}
}
