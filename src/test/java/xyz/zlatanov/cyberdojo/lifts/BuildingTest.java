package xyz.zlatanov.cyberdojo.lifts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;

class BuildingTest {

	@ParameterizedTest
	@FieldSource
	void shouldTransportPassengers(int capacity, int[][] queues, int[] expectedStops) {
		var lift = new Lift(capacity);
		var building = new Building(lift);
		var result = building.runLift(queues);
		assertArrayEquals(expectedStops, result);
	}

	static List<Arguments>	shouldTransportPassengersSingle	= List.of(
			argumentSet("Down and down",
					5,
					new int[][] {
							new int[] {}, // G
							new int[] { 0 }, // 1
							new int[] {}, // 2
							new int[] {}, // 3
							new int[] { 2 }, // 4
							new int[] { 3 }, // 5
							new int[] {}, // 6
					},
					new int[] { 0, 5, 4, 3, 2, 1, 0 })
	//
	);

	static List<Arguments>	shouldTransportPassengers		= List.of(
			argumentSet("Empty",
					5,
					new int[][] {
							new int[] {}, // G
							new int[] {}, // 1
							new int[] {}, // 2
							new int[] {}, // 3
							new int[] {}, // 4
							new int[] {}, // 5
							new int[] {}, // 6
					},
					new int[] { 0 }),

			argumentSet("Up",
					5,
					new int[][] {
							new int[] {}, // G
							new int[] {}, // 1
							new int[] { 5, 5, 5 }, // 2
							new int[] {}, // 3
							new int[] {}, // 4
							new int[] {}, // 5
							new int[] {}, // 6
					},
					new int[] { 0, 2, 5, 0 }),

			argumentSet("Down",
					5,
					new int[][] {
							new int[] {}, // G
							new int[] {}, // 1
							new int[] { 1, 1 }, // 2
							new int[] {}, // 3
							new int[] {}, // 4
							new int[] {}, // 5
							new int[] {}, // 6
					},
					new int[] { 0, 2, 1, 0 }),

			argumentSet("Up and up",
					5,
					new int[][] {
							new int[] {}, // G
							new int[] { 3 }, // 1
							new int[] { 4 }, // 2
							new int[] {}, // 3
							new int[] { 5 }, // 4
							new int[] {}, // 5
							new int[] {}, // 6
					},
					new int[] { 0, 1, 2, 3, 4, 5, 0 }),

			argumentSet("Down and down",
					5,
					new int[][] {
							new int[] {}, // G
							new int[] { 0 }, // 1
							new int[] {}, // 2
							new int[] {}, // 3
							new int[] { 2 }, // 4
							new int[] { 3 }, // 5
							new int[] {}, // 6
					},
					new int[] { 0, 5, 4, 3, 2, 1, 0 }),

			argumentSet("Up and down",
					5,
					new int[][] {
							new int[] { 3 }, // G
							new int[] { 2 }, // 1
							new int[] { 0 }, // 2
							new int[] { 2 }, // 3
							new int[] {}, // 4
							new int[] {}, // 5
							new int[] { 5 }, // 6
					},
					new int[] { 0, 1, 2, 3, 6, 5, 3, 2, 0 }),

			argumentSet("Small lift",
					1,
					new int[][] {
							new int[] {}, // G
							new int[] { 2 }, // 1
							new int[] { 3, 3, 3 }, // 2
							new int[] { 1 }, // 3
							new int[] {}, // 4
							new int[] {}, // 5
							new int[] {}, // 6
					},
					new int[] { 0, 1, 2, 3, 1, 2, 3, 2, 3, 0 }),

			argumentSet("Yo-yo",
					2,
					new int[][] {
							new int[] {}, // G
							new int[] {}, // 1
							new int[] { 4, 4, 4, 4 }, // 2
							new int[] {}, // 3
							new int[] { 2, 2, 2, 2 }, // 4
							new int[] {}, // 5
							new int[] {}, // 6
					},
					new int[] { 0, 2, 4, 2, 4, 2, 0 }),

			argumentSet("Coming to work",
					5,
					new int[][] {
							new int[] { 1, 2, 3, 4 }, // G
							new int[] {}, // 1
							new int[] {}, // 2
							new int[] {}, // 3
							new int[] {}, // 4
							new int[] {}, // 5
							new int[] {}, // 6
					},
					new int[] { 0, 1, 2, 3, 4, 0 }),

			argumentSet("Lift full up",
					5,
					new int[][] {
							new int[] { 3, 3, 3, 3, 3, 3 }, // G
							new int[] {}, // 1
							new int[] {}, // 2
							new int[] {}, // 3
							new int[] {}, // 4
							new int[] {}, // 5
							new int[] {}, // 6
					},
					new int[] { 0, 3, 0, 3, 0 }),

			argumentSet("Lift full down",
					5,
					new int[][] {
							new int[] {}, // G
							new int[] {}, // 1
							new int[] {}, // 2
							new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, // 3
							new int[] {}, // 4
							new int[] {}, // 5
							new int[] {}, // 6
					},
					new int[] { 0, 3, 1, 3, 1, 3, 1, 0 }),

			argumentSet("Lift full up and down",
					5,
					new int[][] {
							new int[] { 3, 3, 3, 3, 3, 3 }, // G
							new int[] {}, // 1
							new int[] {}, // 2
							new int[] {}, // 3
							new int[] {}, // 4
							new int[] { 4, 4, 4, 4, 4, 4 }, // 5
							new int[] {}, // 6
					},
					new int[] { 0, 3, 5, 4, 0, 3, 5, 4, 0 }),

			argumentSet("Tricky queues",
					5,
					new int[][] {
							new int[] {}, // G
							new int[] { 0, 0, 0, 6 }, // 1
							new int[] {}, // 2
							new int[] {}, // 3
							new int[] {}, // 4
							new int[] { 6, 6, 0, 0, 0, 6 }, // 5
							new int[] {}, // 6
					},
					new int[] { 0, 1, 5, 6, 5, 1, 0, 1, 0 }),

			argumentSet("Fire drill",
					5,
					new int[][] {
							new int[] {}, // G
							new int[] { 0, 0, 0, 0 }, // 1
							new int[] { 0, 0, 0, 0 }, // 2
							new int[] { 0, 0, 0, 0 }, // 3
							new int[] { 0, 0, 0, 0 }, // 4
							new int[] { 0, 0, 0, 0 }, // 5
							new int[] { 0, 0, 0, 0 }, // 6
					},
					new int[] { 0, 6, 5, 4, 3, 2, 1, 0, 5, 4, 3, 2, 1, 0, 4, 3, 2, 1, 0, 3, 2, 1, 0, 1, 0 }));

}