package xyz.zlatanov.cyberdojo.haiku;

public class Line {

	private final Word[] words;

	public Line(String line) {
		var strWords = line.split(" ");
		words = new Word[strWords.length];
		for (int i = 0; i < strWords.length; i++) {
			words[i] = new Word(strWords[i]);
		}
	}

	long syllables() {
		var result = 0L;
		for (Word word : words) {
			result += word.syllables();
		}
		return result;
	}
}
