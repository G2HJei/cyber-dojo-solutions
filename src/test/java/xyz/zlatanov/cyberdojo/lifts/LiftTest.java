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
		lift.call(1, UP);
		lift.proceed();
		assertEquals(Status.of(1, UP), lift.status());
	}

	@Test
	void shouldReturnToGroundWhenNoRequestsRemain() {
		lift.call(1, UP);
		lift.proceed();
		lift.proceed();
		assertEquals(Status.of(0, UP), lift.status());
	}

	@Test
	void shouldReportCapacity() {
		lift.enter(1);
		lift.enter(2);
		assertTrue(lift.capacityReached());
	}

	@Test
	void shouldAccountForExits() {
		lift.enter(1);
		lift.enter(1);
		lift.exit();

		assertFalse(lift.capacityReached());
	}

	@Test
	void shouldProceedToRequestedFloor() {
		lift.call(1, UP);
		lift.proceed();
		lift.enter(2);
		lift.proceed();
		assertEquals(Status.of(2, UP), lift.status());
	}

	@Test
	void shouldKeepGoingUpIfPeopleAreWaiting() {
		lift.call(1, UP);
		lift.call(2, DOWN);
		lift.call(3, UP);

		lift.proceed();
		lift.enter(2);
		lift.proceed();
		lift.proceed();

		assertEquals(Status.of(3, UP), lift.status());
	}

	@Test
	void shouldGoDownAfterNoPeopleRemainingUp() {
		lift.call(1, UP);
		lift.call(1, DOWN);

		lift.proceed();
		lift.enter(2);
		lift.proceed();
		lift.proceed();

		assertEquals(Status.of(1, DOWN), lift.status());
	}
}