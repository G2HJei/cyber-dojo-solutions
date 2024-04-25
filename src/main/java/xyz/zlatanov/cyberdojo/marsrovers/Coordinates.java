package xyz.zlatanov.cyberdojo.marsrovers;

public class Coordinates {

	private final int	x;
	private final int	y;

	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	@Override
	public String toString() {
		return x + ":" + y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Coordinates that = (Coordinates) o;
		return x == that.x && y == that.y;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		return result;
	}
}
