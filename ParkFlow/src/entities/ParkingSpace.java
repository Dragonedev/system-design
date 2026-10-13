package entities;

public class ParkingSpace {

	private int id;
	private Vehicle vehicle;

	ParkingSpace(int id) {
		this.id = id;
	}

	public boolean isAvailable() {
		return vehicle == null;
	}

	public void parkVehicle(Vehicle vehicle) {
		if (isAvailable()) {
			this.vehicle = vehicle;
		}
	}

	public Vehicle removeVehicle() {
		vehicle = null;
		return vehicle;
	}

	public int getId() {
		return id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

}
