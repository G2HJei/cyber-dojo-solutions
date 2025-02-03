package xyz.zlatanov.cyberdojo.lifts;

import static xyz.zlatanov.cyberdojo.lifts.Direction.DOWN;
import static xyz.zlatanov.cyberdojo.lifts.Direction.UP;

public class Person {

	private int			currentFloor;
	private final int	desiredFloor;
	private boolean		calledLift	= false;
	private boolean		insideLift	= false;

	public Person(int currentFloor, int desiredFloor) {
		this.desiredFloor = desiredFloor;
		this.currentFloor = currentFloor;
	}

	public void interactWith(Lift lift) {
		if (insideLift) {
			currentFloor = lift.status().floor();
			if (reachedDesiredFloor()) {
				exitLift(lift);
			}
		} else if (checkIfLiftHasArrived(lift)) {
			attemptToEnterLift(lift);
		} else if (!reachedDesiredFloor()) {
			callLift(lift);
		}
	}

	public boolean insideLift() {
		return insideLift;
	}

	private boolean checkIfLiftHasArrived(Lift lift) {
		var liftStatus = lift.status();
		var isAtCurrentFloor = liftStatus.floor() == currentFloor;
		var directionIsCorrect = (currentFloor < desiredFloor && liftStatus.direction() == UP)
				|| (currentFloor > desiredFloor && liftStatus.direction() == DOWN);
		return isAtCurrentFloor && directionIsCorrect;
	}

	private void attemptToEnterLift(Lift lift) {
		if (lift.capacityReached()) {
			calledLift = false;
		} else {
			lift.enter(desiredFloor);
			insideLift = true;
		}
	}

	private void exitLift(Lift lift) {
		lift.exit();
		insideLift = false;
		calledLift = false;
	}

	private boolean reachedDesiredFloor() {
		return currentFloor == desiredFloor;
	}

	private void callLift(Lift lift) {
		if (!calledLift) {
			var direction = currentFloor < desiredFloor ? UP : DOWN;
			lift.call(currentFloor, direction);
			calledLift = true;
		}
	}
}
