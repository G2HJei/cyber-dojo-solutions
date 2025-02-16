package xyz.zlatanov.cyberdojo.lifts;

import java.util.ArrayList;
import java.util.List;

public class Building {

	private final Lift			lift;
	private final List<Person>	persons	= new ArrayList<>();

	public Building(Lift lift) {
		this.lift = lift;
	}

	public int[] runLift(int[][] queues) {

		for (int currentFloor = 0; currentFloor < queues.length; currentFloor++) {
			for (int personIndex = 0; personIndex < queues[currentFloor].length; personIndex++) {
				persons.add(new Person(currentFloor, queues[currentFloor][personIndex]));
			}
		}

		var completed = true;
		var liftFloor = lift.floor();
		var liftDirection = lift.direction();
		var liftStops = new ArrayList<Integer>();
		do {
			liftStops.add(liftFloor);
			persons.sort((p1, p2) -> Boolean.compare(p2.insideLift(), p1.insideLift()));
			persons.forEach(p -> p.interactWith(lift));
			lift.proceed();
			completed = liftFloor == lift.floor() && lift.direction() == liftDirection;
			liftFloor = lift.floor();
			liftDirection = lift.direction();
		} while (!completed);

		return liftStops.stream()
				.mapToInt(s -> s)
				.toArray();
	}
}
