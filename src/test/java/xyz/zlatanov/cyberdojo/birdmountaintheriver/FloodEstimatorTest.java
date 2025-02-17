package xyz.zlatanov.cyberdojo.birdmountaintheriver;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;
import static xyz.zlatanov.cyberdojo.birdmountaintheriver.HeightsEstimator.RIVER;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FloodEstimatorTest {

	@ParameterizedTest
	@MethodSource
	void shouldEstimateFlood(int days, int[][] input, int[][] expected) {
		var floodEstimate = FloodEstimator.estimate(input, days);
		assertArrayEquals(expected, floodEstimate);
	}

	static List<Arguments> shouldEstimateFlood() {
		return List.of(
				argumentSet("Day 1 Flood",
						1,
						new int[][] {
								{ 0, 1, 1, 1, 1, 0 },
								{ 1, 2, 2, 2, 1, 0 },
								{ 1, 1, 1, 1, 1, 0 },
								{ RIVER, RIVER, RIVER, RIVER, RIVER, RIVER },
								{ 1, 0, 1, 0, 1, 1 }
						},
						new int[][] {
								{ 0, 1, 1, 1, 1, RIVER },
								{ 1, 2, 2, 2, 1, RIVER },
								{ 1, 1, 1, 1, 1, RIVER },
								{ RIVER, RIVER, RIVER, RIVER, RIVER, RIVER },
								{ 1, RIVER, 1, RIVER, 1, 1 }
						}),
				argumentSet("Day 2 Flood",
						2,
						new int[][] {
								{ 0, 1, 1, 1, 1, 0 },
								{ 1, 2, 2, 2, 1, 0 },
								{ 1, 1, 1, 1, 1, 0 },
								{ RIVER, RIVER, RIVER, RIVER, RIVER, RIVER },
								{ 1, 0, 1, 0, 1, 1 }
						},
						new int[][] {
								{ RIVER, RIVER, RIVER, RIVER, RIVER, RIVER },
								{ RIVER, 2, 2, 2, RIVER, RIVER },
								{ RIVER, RIVER, RIVER, RIVER, RIVER, RIVER },
								{ RIVER, RIVER, RIVER, RIVER, RIVER, RIVER },
								{ RIVER, RIVER, RIVER, RIVER, RIVER, RIVER }
						}),
				argumentSet("Day 3 Flood",
						3,
						new int[][] {
								{ 0, 1, 1, 1, 1, 0 },
								{ 1, 2, 2, 2, 1, 0 },
								{ 1, 1, 1, 1, 1, 0 },
								{ RIVER, RIVER, RIVER, RIVER, RIVER, RIVER },
								{ 1, 0, 1, 0, 1, 1 }
						},
						new int[][] {
								{ RIVER, RIVER, RIVER, RIVER, RIVER, RIVER },
								{ RIVER, RIVER, RIVER, RIVER, RIVER, RIVER },
								{ RIVER, RIVER, RIVER, RIVER, RIVER, RIVER },
								{ RIVER, RIVER, RIVER, RIVER, RIVER, RIVER },
								{ RIVER, RIVER, RIVER, RIVER, RIVER, RIVER }
						}));
	}
}