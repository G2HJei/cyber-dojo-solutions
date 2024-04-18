package xyz.zlatanov.cyberdojo.gameoflife;

import java.util.Scanner;

public class Grid {

	private final int	rows;
	private final int	cols;
	private boolean[][]	content;

	public Grid(String str) {
		try (Scanner scanner = new Scanner(str)) {
			String[] indexes = scanner.nextLine().split(" ");
			rows = Integer.parseInt(indexes[0]);
			cols = Integer.parseInt(indexes[1]);

			content = new boolean[rows][cols];
			for (int r = 0; r < rows; r++) {
				String line = scanner.nextLine();
				for (int c = 0; c < cols; c++) {
					content[r][c] = line.charAt(c) == '*';
				}
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(rows + " " + cols + "\n");
		for (boolean[] row : content) {
			for (boolean cell : row) {
				sb.append(cell ? '*' : '.');
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public Grid evolve() {
		boolean[][] newContent = new boolean[rows][cols];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				int liveNeighbours = countLiveNeighbours(r, c);
				newContent[r][c] = (content[r][c] && (liveNeighbours == 2 || liveNeighbours == 3)) // rules #1,2,3
						|| (!content[r][c] && liveNeighbours == 3); // rule #4
			}
		}
		content = newContent;
		return this;
	}

	private int countLiveNeighbours(int r, int c) {
		int liveCount = 0;
		for (int i = Math.max(0, r - 1); i <= Math.min(rows - 1, r + 1); i++) {
			for (int j = Math.max(0, c - 1); j <= Math.min(cols - 1, c + 1); j++) {
				if (i != r || j != c) { // Exclude the cell itself
					liveCount += content[i][j] ? 1 : 0;
				}
			}
		}
		return liveCount;
	}

}