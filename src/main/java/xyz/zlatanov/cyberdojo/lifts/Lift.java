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
		moveTo(nextStop());
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
		return direction == UP ? nextRequestUp.orElse(nextRequestDown.orElse(new FloorRequest(0, FLOOR, UP)))
				: nextRequestDown.orElse(nextRequestUp.orElse(new FloorRequest(0, FLOOR, UP)));
	}

	private Optional<FloorRequest> nextRequestUp() {
		return floorRequests.stream()
				.filter(fr -> fr.direction() == UP)
				.filter(fr -> direction == DOWN || fr.floor() > floor) // don't go down if already moving up
				.min(naturalOrder());
	}

	private Optional<FloorRequest> nextRequestDown() {
		return floorRequests.stream()
				.filter(fr -> fr.direction() == DOWN)
				.filter(fr -> direction == UP || fr.floor() < floor) 	// don't go up if already moving down
				.max(naturalOrder());
	}

	private void moveTo(FloorRequest nextStop) {
		floor = nextStop.floor();
		direction = floor == 0 ? UP : nextStop.direction();
		floorRequests.stream()
				.filter(fr -> fr.floor() == floor && fr.direction() == direction)
				.collect(Collectors.toSet())
				.forEach(floorRequests::remove);
	}

	@Override
	public String toString() {
		var nextStop = nextStop();
		var requestsString = floorRequests.stream()
				.map(FloorRequest::toString)
				.collect(Collectors.joining(", "));
		return String.format("(%s/%s) %s-%s -> %s-%s     Queue: [ %s ]",
				peopleInside, capacity, floor, direction, nextStop.floor(), nextStop.direction(), requestsString);
	}
}
