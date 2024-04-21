package xyz.zlatanov.cyberdojo.vendingmachine;

import java.time.Duration;

public class Display {

	private final DisplayConnector	displayConnector;
	private final Duration			flashDelay;
	private String persistentMessage = "";

	public Display(DisplayConnector displayConnector, Duration flashDelay) {
		this.displayConnector = displayConnector;
		this.flashDelay = flashDelay;
	}

	void show(String message) {
		persistentMessage = message;
		displayConnector.display(message);
	}

	public void flash(String message) {
		displayConnector.display(message);
		try {
			Thread.sleep(flashDelay.toMillis());
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} finally {
			displayConnector.display(persistentMessage);
		}
	}
}
