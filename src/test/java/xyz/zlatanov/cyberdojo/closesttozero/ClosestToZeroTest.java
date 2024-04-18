package xyz.zlatanov.cyberdojo.closesttozero;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ClosestToZeroTest {

	@Test
	void shouldFindClosestToZero() {
		assertEquals(1, ClosestToZero.find(new int[] { 1, 2 }));
	}

	@Test
	void shouldFindClosestToZeroNegative() {
		assertEquals(-1, ClosestToZero.find(new int[] { -1, 2 }));
	}

	@Test
	void shouldFindClosestToZeroTied() {
		assertEquals(1, ClosestToZero.find(new int[] { -1, 1, 2 }));
	}
}