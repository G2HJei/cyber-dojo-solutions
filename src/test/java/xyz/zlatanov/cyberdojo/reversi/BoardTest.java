package xyz.zlatanov.cyberdojo.reversi;

import static org.junit.jupiter.api.Assertions.*;
import static xyz.zlatanov.cyberdojo.reversi.DiskColor.W;

import org.junit.jupiter.api.Test;

class BoardTest {

	Board board = new Board();

	@Test
	void shouldStartEmpty() {
		assertNull(board.square(Position.of(1, 1)));
	}

	@Test
	void shouldAllowDiskPlacement() {
		board.place(Position.of(1, 1), W);
		assertEquals(W, board.square(Position.of(1, 1)));
	}

	@Test
	void shouldNotAllowDiskPlacementOnOccupiedSquare() {
		board.place(Position.of(1, 1), W);
		assertThrows(ReversiException.class, () -> board.place(Position.of(1, 1), W));
	}

}