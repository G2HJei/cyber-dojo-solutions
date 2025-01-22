package xyz.zlatanov.cyberdojo.multiplesof3and5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

class MultiplesOf3And5CalculatorTest {

	MultiplesOf3And5Calculator calculator = new MultiplesOf3And5Calculator();

	@Test
	void shouldNotCountLessThan3() {
		IntStream.range(-1, 3)
				.forEach(i -> assertEquals(0, calculator.sum(i)));

	}

	@Test
	void shouldCount3s() {
		assertEquals(3, calculator.sum(3));
	}

	@Test
	void shouldCount5s() {
		assertEquals(8, calculator.sum(5));
	}

	@Test
	void shouldCountMultiplesOf3And5OnlyOnce() {
		assertEquals(60, calculator.sum(15));
	}
}