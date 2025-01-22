package xyz.zlatanov.cyberdojo.battleshipvalidator;

import static xyz.zlatanov.cyberdojo.battleshipvalidator.ShipType.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Field {

	private final List<Ship> ships = new ArrayList<>();

	public Field(int[][] setup) {
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				if (setup[row][col] == 1) {
					var ship = findShip(row, col);
					ship.markCoordinates(row, col);
				}
			}
		}
	}

	public boolean isValid() {
		return ships.size() == 10
				&& 1 == countShips(BATTLESHIP)
				&& 2 == countShips(CRUISER)
				&& 3 == countShips(DESTROYER)
				&& 4 == countShips(SUBMARINE)
				&& noCrookedShips()
				&& noDiagonalNeighbouringShips();
	}

	private Ship findShip(int row, int col) {
		return ships.stream()
				.filter(s -> shipIsNextTo(s, row, col))
				.findFirst()
				.orElseGet(this::markNewShip);
	}

	private static boolean shipIsNextTo(Ship s, int row, int col) {
		// check only left and up because right and down will be checked when the other ship is being marked
		return s.getCoordinatesList().stream()
				.anyMatch(coordinates -> Arrays.equals(coordinates, new int[] { row, col - 1 }) // left
						|| Arrays.equals(coordinates, new int[] { row - 1, col })); // up
	}

	private Ship markNewShip() {
		var newShip = new Ship();
		ships.add(newShip);
		return newShip;
	}

	private long countShips(ShipType shipType) {
		return ships.stream()
				.filter(ship -> ship.getType() == shipType)
				.count();
	}

	private static boolean checkDiagonally(int[] coordinates, Ship otherShip) {
		var otherShipCoordinates = otherShip.getCoordinatesList();
		var otherShipStart = otherShipCoordinates.getFirst();
		var otherShipEnd = otherShipCoordinates.getLast();
		// check only upper neighbours because bottom neighbours will be checked themselves against this one as otherShip
		return Arrays.equals(new int[] { coordinates[0] - 1, coordinates[1] - 1 }, otherShipStart)
				|| Arrays.equals(new int[] { coordinates[0] - 1, coordinates[1] - 1 }, otherShipEnd)
				|| Arrays.equals(new int[] { coordinates[0] - 1, coordinates[1] + 1 }, otherShipStart)
				|| Arrays.equals(new int[] { coordinates[0] - 1, coordinates[1] + 1 }, otherShipEnd);
	}

	private boolean noCrookedShips() {
		for (var ship : ships) {
			var shipCoordinates = ship.getCoordinatesList();
			var shipRowIndexCount = shipCoordinates.stream()
					.map(c -> c[0])
					.collect(Collectors.toSet())
					.size();
			var shipColIndexCount = shipCoordinates.stream()
					.map(c -> c[1])
					.collect(Collectors.toSet())
					.size();
			if (shipRowIndexCount > 1 && shipColIndexCount > 1) {
				return false;
			}
		}
		return true;
	}

	private boolean noDiagonalNeighbouringShips() {
		for (var ship : ships) {
			for (var otherShip : ships) {
				if (!ship.equals(otherShip)) {
					var shipCoordinates = ship.getCoordinatesList();
					if (checkDiagonally(shipCoordinates.getFirst(), otherShip)
							|| checkDiagonally(shipCoordinates.getLast(), otherShip)) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
