package xyz.zlatanov.cyberdojo.closesttozero;

public class ClosestToZero {

	private ClosestToZero() {
	}

	public static int find(int[] ints) {
		int minDistance = Integer.MAX_VALUE;
		int result = Integer.MAX_VALUE;
		for (int i : ints) {
			int distance = Math.abs(i);
			if (distance < minDistance || (distance == minDistance && i > 0)) {
				result = i;
				minDistance = distance;
			}
		}
		return result;
	}
}
