package xyz.zlatanov.cyberdojo.lifts;

import static org.junit.jupiter.api.Assertions.*;
import static xyz.zlatanov.cyberdojo.lifts.Direction.DOWN;
import static xyz.zlatanov.cyberdojo.lifts.Direction.UP;

import org.junit.jupiter.api.Test;

class LiftTest {

	Lift lift = new Lift(2);

	@Test
	void shouldStartAtGroundFloor() {
		assertEquals(new Status(0, UP), lift.status());
	}

	@Test
	void shouldRemainOnGroundFloorIfNowhereToGo() {
		lift.proceed();
		assertEquals(new Status(0, UP), lift.status());
	}

	@Test
	void shouldArriveWhenCalled() {
		lift.call(1, UP)
				.proceed();
		assertEquals(Status.of(1, UP), lift.status());
	}

	@Test
	void shouldReturnToGroundWhenNoRequestsRemain() {
		lift.call(1, UP)
				.proceed()
				.enter(2)
				.proceed()
				.exit()
				.proceed();
		assertEquals(Status.of(0, UP), lift.status());
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
		assertEquals(Status.of(2, DOWN), lift.status());
	}

	@Test
	void shouldKeepGoingUpIfPeopleAreWaiting() {
		lift.call(2, UP)
				.enter(1)
				.proceed()
				.exit()
				.proceed()
				.enter(3)
				.proceed()
				.exit();

		assertEquals(Status.of(3, DOWN), lift.status());
	}

	@Test
	void shouldGoDownAfterNoPeopleRemainingUp() {
		lift.call(1, UP)
				.call(1, DOWN)
				.proceed()
				.enter(2)
				.proceed()
				.proceed();

		assertEquals(Status.of(1, DOWN), lift.status());
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

		assertEquals(Status.of(2, DOWN), lift.status());
	}
}