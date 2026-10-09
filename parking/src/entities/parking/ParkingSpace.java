package entities.parking;

import entities.vehicle.Vehicle;
import exceptions.InvalidParkingOperationException;

public class ParkingSpace {

	private final int id;
	private Vehicle vehicle;

	public ParkingSpace(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public boolean isAvailable() {
		return vehicle == null;
	}

	public void parkVehicle(Vehicle vehicle) {
		if (vehicle == null) {
			throw new InvalidParkingOperationException(
					"Vehicle cannot be null."
					);
		}
		if (!isAvailable()) {
			throw new InvalidParkingOperationException(
					"Parking space is already occupied."
					);
		}
		
		this.vehicle = vehicle;
	}

	public Vehicle removeVehicle() {
		if (isAvailable()) {
			throw new InvalidParkingOperationException(
					"Parking space is already empty."
					);
		}
		
		Vehicle removedVehicle = this.vehicle;
		this.vehicle = null;

		return removedVehicle;
	}
}
