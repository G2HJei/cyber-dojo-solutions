package xyz.zlatanov.cyberdojo.lifts;

import static java.util.Comparator.naturalOrder;
import static xyz.zlatanov.cyberdojo.lifts.Direction.DOWN;
import static xyz.zlatanov.cyberdojo.lifts.Direction.UP;
import static xyz.zlatanov.cyberdojo.lifts.FloorRequest.Type.CALL;
import static xyz.zlatanov.cyberdojo.lifts.FloorRequest.Type.FLOOR;

import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Lift {

	private final int					capacity;
	private final TreeSet<FloorRequest>	floorRequests	= new TreeSet<>();
	private Integer						peopleInside	= 0;
	private Integer						floor			= 0;
	private Direction					direction		= UP;

	public Lift(int capacity) {
		this.capacity = capacity;
	}

	public Lift call(Integer fromFloor, Direction toDirection) {
		floorRequests.add(new FloorRequest(fromFloor, CALL, toDirection));
		return this;
	}

	public Integer floor() {
		return floor;
	}

	public Direction direction() {
		return direction;
	}

	public Lift proceed() {
		move(nextStop());
		return this;
	}

	public boolean capacityReached() {
		return peopleInside >= capacity;
	}

	public Lift enter(Integer requestedFloor) {
		assert !requestedFloor.equals(floor);
		var requestedDirection = requestedFloor > floor ? UP : DOWN;
		floorRequests.add(new FloorRequest(requestedFloor, FLOOR, requestedDirection));
		peopleInside++;
		return this;
	}

	public Lift exit() {
		peopleInside--;
		return this;
	}

	private FloorRequest nextStop() {
		var nextRequestUp = nextRequestUp();
		var nextRequestDown = nextRequestDown();
		return direction == UP ? nextRequestUp.orElse(nextRequestDown.orElse(getDefaultFloorRequest()))
				: nextRequestDown.orElse(nextRequestUp.orElse(getDefaultFloorRequest()));
	}

	private FloorRequest getDefaultFloorRequest() {
		return floorRequests.isEmpty()
				? new FloorRequest(0, FLOOR, UP)
				: floorRequests.first();
	}

	private Optional<FloorRequest> nextRequestUp() {
		if (direction == UP) {
			return floorRequests.stream()
					.filter(fr -> fr.direction() == UP && fr.floor() >= floor)
					.min(naturalOrder());
		} else {
			return floorRequests.stream()
					.filter(fr -> fr.direction() == UP)
					.min(naturalOrder());
		}
	}

	private Optional<FloorRequest> nextRequestDown() {
		if (direction == DOWN) {
			return floorRequests.stream()
					.filter(fr -> fr.direction() == DOWN && fr.floor() <= floor)
					.max(naturalOrder());
		} else {
			return floorRequests.stream()
					.filter(fr -> fr.direction() == DOWN)
					.max(naturalOrder());
		}
	}

	private void move(FloorRequest floorRequest) {
		floor = floorRequest.floor();
		direction = floorRequest.direction();
		dequeue(floorRequest);
		if (floorRequest.type() == CALL) {
			return;
		}
		if (floorRequest.type() == FLOOR && shouldChangeDirection()) {
			direction = direction.reverse();
			if (nextStop().floor() == floor) {
				dequeue(nextStop());
			}
		}
	}

	private boolean shouldChangeDirection() {
		if (direction == UP) {
			return floorRequests.stream()
					.filter(fr -> fr.direction() == UP)
					.noneMatch(fr -> fr.floor() > floor)
					&& nextStop().floor() <= floor
					&& floor != 0;
		} else {
			return floorRequests.stream()
					.filter(fr -> fr.direction() == DOWN)
					.noneMatch(fr -> fr.floor() <= floor)
					&& (nextStop().floor() >= floor);
		}
	}

	private void dequeue(FloorRequest floorRequest) {
		floorRequests.stream()
				.filter(fr -> fr.floor() == floorRequest.floor()
						&& fr.direction() == floorRequest.direction())
				.collect(Collectors.toSet())
				.forEach(floorRequests::remove);
	}

	@Override
	public String toString() {
		var requestsString = floorRequests.stream()
				.map(FloorRequest::toString)
				.collect(Collectors.joining(", "));
		return String.format("Capacity: %s/%s	Status: %s-%s	Queue: [ %s ]",
				peopleInside, capacity, floor, direction, requestsString);
	}
}
