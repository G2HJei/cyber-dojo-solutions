package xyz.zlatanov.cyberdojo.vendingmachine;

import static org.mockito.Mockito.*;
import static xyz.zlatanov.cyberdojo.vendingmachine.Coin.*;
import static xyz.zlatanov.cyberdojo.vendingmachine.Product.*;
import static xyz.zlatanov.cyberdojo.vendingmachine.VendingMachine.*;

import org.junit.jupiter.api.Test;

class VendingMachineTest {

	Display			display			= mock(Display.class);
	CoinReturn		coinReturn		= mock(CoinReturn.class);
	Dispenser		dispenser		= mock(Dispenser.class);
	VendingMachine	vendingMachine	= new VendingMachine(display, coinReturn, dispenser);

	@Test
	void shouldStartWithoutChange() {
		verify(display, times(1)).show(EXACT_CHANGE_ONLY);
	}

	@Test
	void shouldAcceptQuartersDimesAndNickels() {
		vendingMachine.insert(QUARTER);
		vendingMachine.insert(DIME);
		vendingMachine.insert(NICKEL);
		verify(coinReturn, never()).add(any());
	}

	@Test
	void shouldNotAcceptPennies() {
		vendingMachine.insert(PENNY);
		verify(coinReturn, times(1)).add(PENNY);
	}

	@Test
	void shouldReturnCoins() {
		vendingMachine.insert(NICKEL)
				.insert(DIME)
				.insert(QUARTER);
		vendingMachine.returnCoins();
		verify(coinReturn, times(1)).add(NICKEL);
		verify(coinReturn, times(1)).add(DIME);
		verify(coinReturn, times(1)).add(QUARTER);
	}

	@Test
	void shouldReturnOnlyInsertedCoins() {
		vendingMachine.addStock(CHIPS);
		vendingMachine.insert(QUARTER).insert(QUARTER);
		vendingMachine.select(CHIPS);
		vendingMachine.insert(QUARTER);
		vendingMachine.returnCoins();
		verify(coinReturn, times(1)).add(QUARTER);
	}

	@Test
	void shouldMakeChangeWithInsertedCoins() {
		vendingMachine.addStock(CHIPS);
		vendingMachine.insert(QUARTER).insert(QUARTER).insert(DIME);
		vendingMachine.select(CHIPS);
		verify(coinReturn, times(1)).add(DIME);
	}

	@Test
	void shouldMakeChangeWithCoinsFromPreviousPurchases() {
		vendingMachine.addStock(CHIPS).addStock(CANDY);
		vendingMachine.insert(DIME).insert(DIME).insert(DIME).insert(DIME).insert(DIME);
		vendingMachine.select(CHIPS);
		vendingMachine.insert(QUARTER).insert(QUARTER).insert(QUARTER);
		vendingMachine.select(CANDY);
		verify(coinReturn, times(1)).add(any());
		verify(coinReturn, times(1)).add(DIME);
	}

	@Test
	void shouldDisplayInsertedAmount() {
		vendingMachine.insert(NICKEL);
		verify(display, times(1)).show(String.valueOf(NICKEL.value()));
	}

	@Test
	void shouldKeepTrackOfInsertedAmount() {
		vendingMachine.insert(NICKEL)
				.insert(DIME);
		var inOrder = inOrder(display);
		inOrder.verify(display).show(NICKEL.value().toString());
		inOrder.verify(display).show(NICKEL.value().add(DIME.value()).toString());
	}

	@Test
	void shouldFlashPriceIfMoreCoinsAreNeeded() {
		vendingMachine.addStock(COLA);
		vendingMachine.select(COLA);
		verify(display, times(1)).flash(COLA.name() + " " + COLA.price());
	}

	@Test
	void shouldDispenseProduct() {
		vendingMachine.addStock(CHIPS);
		vendingMachine.insert(QUARTER).insert(QUARTER);
		vendingMachine.select(CHIPS);
		verify(dispenser, times(1)).add(CHIPS);
	}

	@Test
	void shouldThankAfterPurchase() {
		vendingMachine.addStock(CHIPS);
		vendingMachine.insert(QUARTER).insert(QUARTER);
		vendingMachine.select(CHIPS);
		verify(display, times(1)).flash(THANK_YOU);
	}

	@Test
	void shouldNotOfferSoldOutProducts() {
		vendingMachine.select(CANDY);
		verify(display, times(1)).flash(SOLD_OUT);
	}

	@Test
	void shouldNotSellIfCantMakeChange() {
		vendingMachine.addStock(CANDY);
		vendingMachine.insert(QUARTER).insert(QUARTER).insert(QUARTER);
		vendingMachine.select(CANDY);
		verify(display, times(1)).flash(CANT_MAKE_CHANGE);
	}

	@Test
	void shouldShowInsertCoinAfterProductSelect() {
		vendingMachine.addStock(CHIPS);
		vendingMachine.insert(QUARTER).insert(DIME).insert(DIME).insert(NICKEL);
		vendingMachine.select(CHIPS);
		verify(display, times(1)).show(INSERT_COIN);
	}

	@Test
	void shouldRequireExactChangeWhenCannotMakeChangeAfterPurchase() {
		vendingMachine.addStock(CHIPS);
		vendingMachine.insert(QUARTER).insert(QUARTER);
		vendingMachine.select(CHIPS);
		verify(display, never()).show(INSERT_COIN);
	}

	@Test
	void shouldRequireExactChangeWhenEmptyAfterReturningCoins() {
		vendingMachine.insert(DIME);
		vendingMachine.returnCoins();
		verify(display, times(2)).show(EXACT_CHANGE_ONLY);
	}

	@Test
	void shouldRequireNewCoinsAfterPurchase() {
		vendingMachine.addStock(CHIPS).addStock(CHIPS);
		vendingMachine.insert(QUARTER).insert(QUARTER);
		vendingMachine.select(CHIPS);
		vendingMachine.select(CHIPS);
		verify(display, times(1)).flash(CHIPS.name() + " " + CHIPS.price());
	}
}