package xyz.zlatanov.cyberdojo.haiku2;

import java.util.List;

public class Line {

	private final List<Character>	vowels	= List.of('a', 'e', 'i', 'o', 'u', 'y');
	private final String			text;

	public Line(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}

	public int syllables() {
		var syllableCount = 0;
		for (var c : text.toCharArray()) {
			if (vowels.contains(c)) {
				syllableCount++;
			}
		}
		return syllableCount;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Line otherLine
				&& this.text.equals(otherLine.text);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
