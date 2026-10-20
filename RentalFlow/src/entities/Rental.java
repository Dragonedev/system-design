
package entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Rental {

	private final int id;
	private final LocalDate pickupDate;
	private final LocalDate expectedReturnDate;
	private LocalDate actualReturnDate;
	private final Customer customer;
	private final Vehicle vehicle;
	private BigDecimal fineAmount;
	private BigDecimal totalAmount;

	public Rental(int id, LocalDate pickupDate, LocalDate expectedReturnDate, BigDecimal dailyRate, BigDecimal fine,
			Customer customer, Vehicle vehicle) {

		this.id = id;
		this.pickupDate = pickupDate;
		this.expectedReturnDate = expectedReturnDate;
		this.customer = customer;
		this.vehicle = vehicle;
	}

	public void closeLoan(LocalDate actualReturnDate, BigDecimal totalAmount, BigDecimal fineAmount) {
		if (this.actualReturnDate != null) {
			throw new IllegalStateException("This loan has already been closed.");
		}

		if (actualReturnDate == null) {
			throw new IllegalArgumentException("Return date cannot be null.");
		}

		if (actualReturnDate.isBefore(pickupDate)) {
			throw new IllegalArgumentException("Return date cannot be before pickup date.");
		}

		this.fineAmount = fineAmount;
		this.totalAmount = totalAmount;
		this.actualReturnDate = actualReturnDate;
	}

	public boolean isActive() {
		return actualReturnDate == null;
	}

	public int getId() {
		return id;
	}

	public LocalDate getPickupDate() {
		return pickupDate;
	}

	public LocalDate getExpectedReturnDate() {
		return expectedReturnDate;
	}

	public LocalDate getActualReturnDate() {
		return actualReturnDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public BigDecimal getFineAmount() {
		return fineAmount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		String status = isActive() ? "ACTIVE" : "CLOSED";

		String actualReturn = actualReturnDate == null ? "Not returned" : actualReturnDate.format(formatter);

		String fine = fineAmount == null ? "Pending" : "R$ " + fineAmount.toPlainString();

		String total = totalAmount == null ? "Pending" : "R$ " + totalAmount.toPlainString();

		return String.format("""
				══════════════════════════════════════════
				          VEHICLE RENTAL RECEIPT
				══════════════════════════════════════════
				Rental ID:          %d
				Status:             %s

				CUSTOMER
				Name:               %s
				CPF:                %s
				CNH:                %s

				VEHICLE
				License Plate:      %s
				Brand:              %s
				Model:              %s

				RENTAL DETAILS
				Pickup Date:        %s
				Expected Return:    %s
				Actual Return:      %s
				Daily Rate:         R$ %s
				Fine Amount:        %s
				Total Amount:       %s
				══════════════════════════════════════════
				""", id, status, customer.getName(), customer.getCpf(), customer.getCnh(), vehicle.getLicensePlate(),
				vehicle.getBrand(), vehicle.getModel(), pickupDate.format(formatter),
				expectedReturnDate.format(formatter), actualReturn, vehicle.getDailyRate().toPlainString(), fine,
				total);
	}
}
