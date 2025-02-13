package xyz.zlatanov.cyberdojo.battleshipvalidator;

public enum ShipType {

	BATTLESHIP(4), CRUISER(3), DESTROYER(2), SUBMARINE(1);

	private final int size;

	ShipType(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}
}
