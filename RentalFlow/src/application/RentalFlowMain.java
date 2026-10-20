
package application;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entities.Rental;
import entities.Vehicle;
import service.CustomerService;
import service.RentalCalculator;
import service.RentalService;

public class RentalFlowMain {

	public static void main(String[] args) {

		List<Rental> rentals = new ArrayList<>();
		RentalCalculator calculator = new RentalCalculator();
		CustomerService customerService = new CustomerService();

		RentalService rentalService = new RentalService(calculator, customerService);

		// Register vehicles
		rentalService.registerVehicle(new Vehicle("ABC-798", "Toyota", "Supra", 1000.00, new BigDecimal("2500.00")));

		rentalService.registerVehicle(new Vehicle("CDE-567", "Toyota", "Corolla", 900.00, new BigDecimal("600.00")));

		rentalService
				.registerVehicle(new Vehicle("FGH-123", "Toyota", "Corolla Cross", 1000.00, new BigDecimal("800.00")));

		rentalService.registerVehicle(new Vehicle("IJK-456", "Toyota", "Yaris", 500.00, new BigDecimal("600.00")));

		rentalService.registerVehicle(new Vehicle("MCL-720", "McLaren", "720S", 800.00, new BigDecimal("8500.00")));

		rentalService.registerVehicle(new Vehicle("MCL-765", "McLaren", "765LT", 500.00, new BigDecimal("10000.00")));

		rentalService.registerVehicle(new Vehicle("MCL-750", "McLaren", "750S", 300.00, new BigDecimal("9000.00")));

		rentalService.registerVehicle(new Vehicle("MCL-P1", "McLaren", "P1", 1200.00, new BigDecimal("15000.00")));

		// Register customer
		customerService.registerCustomer("Alexander Windsor", "10000001", "900001");
		customerService.registerCustomer("Sebastian Rockefeller", "10000002", "900002");
		customerService.registerCustomer("Maximilian von Habsburg", "10000003", "900003");
		customerService.registerCustomer("Theodore Montgomery", "10000004", "900004");

		// Register rental
		Rental rental1 = rentalService.registerRental("ABC-798", "10000001", LocalDate.now(),
				LocalDate.parse("2026-10-25"), new BigDecimal("150.00"), new BigDecimal("200.00"));

		Rental rental2 = rentalService.registerRental("MCL-720", "10000001", LocalDate.now(),
				LocalDate.parse("2026-10-25"), new BigDecimal("150.00"), new BigDecimal("200.00"));

		Rental rental3 = rentalService.registerRental("MCL-765", "10000002", LocalDate.now(),
				LocalDate.parse("2026-10-27"), new BigDecimal("150.00"), new BigDecimal("200.00"));

		Rental rental4 = rentalService.registerRental("MCL-750", "10000003", LocalDate.now(),
				LocalDate.parse("2026-10-28"), new BigDecimal("150.00"), new BigDecimal("200.00"));

		Rental rental5 = rentalService.registerRental("MCL-P1", "10000004", LocalDate.now(),
				LocalDate.parse("2026-10-30"), new BigDecimal("150.00"), new BigDecimal("200.00"));

		Rental rental6 = rentalService.registerRental("CDE-567", "10000001", LocalDate.now(),
				LocalDate.parse("2026-10-24"), new BigDecimal("150.00"), new BigDecimal("200.00"));

		rentals.add(rental1);
		rentals.add(rental2);
		rentals.add(rental3);
		rentals.add(rental4);
		rentals.add(rental5);
		rentals.add(rental6);

		System.out.println("=== ACTIVE RENTAL ===");
		for (Rental r : rentals) {
			if (r.isActive()) {
				System.out.println(r);
			}
		}
		// Return vehicle
		rentalService.returnVehicle(rental1.getId(), LocalDate.parse("2026-10-30"));
		rentalService.returnVehicle(rental2.getId(), LocalDate.parse("2026-10-30"));
		System.out.println("\n=== CLOSED RENTAL ===");
		for (Rental r : rentals) {
			if (!r.isActive()) {
				System.out.println(r);
			}
		}
	}
}
