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

	public Lift(Integer capacity) {
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

	public Lift enter(int requestedFloor) {
		floorRequests.add(new FloorRequest(requestedFloor, FLOOR, requestedFloor > floor ? UP : DOWN));
		peopleInside++;
		return this;
	}

	public Lift exit() {
		peopleInside--;
		return this;
	}

	private FloorRequest nextStop() {
		return direction == UP
				? nextRequestUp().or(this::nextRequestDown).orElse(defaultFloorRequest())
				: nextRequestDown().or(this::nextRequestUp).orElse(defaultFloorRequest());
	}

	private FloorRequest defaultFloorRequest() {
		return floorRequests.isEmpty() ? new FloorRequest(0, FLOOR, UP) : floorRequests.first();
	}

	private Optional<FloorRequest> nextRequestUp() {
		return floorRequests.stream()
				.filter(fr -> fr.direction() == UP && (direction != UP || fr.floor() >= floor))
				.min(naturalOrder());
	}

	private Optional<FloorRequest> nextRequestDown() {
		return floorRequests.stream()
				.filter(fr -> fr.direction() == DOWN && (direction != DOWN || fr.floor() <= floor))
				.max(naturalOrder());
	}

	private void move(FloorRequest floorRequest) {
		floor = floorRequest.floor();
		direction = floorRequest.direction();
		removeRequest(floorRequest);

		if (floorRequest.type() == FLOOR && shouldChangeDirection()) {
			direction = direction.reverse();
			if (nextStop().floor() == floor) {
				removeRequest(nextStop());
			}
		}
	}

	private boolean shouldChangeDirection() {
		var noMoreUpRequests = floorRequests.stream().noneMatch(fr -> fr.direction() == UP && fr.floor() > floor);
		var noMoreDownRequests = floorRequests.stream().noneMatch(fr -> fr.direction() == DOWN && fr.floor() < floor);

		return direction == UP
				? noMoreUpRequests && nextStop().floor() <= floor && floor != 0
				: noMoreDownRequests && nextStop().floor() >= floor;
	}

	private void removeRequest(FloorRequest floorRequest) {
		floorRequests.removeIf(fr -> fr.floor() == floorRequest.floor()
				&& fr.direction() == floorRequest.direction());
	}

	@Override
	public String toString() {
		var next = nextStop();
		var requestsString = floorRequests.stream().map(FloorRequest::toString).collect(Collectors.joining(", "));
		return String.format("Capacity: %d/%d | Status: %d-%s -> %d-%s | Queue: [ %s ]",
				peopleInside, capacity, floor, direction, next.floor(), next.direction(), requestsString);
	}
}
