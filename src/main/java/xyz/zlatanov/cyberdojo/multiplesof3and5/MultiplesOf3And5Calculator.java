package xyz.zlatanov.cyberdojo.multiplesof3and5;

public class MultiplesOf3And5Calculator {

	public int sum(int max) {
		var sum = 0;
		int i = 2;
		while (i++ < max) {
			if (i % 3 == 0 || i % 5 == 0) {
				sum += i;
			}
		}
		return sum;
	}
}
