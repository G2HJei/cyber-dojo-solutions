package xyz.zlatanov.cyberdojo.vendingmachine;

import java.math.BigDecimal;

public enum Coin {

	PENNY(BigDecimal.valueOf(0.01)), //
	NICKEL(BigDecimal.valueOf(0.5)), //
	DIME(BigDecimal.valueOf(0.1)), //
	QUARTER(BigDecimal.valueOf(0.25));

	Coin(BigDecimal value) {
		this.value = value;
	}

	private BigDecimal value;

	public BigDecimal value() {
		return value;
	}
}
