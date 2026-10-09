package entities.vehicle;

import entities.enums.VehicleType;

public class Car extends Vehicle {

	public Car(String plate, String brand, String model) {
		super(plate, brand, model);
	}

	public VehicleType getType() {
		return VehicleType.CAR;
	}

}
