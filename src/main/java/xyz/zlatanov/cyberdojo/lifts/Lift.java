package xyz.zlatanov.cyberdojo.lifts;

import static xyz.zlatanov.cyberdojo.lifts.Direction.UP;
import static xyz.zlatanov.cyberdojo.lifts.FloorRequest.Type.CALL;

import java.util.HashSet;
import java.util.Set;

public class Lift {

	private final int				capacity;
	private final Set<FloorRequest>	floorRequests	= new HashSet<>();
	private Integer					peopleInside	= 0;
	private Integer					floor			= 0;
	private Direction				direction		= UP;

	public Lift(int capacity) {
		this.capacity = capacity;
	}

	public Lift call(Integer fromFloor, Direction direction) {
		floorRequests.add(new FloorRequest(fromFloor, CALL, direction));
		return this;
	}

	public Integer floor() {
		return floor;
	}

	public Direction direction() {
		return direction;
	}

	public Lift proceed() {
		// var nextRequest = nextRequest();
		return this;
	}

	public boolean capacityReached() {
		return peopleInside >= capacity;
	}

	public Lift enter(Integer requestedFloor) {
		peopleInside++;
		return this;
	}

	public Lift exit() {
		peopleInside--;
		return this;
	}

}
