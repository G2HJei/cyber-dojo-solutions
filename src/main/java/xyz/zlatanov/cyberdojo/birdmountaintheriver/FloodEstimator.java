package xyz.zlatanov.cyberdojo.birdmountaintheriver;

import static xyz.zlatanov.cyberdojo.birdmountaintheriver.HeightsEstimator.RIVER;

public class FloodEstimator {

	private int				estimatedDay	= 0;
	private final int		requestedDay;
	private final int[][]	grid;

	public static int[][] estimate(int[][] dayZeroGrid, int day) {
		var estimator = new FloodEstimator(dayZeroGrid, day);
		return estimator.estimate();
	}

	private FloodEstimator(int[][] grid, int requestedDay) {
		this.grid = grid;
		this.requestedDay = requestedDay;
	}

	private int[][] estimate() {
		if (estimatedDay == requestedDay) {
			return grid;
		}
		var floodedNewSpot = false;
		for (int y = 0; y < grid.length; y++) {
			var line = grid[y];
			for (int x = 0; x < line.length; x++) {
				var spot = line[x];
				if (spot != RIVER && isLowEnough(spot) && hasNeighbouringRiver(y, x)) {
					grid[y][x] = RIVER;
					floodedNewSpot = true;
				}
			}
		}
		if (!floodedNewSpot) {
			estimatedDay++;
		}
		return estimate();
	}

	private boolean isLowEnough(int spot) {
		return spot <= estimatedDay;
	}

	private boolean hasNeighbouringRiver(int y, int x) {
		var left = x > 0 && grid[y][x - 1] == RIVER;
		var right = x < grid[0].length - 1 && grid[y][x + 1] == RIVER;
		var top = y > 0 && grid[y - 1][x] == RIVER;
		var bottom = y < grid.length - 1 && grid[y + 1][x] == RIVER;
		return left || right || top || bottom;
	}
}
