package xyz.zlatanov.cyberdojo.haiku;

import java.util.Arrays;
import java.util.stream.Collectors;

public class HaikuValidator {

	public String validate(String in) {
		return Arrays.stream(in.split("\n"))
				.map(this::validateSingle)
				.collect(Collectors.joining("\n"));
	}

	private String validateSingle(String in) {
		var haiku = new Haiku(in);
		var syllables = haiku.syllables();
		var isValid = haiku.isValid() ? "Y" : "N";
		return syllables[0] + "," + syllables[1] + "," + syllables[2] + "," + isValid;
	}
}