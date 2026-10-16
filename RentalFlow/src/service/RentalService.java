
package service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entities.Customer;
import entities.Rental;
import entities.Vehicle;
import entities.enums.VehicleStatus;
import exception.InvalidVehicleException;
import exception.UnavailableVehicleException;

public class RentalService {

	private final List<Vehicle> vehicles;
	private final List<Rental> rentals;
	private final RentalCalculator rentalCalculator;

	private int nextRentalId = 1;

	public RentalService(RentalCalculator rentalCalculator) {
		this.vehicles = new ArrayList<>();
		this.rentals = new ArrayList<>();
		this.rentalCalculator = rentalCalculator;
	}

	public void registerVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
	}

	public Rental registerRental(String plate, Customer customer, LocalDate pickupDate, LocalDate expectedReturnDate,
			BigDecimal dailyRate) {
		Vehicle vehicle = findVehicleByPlate(plate);

		if (vehicle == null) {
			throw new InvalidVehicleException("Vehicle not found.");
		}

		if (!verifyAvailability(vehicle)) {
			throw new UnavailableVehicleException("Vehicle unavailable.");
		}

		Rental rental = new Rental(nextRentalId++, pickupDate, expectedReturnDate, dailyRate, customer, vehicle);

		vehicle.updateStatus(VehicleStatus.RENTED);
		rentals.add(rental);

		return rental;
	}

	public BigDecimal returnVehicle(int rentalId, LocalDate actualReturnDate) {
		Rental rental = findRentalById(rentalId);

		if (!rental.isActive()) {
			throw new IllegalStateException("Rental has already been closed.");
		}

		rental.closeLoan(actualReturnDate);
		rental.getVehicle().updateStatus(VehicleStatus.AVAILABLE);

		return rentalCalculator.calculateTotalAmount(rental.getPickupDate(), rental.getActualReturnDate(),
				rental.getDailyRate());
	}

	public Vehicle findVehicleByPlate(String plate) {
		for (Vehicle vehicle : vehicles) {
			if (vehicle.getLicensePlate().equalsIgnoreCase(plate)) {
				return vehicle;
			}
		}

		return null;
	}

	public Rental findRentalById(int rentalId) {
		for (Rental rental : rentals) {
			if (rental.getId() == rentalId) {
				return rental;
			}
		}

		throw new IllegalArgumentException("Rental not found.");
	}

	public boolean verifyAvailability(Vehicle vehicle) {
		return vehicle.getVehicleStatus() == VehicleStatus.AVAILABLE;
	}

	public List<Vehicle> getVehicles() {
		return List.copyOf(vehicles);
	}

	public List<Rental> getRentals() {
		return List.copyOf(rentals);
	}
}
