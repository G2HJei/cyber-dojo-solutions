package xyz.zlatanov.cyberdojo.haiku;

import java.util.List;

public class Word {

	private final String text;

	public Word(String text) {
		this.text = text;
	}

	public long syllables() {
		int syllableCount = 0;
		boolean prevVowel = false;
		for (char c : text.toCharArray()) {
			if (isVowel(c) && !prevVowel) {
				syllableCount++;
				prevVowel = true;
			} else if (!isVowel(c)) {
				prevVowel = false;
			}
		}
		return syllableCount;
	}

	private boolean isVowel(char c) {
		return List.of('a', 'e', 'i', 'o', 'u', 'y')
				.contains(c);
	}
}
