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
		checkDirectionReversal();
		move();
		return this;
	}

	private Lift checkReturnToGroundFloor() {
		if (upQueue.isEmpty() && downQueue.isEmpty()) {
			if (status.floor() == 0) {
				status = new Status(0, UP);
			} else {
				downQueue.add(new FloorRequest(0, FLOOR, DOWN));
				status = new Status(status.floor(), DOWN);
			}
		}
		return this;
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

	private void checkDirectionReversal() {
		var nextFloorInActiveQueue = !activeQueue().isEmpty()
				&& ((status.direction() == UP && activeQueue().first().floor() > status.floor())
						|| (status.direction() == DOWN && activeQueue().first().floor() < status.floor()));
		var peopleReachedDesiredFloor = activeQueue().stream().filter(fr -> fr.floor() == status.floor()).count();
		var projectedCapacity = capacity - peopleReachedDesiredFloor;
		var projectedAvailableCapacity = capacity > projectedCapacity;
		var calledInSameDirection = activeQueue().stream()
				.anyMatch(fr -> fr.floor() == status.floor()
						&& fr.direction().equals(status.direction()));
		var expectedEntrant = projectedAvailableCapacity && calledInSameDirection;
		if (!expectedEntrant && !nextFloorInActiveQueue && !inactiveQueue().isEmpty()) {
			status = new Status(status.floor(), status.direction().reverse());
		}
	}

	private void move() {
		if (activeQueue().isEmpty() && inactiveQueue().isEmpty()) {
			return;
		}
		var nextRequest = activeQueue().first();
		arriveAt(nextRequest.floor());
		var direction = status.floor() < nextRequest.floor() ? UP : DOWN;
		status = new Status(nextRequest.floor(), direction);
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
