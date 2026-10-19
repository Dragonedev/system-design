
package application;

import java.math.BigDecimal;
import java.time.LocalDate;

import entities.Customer;
import entities.Rental;
import entities.Vehicle;
import service.CustomerService;
import service.RentalCalculator;
import service.RentalService;

public class RentalFlowMain {

	public static void main(String[] args) {

		RentalCalculator calculator = new RentalCalculator();
		CustomerService customerService = new CustomerService();

		RentalService rentalService = new RentalService(calculator, customerService);

		// Register vehicles
		rentalService.registerVehicle(new Vehicle("ABC-798", "Toyota", "Supra", 1000.00));

		rentalService.registerVehicle(new Vehicle("CDE-567", "Toyota", "Corolla", 900.00));

		rentalService.registerVehicle(new Vehicle("FGH-123", "Toyota", "Corolla Cross", 1000.00));

		rentalService.registerVehicle(new Vehicle("IJK-456", "Toyota", "Yaris", 500.00));

		// Register customer
		customerService.registerCustomer(new Customer("Eduardo Dragone", "10074843", "908766"));

		// Register rental
		Rental rental = rentalService.registerRental("ABC-798", "10074843", LocalDate.now(),
				LocalDate.parse("2026-10-25"), new BigDecimal("150.00"), new BigDecimal("200.00"));

		System.out.println("=== ACTIVE RENTAL ===");
		System.out.println(rental);

		// Return vehicle
		rentalService.returnVehicle(rental.getId(), LocalDate.parse("2026-10-30"));

		System.out.println("\n=== CLOSED RENTAL ===");
		System.out.println(rental);
	}
}
