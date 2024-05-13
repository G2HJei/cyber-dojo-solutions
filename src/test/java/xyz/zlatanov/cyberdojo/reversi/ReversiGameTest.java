package xyz.zlatanov.cyberdojo.reversi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static xyz.zlatanov.cyberdojo.reversi.DiskColor.B;
import static xyz.zlatanov.cyberdojo.reversi.DiskColor.W;
import static xyz.zlatanov.cyberdojo.reversi.TestCases.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class ReversiGameTest {

	Board board = new Board();

	@Test
	void shouldSetupBoard() {
		var game = new ReversiGame(board, SIMPLE_SETUP);
		assertEquals(W, board.square(Position.of(4, 4)));
		assertEquals(B, board.square(Position.of(4, 5)));
		assertEquals(B, board.square(Position.of(5, 4)));
		assertEquals(W, board.square(Position.of(5, 5)));
		assertEquals(B, game.actingPlayer());
	}

	@Test
	void shouldAllowOnlyLegalMoves() {
		var game = new ReversiGame(board, SIMPLE_SETUP);
		var legalMoves = game.legalMoves();
		assertTrue(Arrays.stream(legalMoves).anyMatch(p -> p.x() == 4 && p.y() == 3));
		assertTrue(Arrays.stream(legalMoves).anyMatch(p -> p.x() == 3 && p.y() == 4));
		assertTrue(Arrays.stream(legalMoves).anyMatch(p -> p.x() == 6 && p.y() == 5));
		assertTrue(Arrays.stream(legalMoves).anyMatch(p -> p.x() == 5 && p.y() == 6));
	}

	@Test
	void shouldAllowOnlyLegalMovesWithComplexSetup() {
		var game = new ReversiGame(board, COMPLEX_SETUP);
		var legalMoves = game.legalMoves();
		assertTrue(Arrays.stream(legalMoves).anyMatch(p -> p.x() == 2 && p.y() == 4));
		assertTrue(Arrays.stream(legalMoves).anyMatch(p -> p.x() == 3 && p.y() == 5));
		assertTrue(Arrays.stream(legalMoves).anyMatch(p -> p.x() == 5 && p.y() == 7));
	}

	@Test
	void shouldPrintLegalMoves() {
		var game = new ReversiGame(board, SIMPLE_SETUP);
		assertEquals(SIMPLE_RESULT, game.printLegalMoves());
	}

	@Test
	void shouldPrintLegalMovesComplexSetup() {
		var game = new ReversiGame(board, COMPLEX_SETUP);
		assertEquals(COMPLEX_RESULT, game.printLegalMoves());
	}

}