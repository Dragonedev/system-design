package entities;

import entities.enums.VehicleType;

public abstract class Vehicle {

	private String plate;
	private String brand;
	private String model;

	public Vehicle(String plate, String brand, String model) {
		this.plate = plate;
		this.brand = brand;
		this.model = model;
	}

	public String getPlate() {
		return plate;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public abstract VehicleType getType();

	@Override
	public String toString() {
		return "Vehicle [plate=" + plate + ", brand=" + brand + ", model=" + model + "]";
	}

}