package entities;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

	private String name;
	private List<ParkingSpace> spaces;

	public ParkingLot(String name, int capacity) {
		this.name = name;
		this.spaces = new ArrayList<>();

		for (int i = 1; i <= capacity; i++) {
			spaces.add(new ParkingSpace(i));
		}
	}

	public String getName() {
		return name;
	}

	public List<ParkingSpace> getSpaces() {
		return List.copyOf(spaces);
	}

	public List<ParkingSpace> getAvailableSpots() {
		return spaces.stream().filter(ParkingSpace::isAvailable).toList();
	}

	public int getAvailableSpaceCount() {
		return (int) spaces.stream().filter(ParkingSpace::isAvailable).count();
	}

	public ParkingSpace findSpaceById(int id) {
		return spaces.stream().filter(space -> space.getId() == id).findFirst().orElse(null);
	}
}
