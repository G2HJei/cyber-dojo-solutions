package xyz.zlatanov.cyberdojo.marsrovers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static xyz.zlatanov.cyberdojo.marsrovers.Direction.NORTH;

import org.junit.jupiter.api.Test;

class RoverTest {

	@Test
	void shouldReportItsStatus() {
		var rover = new Rover();
		assertEquals("0:0:N", rover.statusReport());
	}

	@Test
	void shouldTurnLeft() {
		assertCommand(new Rover(), "L", "0:0:W");
	}

	@Test
	void shouldTurnRight() {
		assertCommand(new Rover(), "R", "0:0:E");
	}

	@Test
	void shouldMoveForward() {
		assertCommand(new Rover(), "M", "0:1:N");
	}

	@Test
	void shouldWrapAround() {
		var rover = new Rover(new Coordinates(0, 9), NORTH);
		assertCommand(rover, "M", "0:0:N");
	}

	@Test
	void shouldStopAtObstacles() {
		var grid = new Grid(new Coordinates(0, 1));
		var rover = new Rover(grid);
		assertCommand(rover, "M", "O:0:0:N");
	}

	@Test
	void shouldExecuteMultipleCommands() {
		assertCommand(new Rover(), "LLMRMMLM", "8:8:S");
	}

	@Test
	void shouldInterruptExecutionOnObstacle() {
		var grid = new Grid(new Coordinates(0, 2));
		var rover = new Rover(grid);
		assertCommand(rover, "MMR", "O:0:1:N");
	}

	@Test
	void shouldPassExam1() {
		assertCommand(new Rover(), "MMRMMLM", "2:3:N");
	}

	@Test
	void shouldPassExam2() {
		assertCommand(new Rover(), "MMMMMMMMMM", "0:0:N");
	}

	@Test
	void shouldPassExam3() {
		var grid = new Grid(new Coordinates(0, 3));
		var rover = new Rover(grid);
		assertCommand(rover, "MMMM", "O:0:2:N");
	}

	private static void assertCommand(Rover rover, String command, String expectedStatus) {
		rover.execute(command);
		assertEquals(expectedStatus, rover.statusReport());
	}
}