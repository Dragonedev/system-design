package entities.vehicle;

import entities.Vehicle;
import entities.enums.VehicleType;

public class Motorcycle extends Vehicle {

	public Motorcycle(String plate, String brand, String model) {
		super(plate, brand, model);
	}

	public VehicleType getType() {
		return VehicleType.MOTORCYLE;
	}

}
