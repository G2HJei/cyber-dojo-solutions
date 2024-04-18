package xyz.zlatanov.cyberdojo.gameoflife;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GridTest {

	@Test
	void shouldCreateFromString() {
		var input = """
				2 2
				..
				..
				""";
		var grid = new Grid(input);
		assertEquals(input, grid.toString());
	}

	@Test
	void shouldCreateGridOfCells() {
		var input = """
				8 8
				*...*...
				.*...*..
				..*...*.
				..*...*.
				........
				........
				......*.
				........
				""";
		var grid = new Grid(input);
		assertEquals(input, grid.toString());
	}

	@Test
	void shouldKillCellDueToUnderpopulation() {
		assertEvolution("""
				3 3
				...
				.*.
				...
				""",
				"""
						3 3
						...
						...
						...
						""");
	}

	@Test
	void shouldKillCellDueToOvercrowding() {
		assertEvolution("""
				3 3
				***
				***
				***
				""",
				"""
						3 3
						*.*
						...
						*.*
						""");
	}

	@Test
	void shouldKeepCellWith2or3Neighbours() {
		assertEvolution("""
				1 3
				***
				.*.
				""",
				"""
						1 3
						.*.
						""");
	}

	@Test
	void shouldReviveCellWith3Neighbours() {
		assertEvolution("""
				3 3
				.*.
				**.
				...
				""",
				"""
						3 3
						**.
						**.
						...
						""");
	}

	@Test
	void shouldPassExample1() {
		assertEvolution("""
				4 8
				........
				....*...
				...**...
				.....*..
				""",
				"""
						4 8
						........
						...**...
						...***..
						....*...
						""");
	}

	@Test
	void shouldPassExample2() {
		assertEvolution("""
				5 8
				........
				...**...
				.*****..
				........
				........
				""",
				"""
						5 8
						........
						.....*..
						..*..*..
						..***...
						........
						""");
	}

	void assertEvolution(String in, String out) {
		assertEquals(out, new Grid(in).evolve().toString());
	}
}