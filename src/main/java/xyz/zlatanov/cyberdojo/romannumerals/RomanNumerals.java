package xyz.zlatanov.cyberdojo.romannumerals;

public class RomanNumerals {

	private RomanNumerals() {
	}

	public static String toRomanNumeral(int arabic) {
		return thousandsToRomanNumeral(arabic % 10000 / 1000)
				+ hundretsToRomanNumeral(arabic % 1000 / 100)
				+ tensToRomanNumeral(arabic % 100 / 10)
				+ onesToRomanNumeral(arabic % 10);
	}

	private static String onesToRomanNumeral(int arabic) {
		return switch (arabic) {
			case 1 -> "I";
			case 2 -> "II";
			case 3 -> "III";
			case 4 -> "IV";
			case 5 -> "V";
			case 6 -> "VI";
			case 7 -> "VII";
			case 8 -> "VIII";
			case 9 -> "IX";
			default -> "";
		};
	}

	private static String tensToRomanNumeral(int arabic) {
		return switch (arabic) {
			case 1 -> "X";
			case 2 -> "XX";
			case 3 -> "XXX";
			case 4 -> "XL";
			case 5 -> "L";
			case 6 -> "LX";
			case 7 -> "LXX";
			case 8 -> "LXXX";
			case 9 -> "XC";
			default -> "";
		};
	}

	private static String hundretsToRomanNumeral(int arabic) {
		return switch (arabic) {
			case 1 -> "C";
			case 2 -> "CC";
			case 3 -> "CCC";
			case 4 -> "CD";
			case 5 -> "D";
			case 6 -> "DC";
			case 7 -> "DCC";
			case 8 -> "DCCC";
			case 9 -> "CM";
			default -> "";
		};
	}

	private static String thousandsToRomanNumeral(int arabic) {
		return switch (arabic) {
			case 1 -> "M";
			case 2 -> "MM";
			case 3 -> "MMM";
			case 4 -> "MMMM";
			default -> "";
		};
	}

}
