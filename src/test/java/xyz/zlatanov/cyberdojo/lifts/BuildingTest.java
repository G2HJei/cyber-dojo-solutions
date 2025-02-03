package xyz.zlatanov.cyberdojo.lifts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;

class BuildingTest {

	@ParameterizedTest
	@FieldSource("testCases")
	void shouldTransportPassengers(int[][] queues, int[] expectedStops) {
		var lift = new Lift(queues.length);
		var building = new Building(lift);
		var result = building.runLift(queues);
		assertArrayEquals(expectedStops, result);
	}

	static List<Arguments> testCases = List.of(
			argumentSet("Should transport up",
					new int[][] {
							new int[0], // G
							new int[0], // 1
							new int[] { 5, 5, 5 }, // 2
							new int[0], // 3
							new int[0], // 4
							new int[0], // 5
							new int[0], // 6
					}, new int[] { 0, 2, 5, 0 }),

			argumentSet("Should transport down",
					new int[][] {
							new int[0], // G
							new int[0], // 1
							new int[] { 1, 1 }, // 2
							new int[0], // 3
							new int[0], // 4
							new int[0], // 5
							new int[0], // 6
					}, new int[] { 0, 2, 1, 0 }),

			argumentSet("Should transport up and up again",
					new int[][] {
							new int[0], // G
							new int[] { 3 }, // 1
							new int[] { 4 }, // 2
							new int[0], // 3
							new int[] { 5 }, // 4
							new int[0], // 5
							new int[0], // 6
					}, new int[] { 0, 1, 2, 3, 4, 5, 0 }),

			argumentSet("Should transport down and down again",
					new int[][] {
							new int[0], // G
							new int[] { 0 }, // 1
							new int[0], // 2
							new int[0], // 3
							new int[] { 2 }, // 4
							new int[] { 3 }, // 5
							new int[0], // 6
					}, new int[] { 0, 5, 4, 3, 2, 1, 0 }));

}