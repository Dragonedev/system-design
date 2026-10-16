
package entities;

import java.util.Objects;

import entities.enums.VehicleStatus;

public class Vehicle {

	private final String licensePlate;
	private final String brand;
	private final String model;
	private final Double mileage;
	private VehicleStatus vehicleStatus;

	public Vehicle(String licensePlate, String brand, String model, Double mileage) {
		if (licensePlate == null || licensePlate.isBlank()) {
			throw new IllegalArgumentException("License plate is required.");
		}

		if (mileage == null || mileage < 0) {
			throw new IllegalArgumentException("Mileage cannot be negative.");
		}

		this.licensePlate = licensePlate;
		this.brand = Objects.requireNonNull(brand);
		this.model = Objects.requireNonNull(model);
		this.mileage = mileage;
		this.vehicleStatus = VehicleStatus.AVAILABLE;
	}

	public void updateStatus(VehicleStatus vehicleStatus) {
		this.vehicleStatus = Objects.requireNonNull(vehicleStatus);
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public Double getMileage() {
		return mileage;
	}

	public VehicleStatus getVehicleStatus() {
		return vehicleStatus;
	}
}
