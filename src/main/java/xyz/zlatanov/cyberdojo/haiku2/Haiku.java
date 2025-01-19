package xyz.zlatanov.cyberdojo.haiku2;

public class Haiku {

	private static final String	LINE_SEPARATOR	= "/";
	private final Line[]		lines			= new Line[3];

	public Haiku(String inputLine) {
		var lineSplit = inputLine.split(LINE_SEPARATOR);
		for (int i = 0; i < lineSplit.length; i++) {
			var line = lineSplit[i];
			lines[i] = new Line(line);
		}
	}

	public boolean isValid() {
		return lines[0].syllables() == 5
				&& lines[1].syllables() == 7
				&& lines[2].syllables() == 5;
	}

	public int[] syllableCount() {
		return new int[] { lines[0].syllables(), lines[1].syllables(), lines[2].syllables() };
	}

	@Override
	public String toString() {
		return lines[0] + LINE_SEPARATOR + lines[1] + LINE_SEPARATOR + lines[2];
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Haiku otherHaiku
				&& this.lines[0].equals(otherHaiku.lines[0])
				&& this.lines[1].equals(otherHaiku.lines[1])
				&& this.lines[2].equals(otherHaiku.lines[2]);
	}
}
