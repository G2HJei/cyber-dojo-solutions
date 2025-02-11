package xyz.zlatanov.cyberdojo.lifts;

import static org.junit.jupiter.api.Assertions.*;
import static xyz.zlatanov.cyberdojo.lifts.Direction.DOWN;
import static xyz.zlatanov.cyberdojo.lifts.Direction.UP;

import org.junit.jupiter.api.Test;

class LiftTest {

	Lift lift = new Lift(2);

	@Test
	void shouldStartAtGroundFloor() {
		assertStatus(0, UP);
	}

	@Test
	void shouldRemainOnGroundFloorIfNowhereToGo() {
		lift.proceed();
		assertStatus(0, UP);
	}

	@Test
	void shouldArriveWhenCalled() {
		lift.call(1, UP)
				.proceed();
		assertStatus(1, UP);
	}

	@Test
	void shouldReturnToGroundWhenNoRequestsRemain() {
		lift.call(1, UP)
				.proceed()
				.enter(2)
				.proceed()
				.exit()
				.proceed();
		assertStatus(0, UP);
	}

	@Test

	void shouldReportCapacity() {
		lift.enter(1)
				.enter(2);
		assertTrue(lift.capacityReached());
	}

	@Test
	void shouldAccountForExits() {
		lift.enter(1)
				.enter(1)
				.exit();

		assertFalse(lift.capacityReached());
	}

	@Test
	void shouldProceedToRequestedFloor() {
		lift.call(1, UP)
				.proceed()
				.enter(2)
				.proceed();
		assertStatus(2, DOWN);
	}

	@Test
	void shouldKeepGoingUpIfPeopleAreWaiting() {
		lift.enter(1)
				.call(2, UP)
				.proceed()
				.exit()
				.proceed()
				.enter(3)
				.proceed()
				.exit();

		assertStatus(3, DOWN);
	}

	@Test
	void shouldGoDownAfterNoPeopleRemainingUp() {
		lift.call(1, UP)
				.call(1, DOWN)
				.proceed()
				.enter(2)
				.proceed()
				.proceed();

		assertStatus(1, DOWN);
	}

	@Test
	void shouldReverseDirectionAfterTopIsReached() {
		lift.call(1, UP)
				.call(1, UP)
				.call(2, UP)
				.call(2, DOWN)
				.proceed() // 1, up
				.enter(3)
				.enter(3)
				.proceed()// 2,up
				// cant enter due to capacity
				.proceed()// 3,up
				.call(2, UP) // repeat call after not being able to enter
				.exit()
				.exit()
				.proceed();

		assertStatus(2, DOWN);
	}

	private void assertStatus(Integer floor, Direction direction) {
		var message = String.format("Incorrect lift status! Expected %s-%s, but was %s-%s", floor, direction, lift.floor(),
				lift.direction());
		assertEquals(floor, lift.floor(), message);
		assertEquals(direction, lift.direction(), message);
	}
}