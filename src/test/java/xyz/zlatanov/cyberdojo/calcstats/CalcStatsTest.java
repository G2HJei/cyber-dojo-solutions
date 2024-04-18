package xyz.zlatanov.cyberdojo.calcstats;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CalcStatsTest {

	@Test
	void shouldFindMin() {
		assertEquals(0, new CalcStats(new int[] { 0, 1 }).min());
	}

	@Test
	void shouldFindMax() {
		assertEquals(1, new CalcStats(new int[] { 0, 1 }).max());
	}

	@Test
	void shouldFindElementsCount() {
		assertEquals(2, new CalcStats(new int[] { 0, 1 }).elementsCount());
	}

	@Test
	void shouldFindAverageValue() {
		assertEquals(2.5, new CalcStats(new int[] { 0, 1, 2, 3, 4, 5 }).average());
	}
}