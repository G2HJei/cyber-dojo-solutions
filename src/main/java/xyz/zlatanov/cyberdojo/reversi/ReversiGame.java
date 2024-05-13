package xyz.zlatanov.cyberdojo.reversi;

import static xyz.zlatanov.cyberdojo.reversi.DiskColor.B;
import static xyz.zlatanov.cyberdojo.reversi.DiskColor.W;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ReversiGame {

	private final Board		board;
	private final DiskColor	actingPlayer;

	public ReversiGame(Board board, String setup) {
		this.board = board;
		try (Scanner scanner = new Scanner(setup)) {
			for (int y = 8; y > 0; y--) {
				String line = scanner.nextLine();
				for (int x = 1; x < 9; x++) {
					switch (line.charAt(x - 1)) {
						case 'W' -> board.place(Position.of(x, y), W);
						case 'B' -> board.place(Position.of(x, y), B);
						default -> board.place(Position.of(x, y), null);
					}
				}
			}
			actingPlayer = DiskColor.valueOf(scanner.nextLine());
		}
	}

	public DiskColor actingPlayer() {
		return actingPlayer;
	}

	public String printLegalMoves() {
		var legalMoves = legalMoves();
		var sb = new StringBuilder();
		for (int y = 8; y > 0; y--) {
			for (int x = 1; x < 9; x++) {
				var squareDisk = board.square(Position.of(x, y));
				if (isInLegalMoves(legalMoves, x, y)) {
					sb.append("0");
				} else if (squareDisk != null) {
					sb.append(squareDisk.name());
				} else {
					sb.append(".");
				}
			}
			sb.append("\n");
		}
		sb.append(actingPlayer.name());
		return sb.toString();
	}

	public Position[] legalMoves() {
		final List<Position> result = new ArrayList<>();
		for (int x = 8; x > 0; x--) {
			for (int y = 8; y > 0; y--) {
				if (board.square(Position.of(x, y)) != null) {
					continue;
				}
				if (findLeft(x, y) || findRight(x, y)
						|| findUp(x, y) || findDown(x, y)) {
					result.add(new Position(x, y));
				}
			}
		}
		return result.toArray(new Position[] {});
	}

	private boolean findLeft(int x, int y) {
		if (board.square(Position.of(x - 1, y)) != actingPlayer.reversed()) {
			return false;
		}
		var result = false;
		for (int left = x - 2; left > 0; left--) {
			if (board.square(Position.of(left, y)) == actingPlayer) {
				result = true;
				break;
			}
		}
		return result;
	}

	private boolean findRight(int x, int y) {
		if (board.square(Position.of(x + 1, y)) != actingPlayer.reversed()) {
			return false;
		}
		var result = false;
		for (int right = x + 2; right < 9; right++) {
			if (board.square(Position.of(right, y)) == actingPlayer) {
				result = true;
				break;
			}
		}
		return result;
	}

	private boolean findUp(int x, int y) {
		if (board.square(Position.of(x, y + 1)) != actingPlayer.reversed()) {
			return false;
		}
		var result = false;
		for (int up = y + 2; up < 9; up++) {
			if (board.square(Position.of(x, up)) == actingPlayer) {
				result = true;
				break;
			}
		}
		return result;
	}

	private boolean findDown(int x, int y) {
		if (board.square(Position.of(x, y - 1)) != actingPlayer.reversed()) {
			return false;
		}
		var result = false;
		for (int down = y - 2; down > 0; down--) {
			if (board.square(Position.of(x, down)) == actingPlayer) {
				result = true;
				break;
			}
		}
		return result;
	}

	private static boolean isInLegalMoves(Position[] legalMoves, int x, int y) {
		return Arrays.stream(legalMoves).anyMatch(p -> p.x() == x && p.y() == y);
	}
}
