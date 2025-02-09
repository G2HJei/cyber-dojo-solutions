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

	@Override
	public String toString() {
		if (currentFloor == desiredFloor) {
			return "ARRIVED at " + desiredFloor;
		}
		if (insideLift) {
			return "INSIDE lift, going to " + desiredFloor;
		}
		if (calledLift) {
			return "CALLED lift at " + currentFloor + ", going to " + desiredFloor;
		}
		return "WAITING lift to de[art at " + currentFloor + ", going to " + desiredFloor;
	}

	public void interactWith(Lift lift) {
		if (insideLift) {
			currentFloor = lift.floor();
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
		var isAtCurrentFloor = lift.floor() == currentFloor;
		var directionIsCorrect = (currentFloor < desiredFloor && lift.direction() == UP)
				|| (currentFloor > desiredFloor && lift.direction() == DOWN);
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
