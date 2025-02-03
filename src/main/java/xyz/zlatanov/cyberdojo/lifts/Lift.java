package xyz.zlatanov.cyberdojo.lifts;

import static java.util.Comparator.reverseOrder;
import static xyz.zlatanov.cyberdojo.lifts.Direction.DOWN;
import static xyz.zlatanov.cyberdojo.lifts.Direction.UP;

import java.util.TreeSet;

public class Lift {

	private final int				capacity;

	private Integer					peopleInside	= 0;
	private Integer					currentFloor	= 0;
	private Direction				direction		= UP;

	private final TreeSet<Integer>	upQueue			= new TreeSet<>();
	private final TreeSet<Integer>	downQueue		= new TreeSet<>(reverseOrder());

	public Lift(int capacity) {
		this.capacity = capacity;
	}

	public void call(Integer callingFloor, Direction requestedDirection) {
		if (direction == requestedDirection) {
			activeQueue().add(callingFloor);
		} else {
			inactiveQueue().add(callingFloor);
		}
	}

	public Status status() {
		return Status.of(currentFloor, direction);
	}

	public void proceed() {
		checkReturnToGroundFloor();
		checkDirectionReversal();
		move();
	}

	private void checkReturnToGroundFloor() {
		if (upQueue.isEmpty() && downQueue.isEmpty()) {
			if (currentFloor == 0) {
				direction = UP;
			} else {
				downQueue.add(0);
				direction = DOWN;
			}
		}
	}

	public void enter(Integer requestedFloor) {
		peopleInside++;
		activeQueue().add(requestedFloor);
	}

	public boolean capacityReached() {
		return peopleInside >= capacity;
	}

	public void exit() {
		peopleInside--;
	}

	private void checkDirectionReversal() {
		var inactiveIsEmpty = inactiveQueue().isEmpty();
		var activeIsEmpty = activeQueue().isEmpty();
		var canContinueToNextFloor = !activeIsEmpty
				&& ((direction == UP && activeQueue().first() > currentFloor)
						|| (direction == DOWN && activeQueue().first() < currentFloor));
		if (!canContinueToNextFloor && !inactiveIsEmpty) {
			direction = direction.reverse();
		}
	}

	private void move() {
		if (activeQueue().isEmpty() && inactiveQueue().isEmpty()) {
			return;
		}
		currentFloor = activeQueue().first();
		activeQueue().pollFirst();
		if (currentFloor == 0) {
			direction = UP;
		}
	}

	private TreeSet<Integer> activeQueue() {
		if (direction == UP) {
			return upQueue;
		} else {
			return downQueue;
		}
	}

	private TreeSet<Integer> inactiveQueue() {
		if (direction == DOWN) {
			return upQueue;
		} else {
			return downQueue;
		}
	}
}
