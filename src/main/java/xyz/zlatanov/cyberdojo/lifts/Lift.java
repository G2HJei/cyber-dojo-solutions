package xyz.zlatanov.cyberdojo.lifts;

import static java.util.Comparator.reverseOrder;
import static xyz.zlatanov.cyberdojo.lifts.Direction.DOWN;
import static xyz.zlatanov.cyberdojo.lifts.Direction.UP;
import static xyz.zlatanov.cyberdojo.lifts.FloorRequest.Type.CALL;
import static xyz.zlatanov.cyberdojo.lifts.FloorRequest.Type.FLOOR;

import java.util.TreeSet;
import java.util.stream.Collectors;

public class Lift {

	private final int				capacity;

	private Integer					peopleInside	= 0;
	private Status					status			= Status.of(0, UP);

	private TreeSet<FloorRequest>	upQueue			= new TreeSet<>();
	private TreeSet<FloorRequest>	downQueue		= new TreeSet<>(reverseOrder());

	public Lift(int capacity) {
		this.capacity = capacity;
	}

	public Lift call(Integer callingFloor, Direction requestedDirection) {
		var queue = status.direction() == requestedDirection ? activeQueue() : inactiveQueue();
		queue.add(new FloorRequest(callingFloor, CALL, requestedDirection));
		return this;
	}

	public Status status() {
		return status;
	}

	public Lift proceed() {
		checkReturnToGroundFloor();
		switchActiveQueue();
		var reverseDirection = shouldReverseDirection();
		move(reverseDirection);
		return this;
	}

	private void switchActiveQueue() {
		if (activeQueue().isEmpty() && !inactiveQueue().isEmpty()) {
			status = new Status(status().floor(), status.direction().reverse());
		}
	}

	private void checkReturnToGroundFloor() {
		if (upQueue.isEmpty() && downQueue.isEmpty()) {
			if (status.floor() == 0) {
				status = new Status(0, UP);
			} else {
				downQueue.add(new FloorRequest(0, FLOOR, DOWN));
				status = new Status(status.floor(), DOWN);
			}
		}
	}

	public Lift enter(Integer requestedFloor) {
		peopleInside++;
		activeQueue().add(new FloorRequest(requestedFloor, FLOOR, status.direction()));
		return this;
	}

	public boolean capacityReached() {
		return peopleInside >= capacity;
	}

	public Lift exit() {
		peopleInside--;
		return this;
	}

	private boolean shouldReverseDirection() {
		if (activeQueue().isEmpty()) {
			return false;
		}
		var nextStop = activeQueue().first();
		var peopleToReachDesiredFloor = activeQueue().stream()
				.filter(fr -> fr.floor() == nextStop.floor() && fr.type() == FLOOR)
				.count();
		var projectedAvailableCapacity = capacity - peopleToReachDesiredFloor > 0;
		var calledInSameDirection = activeQueue().stream()
				.anyMatch(fr -> fr.equals(new FloorRequest(nextStop.floor(), CALL, status.direction())));
		var expectedEntrant = projectedAvailableCapacity && calledInSameDirection;
		var secondNextStopKnown = activeQueue().stream()
				.anyMatch(fr -> fr.floor() != activeQueue().first().floor());
		return !expectedEntrant && !secondNextStopKnown;
	}

	private void move(boolean reverseDirection) {
		if (activeQueue().isEmpty() && inactiveQueue().isEmpty()) {
			return;
		}
		var nextRequest = activeQueue().first();
		arriveAt(nextRequest.floor());
		var moveDirection = status.floor() < nextRequest.floor() ? UP : DOWN;
		var nextDirection = reverseDirection ? moveDirection.reverse() : moveDirection;
		status = new Status(nextRequest.floor(), nextDirection);
		if (status.floor() == 0) {
			status = new Status(0, UP);
		}
	}

	private void arriveAt(int floor) {
		var matchedRequests = activeQueue().stream()
				.filter(fr -> fr.floor() == floor)
				.filter(fr -> fr.type() == FLOOR
						|| fr.type() == CALL && fr.direction() == status.direction())
				.collect(Collectors.toSet());
		activeQueue().removeAll(matchedRequests);
	}

	private TreeSet<FloorRequest> activeQueue() {
		if (status.direction() == UP) {
			return upQueue;
		} else {
			return downQueue;
		}
	}

	private TreeSet<FloorRequest> inactiveQueue() {
		if (status.direction() == DOWN) {
			return upQueue;
		} else {
			return downQueue;
		}
	}
}
