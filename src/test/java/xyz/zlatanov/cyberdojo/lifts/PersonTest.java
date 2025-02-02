package xyz.zlatanov.cyberdojo.lifts;

import static org.mockito.Mockito.*;
import static xyz.zlatanov.cyberdojo.lifts.Direction.DOWN;
import static xyz.zlatanov.cyberdojo.lifts.Direction.UP;

import org.junit.jupiter.api.Test;

class PersonTest {

	Lift lift = mock(Lift.class);

	@Test
	void shouldCallLiftUp() {
		var person = new Person(0, 1);
		person.interactWith(lift);
		verify(lift).call(0, UP);
	}

	@Test
	void shouldCallLiftDown() {
		var person = new Person(1, 0);
		person.interactWith(lift);
		verify(lift).call(1, DOWN);
	}

	@Test
	void shouldNotCallTwice() {
		when(lift.status()).thenReturn(Status.of(0, UP));
		var person = new Person(0, 1);
		person.interactWith(lift);
		person.interactWith(lift);
		verify(lift).call(any(), any());
	}

	@Test
	void shouldEnterLift() {
		when(lift.status()).thenReturn(Status.of(0, UP));
		when(lift.capacityReached()).thenReturn(false);

		var person = new Person(0, 1);
		person.interactWith(lift); // call lift
		person.interactWith(lift); // enter lift

		verify(lift, times(1)).enter(1);
	}

	@Test
	void shouldNotCallIfAlreadyAtDesiredFloor() {
		var person = new Person(0, 0);
		person.interactWith(lift);
		verifyNoInteractions(lift);
	}

	@Test
	void shouldCallAgainIfUnableToEnter() {
		when(lift.status()).thenReturn(Status.of(1, UP));
		when(lift.capacityReached()).thenReturn(true);

		var person = new Person(1, 2);
		person.interactWith(lift); // call
		person.interactWith(lift); // fail to enter
		person.interactWith(lift); // call again

		verify(lift, times(2)).call(1, UP);
	}

	@Test
	void shouldExitWhenAtDesiredFloor() {
		when(lift.status()).thenReturn(Status.of(0, UP));
		var person = new Person(0, 1);
		person.interactWith(lift); // call
		person.interactWith(lift); // enter
		when(lift.status()).thenReturn(Status.of(1, UP));

		person.interactWith(lift); // exit

		verify(lift).exit();
	}
}