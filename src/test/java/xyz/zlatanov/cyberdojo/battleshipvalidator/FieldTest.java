package xyz.zlatanov.cyberdojo.battleshipvalidator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FieldTest {

	public static Stream<Arguments> testSetups() {
		return Stream.of(
				Arguments.of(new int[][] {
						{ 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 }, // battleship
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 1, 1, 0, 1, 1, 1, 0, 0, 0 }, // cruisers 2x
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 }, // destroyers 3x
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 0, 1, 0, 1, 0, 1, 0, 0, 0 }, // submarines 4x
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
						true, //
						"Should validate horizontal setup"),

				Arguments.of(new int[][] {
						{ 1, 0, 1, 0, 1, 0, 1, 0, 0, 0 },
						{ 1, 0, 1, 0, 1, 0, 0, 0, 0, 0 },
						{ 1, 0, 1, 0, 0, 0, 1, 0, 0, 0 },
						{ 1, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
						{ 0, 0, 1, 0, 1, 0, 1, 0, 0, 0 },
						{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 1, 0, 1, 0, 1, 0, 0, 0 },
						{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
						// B....2xC...3xD...4xSub)
						true, //
						"Should validate vertical setup"),

				Arguments.of(new int[][] {
						{ 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 }, // battleship
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 1, 1, 0, 1, 1, 1, 0, 0, 0 }, // cruisers 2x
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 }, // destroyers 3x
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 0, 1, 0, 1, 0, 1, 0, 0, 0 }, // submarines 4x
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 }, // 5-size ship
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
						false, //
						"Should not allow ships larger than battleship"),
				Arguments.of(new int[][] {
						{ 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 }, // battleship
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 1, 1, 1, 1, 1, 0, 0, 0, 0 }, // cruisers 2x, neighbours
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 }, // destroyers 3x
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 0, 1, 0, 1, 0, 1, 0, 0, 0 }, // submarines 3x
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
						false, //
						"Should detect neighbouring ships"),
				Arguments.of(new int[][] {
						{ 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 }, // battleship
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 1, 1, 0, 1, 1, 1, 0, 0, 0 }, // cruisers 2x
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 }, // destroyers 3x
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 0, 1, 0, 0, 1, 0, 0, 0, 0 }, // submarines 3x
						{ 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 }, // submarine neighbour by diagonal
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
						false, //
						"Should detect diagonally neighbouring submarines"),
				Arguments.of(new int[][] {
						{ 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 }, // battleship
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 1, 1, 0, 1, 1, 1, 0, 0, 0 }, // cruisers 2x
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 }, // destroyers 3x
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 0, 1, 0, 0, 0, 1, 0, 0, 0 }, // submarines 3x
						{ 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 }, // submarine neighbour by diagonal
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
						false, //
						"Should detect diagonally neighbouring submarines 2"),
				Arguments.of(new int[][] {
						{ 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 }, // battleship
						{ 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 }, // diagonal neighbour cruiser
						{ 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 }, // cruisers
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 }, // destroyers 3x
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 0, 1, 0, 1, 0, 1, 0, 0, 0 }, // submarines 4x
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
						false, //
						"Should detect diagonally neighbouring ships"),
				Arguments.of(new int[][] {
						{ 0, 0, 1, 0, 1, 0, 1, 0, 0, 0 },
						{ 0, 0, 1, 0, 1, 0, 0, 0, 0, 0 },
						{ 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 },
						{ 0, 1, 0, 0, 1, 0, 0, 0, 0, 0 },
						{ 0, 1, 0, 0, 1, 0, 1, 0, 0, 0 },
						{ 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 1, 0, 0, 1, 0, 1, 0, 0, 0 },
						{ 0, 0, 1, 0, 1, 0, 0, 0, 0, 0 },
						{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 } },
						// ..^^^^......................
						false, //
						"Should detect diagonally neighbouring ships 2"),
				Arguments.of(new int[][] {
						{ 1, 0, 0, 0, 1, 0, 1, 0, 0, 0 },
						{ 1, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
						{ 1, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
						{ 0, 1, 0, 0, 1, 0, 0, 0, 0, 0 },
						{ 0, 1, 0, 0, 1, 0, 1, 0, 0, 0 },
						{ 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 1, 0, 0, 1, 0, 1, 0, 0, 0 },
						{ 1, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
						{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
						// CCB.......DDD...4xSub),
						false, //
						"Should detect diagonally neighbouring ships 3"),
				Arguments.of(new int[][] {
						{ 1, 0, 0, 0, 1, 0, 1, 0, 0, 0 },
						{ 1, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
						{ 1, 0, 1, 0, 0, 0, 1, 0, 0, 0 },
						{ 0, 0, 1, 0, 1, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 1, 0, 1, 0, 0, 0 },
						{ 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 }, // battleship
						{ 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 } },
						// C.....D....DD...4xSub..C
						false, //
						"Should detect neighbouring ships at the ends"),
				Arguments.of(new int[][] {
						{ 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 }, // battleship
						{ 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 }, // cruisers 2x
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 }, // destroyers 3x
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 0, 1, 0, 1, 0, 1, 0, 0, 0 }, // submarines 4x
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
						false, //
						"Should detect neighbouring ships"),
				Arguments.of(new int[][] {
						{ 1, 1, 0, 0, 0, 0, 0, 1, 1, 1 }, // crooked battleship + cruiser
						{ 0, 1, 1, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 }, // cruiser
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 }, // destroyers 3x
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 0, 1, 0, 1, 0, 1, 0, 0, 0 }, // submarines 4x
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
						false, //
						"Should detect crooked ships"));
	}

	@ParameterizedTest
	@MethodSource("testSetups")
	void shouldPassTests(int[][] setup, boolean expectedResult, String message) {
		assertEquals(expectedResult, new Field(setup).isValid(), message);
	}
}
