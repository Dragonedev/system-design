package entities;

import exceptions.InvalidParkingOperationException;

public class ParkingSpace {

	private final int id;
	private Vehicle vehicle;

	public ParkingSpace(int id) {
		this.id = id;
	}

	public boolean isAvailable() {
		return vehicle == null;
	}

	public void parkVehicle(Vehicle vehicle) {
		if (!isAvailable()) {
			throw new InvalidParkingOperationException("Parking space is already occupied.");
		}

		if (vehicle == null) {
			throw new InvalidParkingOperationException("Vehicle cannot be null.");
		}

		this.vehicle = vehicle;
	}

	public Vehicle removeVehicle() {
		if (isAvailable()) {
			throw new InvalidParkingOperationException("Parking space is already empty.");
		}

		Vehicle removedVehicle = vehicle;
		return removedVehicle;
	}

	public int getId() {
		return id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

}
