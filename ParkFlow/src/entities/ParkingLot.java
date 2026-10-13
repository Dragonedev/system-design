package entities;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

	private String name;
	private int capacity;
	private List<ParkingSpace> spaces;

	public ParkingLot(String name, int capacity) {
		this.name = name;
		this.capacity = capacity;
		this.spaces = new ArrayList<>();

		for (int i = 1; i <= capacity; i++) {
			spaces.add(new ParkingSpace(i));
		}
	}

	public ParkingSpace findAvailableSpace() {
		for (ParkingSpace ps : spaces) {
			if (ps.isAvailable()) {
				return ps;
			}
		}
		return null;
	}

	public ParkingSpace findSpaceByVehicle(String plate) {
		for (ParkingSpace ps : spaces) {
			Vehicle vehicle = ps.getVehicle();

			if (vehicle != null && vehicle.getPlate().equals(plate)) {
				return ps;
			}
		}
		return null;
	}

	public boolean isFull() {
		return findAvailableSpace() == null;
	}

	public int getOccupiedSpaces() {
		int count = 0;
		for (ParkingSpace ps : spaces) {
			if (!ps.isAvailable()) {
				count++;
			}
		}
		return count;
	}

	public int getAvailableSpaces() {
		int i = 0;
		for (ParkingSpace ps : spaces) {
			if (ps.isAvailable()) {
				i++;
			}
		}
		return i;
	}

	public int getCapacity() {
		return capacity;
	}

	public String getName() {
		return name;
	}
}
