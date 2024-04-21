package xyz.zlatanov.cyberdojo.vendingmachine;

import static java.math.BigDecimal.ZERO;
import static xyz.zlatanov.cyberdojo.vendingmachine.Coin.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

	public static final String	EXACT_CHANGE_ONLY	= "EXACT CHANGE ONLY";
	public static final String	INSERT_COIN			= "INSERT COIN";
	public static final String	THANK_YOU			= "THANK YOU";
	public static final String	SOLD_OUT			= "SOLD OUT";
	public static final String	CANT_MAKE_CHANGE	= "CAN'T MAKE CHANGE";

	private final Display		display;
	private final CoinReturn	coinReturn;
	private final Dispenser		dispenser;
	private final List<Coin>	coinStorage			= new ArrayList<>();
	private final List<Product>	stock				= new ArrayList<>();
	private int					insertedCount		= 0;

	public VendingMachine(Display display, CoinReturn coinReturn, Dispenser dispenser) {
		this.display = display;
		this.coinReturn = coinReturn;
		this.dispenser = dispenser;
		showWelcomeMessage();
	}

	public VendingMachine addStock(Product product) {
		stock.add(product);
		return this;
	}

	public VendingMachine insert(Coin coin) {
		if (List.of(QUARTER, DIME, NICKEL).contains(coin)) {
			insertedCount++;
			coinStorage.addFirst(coin);
			display.show(String.valueOf(currentAmount()));
		} else {
			coinReturn.add(coin);
		}
		return this;
	}

	public void select(Product product) {
		if (!stock.contains(product)) {
			display.flash(SOLD_OUT);
		} else if (currentAmount().compareTo(product.price()) < 0) {
			display.flash(product.name() + " " + product.price());
		} else {
			if (makeChange(product)) {
				dispenseProduct(product);
			} else {
				display.flash(CANT_MAKE_CHANGE);
			}
		}
	}

	public void returnCoins() {
		var coinsToReturn = new Coin[insertedCount];
		for (int i = 0; i < insertedCount; i++) {
			coinsToReturn[i] = coinStorage.get(i);
		}
		for (Coin coin : coinsToReturn) {
			returnCoins(1, coin);
		}
		insertedCount = 0;
		showWelcomeMessage();
	}

	private void showWelcomeMessage() {
		if (coinStorage.contains(NICKEL) || coinStorage.contains(DIME)) {
			display.show(INSERT_COIN);
		} else {
			display.show(EXACT_CHANGE_ONLY);
		}
	}

	private BigDecimal currentAmount() {
		var result = ZERO;
		for (int i = 0; i < insertedCount; i++) {
			result = result.add(coinStorage.get(i).value());
		}
		return result;
	}

	private boolean makeChange(Product product) {
		var changeAmount = currentAmount().subtract(product.price());
		var quarters = countStored(QUARTER);
		var nickels = countStored(NICKEL);
		var dimes = countStored(DIME);
		for (int q = quarters; q >= 0; q--) {
			for (int d = dimes; d >= 0; d--) {
				for (int n = nickels; n >= 0; n--) {
					if (0 == changeAmount.compareTo(
							QUARTER.value().multiply(BigDecimal.valueOf(q))
									.add(DIME.value().multiply(BigDecimal.valueOf(d)))
									.add(NICKEL.value().multiply(BigDecimal.valueOf(n))))) {
						returnCoins(q, QUARTER);
						returnCoins(d, DIME);
						returnCoins(n, NICKEL);
						return true;
					}
				}
			}
		}
		return false;
	}

	private int countStored(Coin coin) {
		return (int) coinStorage.stream()
				.filter(coin::equals)
				.count();
	}

	private void dispenseProduct(Product product) {
		stock.remove(product);
		dispenser.add(product);
		insertedCount = 0;
		display.flash(THANK_YOU);
		showWelcomeMessage();
	}

	private void returnCoins(int quantity, Coin coinType) {
		for (int i = 0; i < quantity; i++) {
			coinStorage.remove(coinType);
			coinReturn.add(coinType);
		}
	}
}
