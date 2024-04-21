package xyz.zlatanov.cyberdojo.vendingmachine;

import java.math.BigDecimal;

public enum Product {

	COLA(BigDecimal.ONE), //
	CHIPS(BigDecimal.valueOf(0.5)), //
	CANDY(BigDecimal.valueOf(0.65));

	private BigDecimal price;

	Product(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal price() {
		return price;
	}
}
