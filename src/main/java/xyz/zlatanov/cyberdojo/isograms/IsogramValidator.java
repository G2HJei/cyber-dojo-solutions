package xyz.zlatanov.cyberdojo.isograms;

import java.util.HashSet;
import java.util.Set;

public class IsogramValidator {

	private final Set<Character> chars = new HashSet<>();

	public boolean isValid(String input) {
		for (Character c : input.toLowerCase().toCharArray()) {
			if (chars.contains(c)) {
				return false;
			}
			chars.add(c);
		}
		return true;
	}
}
