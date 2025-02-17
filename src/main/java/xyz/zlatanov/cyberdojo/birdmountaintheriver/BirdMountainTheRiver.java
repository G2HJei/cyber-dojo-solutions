package xyz.zlatanov.cyberdojo.birdmountaintheriver;

import static xyz.zlatanov.cyberdojo.birdmountaintheriver.HeightsEstimator.RIVER;

public class BirdMountainTheRiver {

	public static int[] dryGround(String input) {
		var grid = HeightsEstimator.estimate(input);
		var result = new int[4];
		result[0] = countDryGround(grid);
		result[1] = countDryGround(FloodEstimator.estimate(grid, 1));
		result[2] = countDryGround(FloodEstimator.estimate(grid, 2));
		result[3] = countDryGround(FloodEstimator.estimate(grid, 3));
		return result;
	}

	private static int countDryGround(int[][] grid) {
		var result = 0;
		for (int[] line : grid) {
			for (int i : line) {
				if (i > RIVER) {
					result++;
				}
			}
		}
		return result;
	}
}
