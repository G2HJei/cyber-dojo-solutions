package xyz.zlatanov.cyberdojo.birdmountaintheriver;

public class HeightsEstimator {

	public static final int		RIVER			= -1;
	private static final int	NOT_ESTIMATED	= -2;

	private final int[][]		grid;
	private int					height			= 0;

	public static int[][] estimate(String in) {
		return new HeightsEstimator(in).grid;
	}

	private HeightsEstimator(String view) {
		var viewSplit = view.split("\n");
		grid = initGrid(viewSplit);
		estimateHeights();
	}

	private int[][] initGrid(String[] viewSplit) {
		final int[][] grid;
		grid = new int[viewSplit.length][viewSplit[0].length()];
		for (int y = 0; y < viewSplit.length; y++) {
			var line = viewSplit[y];
			char[] charArray = line.toCharArray();
			for (int x = 0; x < charArray.length; x++) {
				var c = charArray[x];
				if (c == ' ') {
					grid[y][x] = 0;
				} else if (c == '-') {
					grid[y][x] = RIVER;
				} else {
					grid[y][x] = NOT_ESTIMATED;
				}
			}
		}
		return grid;
	}

	private void estimateHeights() {
		for (int y = 0; y < grid.length; y++) {
			var line = grid[y];
			for (int x = 0; x < line.length; x++) {
				var spot = line[x];
				if (spot == NOT_ESTIMATED && hasEstimatedNeighbour(y, x)) {
					grid[y][x] = height + 1;
				}
			}
		}
		var updated = updateHeight();
		if (updated) {
			estimateHeights();
		}
	}

	private boolean hasEstimatedNeighbour(int y, int x) {
		var edge = height == 0 && (x == 0 || y == 0 || x == grid[0].length - 1 || y == grid.length - 1);
		var left = x > 0 && (grid[y][x - 1] == height || grid[y][x - 1] == RIVER);
		var right = x < grid[0].length - 1 && (grid[y][x + 1] == height || grid[y][x + 1] == RIVER);
		var top = y > 0 && (grid[y - 1][x] == height || grid[y - 1][x] == RIVER);
		var bottom = y < grid.length - 1 && (grid[y + 1][x] == height || grid[y + 1][x] == RIVER);
		return edge || left || right || top || bottom;
	}

	private boolean updateHeight() {
		var max = 0;
		for (int[] line : grid) {
			for (int i : line) {
				if (i > max) {
					max = i;
				}
			}
		}
		var updated = max != height;
		height = max;
		return updated;
	}
}
