package xyz.zlatanov.cyberdojo.battleshipvalidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ship {

	private final List<int[]> coordinatesList = new ArrayList<>();

	public List<int[]> getCoordinatesList() {
		return coordinatesList;
	}

	public void markCoordinates(int row, int col) {
		coordinatesList.add(new int[] { row, col });
	}

	public ShipType getType() {
		return Arrays.stream(ShipType.values())
				.filter(st -> st.getSize() == coordinatesList.size())
				.findFirst()
				.orElseThrow();
	}
}
