package xyz.zlatanov.cyberdojo.birdmountaintheriver;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BirdMountainTheRiverTest {

	@ParameterizedTest
	@MethodSource
	void shouldPredictDryGround(String input, int[] expected) {
		assertArrayEquals(expected, BirdMountainTheRiver.dryGround(input));
	}

	static List<Arguments> shouldPredictDryGround() {
		return List.of(
				Arguments.argumentSet("Nothing",
						"",
						new int[] { 0, 0, 0, 0 }),

				Arguments.argumentSet("Only river",
						"-",
						new int[] { 0, 0, 0, 0 }),

				Arguments.argumentSet("No river",
						" ",
						new int[] { 1, 1, 1, 1 }),

				Arguments.argumentSet("Small mountain with river",
						"- ^",
						new int[] { 2, 1, 0, 0 }),

				Arguments.argumentSet("Kata example", """
						  ^^^^^^            \s
						^^^^^^^^       ^^^  \s
						^^^^^^^  ^^^        \s
						^^^^^^^  ^^^        \s
						^^^^^^^  ^^^        \s
						---------------------
						^^^^^               \s
						   ^^^^^^^^  ^^^^^^^\s
						^^^^^^^^     ^     ^\s
						^^^^^        ^^^^^^^""",
						new int[] { 189, 99, 19, 3 })

		);
	}
}