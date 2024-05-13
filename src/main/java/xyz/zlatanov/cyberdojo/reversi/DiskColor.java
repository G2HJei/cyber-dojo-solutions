package xyz.zlatanov.cyberdojo.reversi;

public enum DiskColor {

	W, B;

	public DiskColor reversed() {
		return W == this ? B : W;
	}
}
