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

		assertEquals(Status.of(3, DOWN), lift.status());
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

	@Test
	void shouldReverseDirectionAfterTopIsReached() {
		lift.call(1, UP);
		lift.call(1, UP);
		lift.call(2, UP);
		lift.call(2, DOWN);
		lift.proceed(); // 1, up
		lift.enter(3);
		lift.enter(3);
		lift.proceed();// 2,up
		// cant enter due to capacity
		lift.proceed();// 3,up
		lift.call(2, UP); // repeat call after not being able to enter
		lift.exit();
		lift.exit();
		lift.proceed();

		assertEquals(Status.of(2, DOWN), lift.status());
	}
}