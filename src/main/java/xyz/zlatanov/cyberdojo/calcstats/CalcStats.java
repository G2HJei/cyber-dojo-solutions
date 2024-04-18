package xyz.zlatanov.cyberdojo.calcstats;

import java.util.Arrays;

public class CalcStats {

	private int				min	= Integer.MAX_VALUE;
	private int				max	= Integer.MIN_VALUE;
	private final int		elementsCount;
	private final double	average;

	public CalcStats(int[] sequence) {
		for (int i : sequence) {
			min = Math.min(i, min);
			max = Math.max(i, max);
		}
		elementsCount = sequence.length;
		average = (double) Arrays.stream(sequence).sum() / elementsCount;
	}

	public int min() {
		return min;
	}

	public int max() {
		return max;
	}

	public int elementsCount() {
		return elementsCount;
	}

	public double average() {
		return average;
	}
}
