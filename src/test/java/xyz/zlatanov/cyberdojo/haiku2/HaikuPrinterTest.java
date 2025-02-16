package xyz.zlatanov.cyberdojo.haiku2;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

class HaikuPrinterTest {

	String			validHaikuText		= "computer programs/the bugs do eat my code/i must not let them";
	String			invalidHaikuText	= "developers/developers/developers";

	PrinterOutput	output				= mock(PrinterOutput.class);
	HaikuPrinter	printer				= new HaikuPrinter(output);

	@Test
	void shouldPrintSingleHaiku() {
		printer.process(validHaikuText);
		verify(output, times(1)).write("5,7,5,Yes");
	}

	@Test
	void shouldPrintMultipleHaikus() {
		printer.process(validHaikuText + "\n" + invalidHaikuText);
		verify(output, times(1)).write("""
				5,7,5,Yes
				4,4,4,No""");
	}
}