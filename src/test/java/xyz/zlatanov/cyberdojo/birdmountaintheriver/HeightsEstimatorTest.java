package xyz.zlatanov.cyberdojo.birdmountaintheriver;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;
import static xyz.zlatanov.cyberdojo.birdmountaintheriver.HeightsEstimator.RIVER;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HeightsEstimatorTest {

	@ParameterizedTest
	@MethodSource
	void shouldEstimateHeight(String input, int[][] expected) {
		assertArrayEquals(expected, HeightsEstimator.estimate(input));
	}

	static List<Arguments> shouldEstimateHeight() {
		return List.of(
				argumentSet("Heights of river",
						"-",
						new int[][] { { RIVER } }),

				argumentSet("Heights of plains",
						" ",
						new int[][] { { 0 } }),

				argumentSet("Heights of hill",
						"^",
						new int[][] { { 1 } }),

				argumentSet("Heights of mountain with river",
						"""
								 ^^^^^
								^^^^^^
								^^^^^-
								------
								^ ^ ^^""",
						new int[][] {
								{ 0, 1, 1, 1, 1, 1 },
								{ 1, 2, 2, 2, 2, 1 },
								{ 1, 1, 1, 1, 1, RIVER },
								{ RIVER, RIVER, RIVER, RIVER, RIVER, RIVER },
								{ 1, 0, 1, 0, 1, 1 }
						}));
	}

}