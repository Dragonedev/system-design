
package service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entities.Customer;
import entities.Rental;
import entities.Vehicle;
import entities.enums.VehicleStatus;
import exception.InvalidCustomerException;
import exception.InvalidRentalException;
import exception.InvalidVehicleException;
import exception.UnavailableVehicleException;

public class RentalService {

	private final List<Vehicle> vehicles;
	private final List<Rental> rentals;
	private final RentalCalculator rentalCalculator;
	private final CustomerService customerService;

	private int nextRentalId = 1;

	public RentalService(RentalCalculator rentalCalculator, CustomerService customerService) {

		this.vehicles = new ArrayList<>();
		this.rentals = new ArrayList<>();
		this.rentalCalculator = rentalCalculator;
		this.customerService = customerService;
	}

	public void registerVehicle(Vehicle vehicle) {
		if (vehicle == null) {
			throw new InvalidVehicleException("Vehicle cannot be null");
		}

		if (findVehicleByPlate(vehicle.getLicensePlate()) != null) {
			throw new InvalidVehicleException("Vehicle with this license plate already exists");
		}

		vehicles.add(vehicle);
	}

	public Rental registerRental(String plate, String cpf, LocalDate pickupDate, LocalDate expectedReturnDate,
			BigDecimal dailyRate, BigDecimal fine) {

		Vehicle vehicle = findVehicleByPlate(plate);

		if (vehicle == null) {
			throw new InvalidVehicleException("Vehicle not found");
		}

		if (!verifyAvailability(vehicle)) {
			throw new UnavailableVehicleException("Vehicle unavailable");
		}

		Customer customer = customerService.findCustomerByCpf(cpf);

		if (customer == null) {
			throw new InvalidCustomerException("Customer not found");
		}

		if (pickupDate == null || expectedReturnDate == null || expectedReturnDate.isBefore(pickupDate)) {
			throw new InvalidRentalException("Invalid rental dates");
		}

		if (dailyRate == null || dailyRate.signum() < 0 || fine == null || fine.signum() < 0) {
			throw new InvalidRentalException("Rates cannot be null or negative");
		}

		Rental rental = new Rental(nextRentalId++, pickupDate, expectedReturnDate, dailyRate, fine, customer, vehicle);

		vehicle.updateStatus(VehicleStatus.RENTED);
		rentals.add(rental);

		return rental;
	}

	public BigDecimal returnVehicle(int rentalId, LocalDate actualReturnDate) {

		Rental rental = findRentalById(rentalId);

		if (!rental.isActive()) {
			throw new InvalidRentalException("Rental has already been closed");
		}

		if (actualReturnDate == null || actualReturnDate.isBefore(rental.getPickupDate())) {
			throw new InvalidRentalException("Invalid return date");
		}

		BigDecimal totalAmount = rentalCalculator.calculateTotalAmount(rental.getPickupDate(),
				rental.getExpectedReturnDate(), actualReturnDate, rental.getDailyRate(), rental.getFine());

		rental.closeLoan(actualReturnDate);
		rental.getVehicle().updateStatus(VehicleStatus.AVAILABLE);

		return totalAmount;
	}

	public Vehicle findVehicleByPlate(String plate) {
		if (plate == null || plate.isBlank()) {
			return null;
		}

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

		throw new InvalidRentalException("Rental not found");
	}

	public boolean verifyAvailability(Vehicle vehicle) {
		return vehicle != null && vehicle.getVehicleStatus() == VehicleStatus.AVAILABLE;
	}

	public List<Vehicle> getVehicles() {
		return List.copyOf(vehicles);
	}

	public List<Rental> getRentals() {
		return List.copyOf(rentals);
	}
}
