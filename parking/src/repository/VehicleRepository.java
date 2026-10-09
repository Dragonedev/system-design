package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import entities.vehicle.Vehicle;

public class VehicleRepository {

	private final List<Vehicle> vehicles = new ArrayList<>();

	public Vehicle save(Vehicle vehicle) {
		vehicles.removeIf(existing -> existing.getPlate().equalsIgnoreCase(vehicle.getPlate()));
		vehicles.add(vehicle);
		return vehicle;
	}

	public Optional<Vehicle> findByPlate(String plate) {
		return vehicles.stream().filter(vehicle -> vehicle.getPlate().equalsIgnoreCase(plate)).findFirst();
	}

	public boolean existsByPlate(String plate) {
		return findByPlate(plate).isPresent();
	}

	public List<Vehicle> findAll() {
		return List.copyOf(vehicles);
	}
}