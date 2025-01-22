package xyz.zlatanov.cyberdojo.wholikesit;

public class LikesFormatter {

	public String label(String[] names) {
		return switch (names.length) {
			case 0 -> "no one likes this";
			case 1 -> names[0] + " likes this";
			case 2 -> String.format("%s and %s like this", names[0], names[1]);
			case 3 -> String.format("%s, %s and %s like this", names[0], names[1], names[2]);
			default -> String.format("%s, %s and %d others like this", names[0], names[1], names.length - 2);
		};
	}
}
