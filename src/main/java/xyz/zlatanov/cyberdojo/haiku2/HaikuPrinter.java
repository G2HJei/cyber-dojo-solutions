package xyz.zlatanov.cyberdojo.haiku2;

import java.util.Arrays;
import java.util.stream.Collectors;

public class HaikuPrinter {

	private final PrinterOutput	output;
	private final HaikuParser	parser	= new HaikuParser();

	public HaikuPrinter(PrinterOutput output) {
		this.output = output;
	}

	public void process(String validInput) {
		var haikus = parser.parse(validInput);
		var outputString = Arrays.stream(haikus)
				.map(HaikuPrinter::toOutputString)
				.collect(Collectors.joining("\n"));
		output.write(outputString);
	}

	private static String toOutputString(Haiku haiku) {
		var syllablesString = Arrays.stream(haiku.syllableCount())
				.mapToObj(String::valueOf)
				.collect(Collectors.joining(","));
		var validString = haiku.isValid() ? "Yes" : "No";
		return syllablesString + "," + validString;
	}
}
