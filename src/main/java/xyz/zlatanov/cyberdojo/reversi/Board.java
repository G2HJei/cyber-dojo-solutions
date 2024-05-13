package xyz.zlatanov.cyberdojo.reversi;

public class Board {

	private final DiskColor[][] squares = new DiskColor[10][10]; // zero index will not be used

	public void place(Position pos, DiskColor color) {
		if (squares[pos.x()][pos.y()] != null) {
			throw new ReversiException("already_occupied");
		}
		squares[pos.x()][pos.y()] = color;
	}

	public DiskColor square(Position pos) {
		return squares[pos.x()][pos.y()];
	}
}
