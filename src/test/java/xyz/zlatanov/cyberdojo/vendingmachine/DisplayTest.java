package xyz.zlatanov.cyberdojo.vendingmachine;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import java.time.Duration;

class DisplayTest {

	DisplayConnector	connector	= mock(DisplayConnector.class);
	Display				display		= new Display(connector, Duration.ofMillis(1));

	@Test
	void shouldDisplayPermanentMessage() {
		display.show("show");
		verify(connector, times(1)).display("show");
	}

	@Test
	void shouldFlashMessageTemporarily() {
		display.show("show");
		display.flash("flash");
		var inOrder = inOrder(connector);
		inOrder.verify(connector, times(1)).display("show");
		inOrder.verify(connector, times(1)).display("flash");
		inOrder.verify(connector, times(1)).display("show");
	}
}