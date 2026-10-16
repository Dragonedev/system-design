package entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Rental {
	private final int id;
	private final LocalDate pickupDate;
	private final LocalDate expectedReturnDate;
	private LocalDate actualReturnDate;
	private final BigDecimal dailyRate;
	private final Customer customer;
	private final Vehicle vehicle;

	public Rental(int id, LocalDate pickupDate, LocalDate expectedReturnDate, BigDecimal dailyRate, Customer customer,
			Vehicle vehicle) {
		this.id = id;
		this.pickupDate = pickupDate;
		this.expectedReturnDate = expectedReturnDate;
		this.dailyRate = dailyRate;
		this.customer = customer;
		this.vehicle = vehicle;
	}

	
	public void closeLoan(LocalDate actualReturnDate) {
		if (this.actualReturnDate != null) {
			throw new IllegalStateException("This loan has already been closed.");
		}

		if (actualReturnDate.isBefore(pickupDate)) {
			throw new IllegalArgumentException("Return date cannot be before pickup date.");
		}

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

	public BigDecimal getDailyRate() {
		return dailyRate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

}
