package xyz.zlatanov.cyberdojo.haiku;

public class Haiku {

	private final Line[] lines = new Line[3];

	public Haiku(String text) {
		var strLines = text.split("/");
		for (int i = 0; i < strLines.length; i++) {
			lines[i] = new Line(strLines[i]);
		}
	}

	public boolean isValid() {
		return lines[0].syllables() == 5
				&& lines[1].syllables() == 7
				&& lines[2].syllables() == 5;
	}

	public long[] syllables() {
		return new long[] {
				lines[0].syllables(),
				lines[1].syllables(),
				lines[2].syllables() };
	}
}
