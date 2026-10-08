package entities;

import java.time.Duration;
import java.time.LocalDateTime;

public class Ticket {

	private boolean active;
	private LocalDateTime checkIn;
	private LocalDateTime checkOut;
	private double fee;

	private Vehicle vehicle;

	public Ticket(Vehicle vehicle) {
		this.vehicle = vehicle;
		this.checkIn = LocalDateTime.now();
		this.active = true;
	}

	public double calculateFee(LocalDateTime checkOut) {
		long minutes = Duration.between(this.checkIn, checkOut).toMinutes();
		long hours = (long) Math.ceil(minutes / 60.0);
		if (hours < 1) {
			hours = 1;
		}

		double amount = 10.00;
		if (hours > 1) {
			amount += (hours - 1) * 5.00;
			
		}
		return amount;
	}

	public void processCheckout(LocalDateTime checkOut) {
		this.fee = calculateFee(checkOut);
		this.checkOut = checkOut;
		this.active = false;
	}

	public double getFee() {
		return fee;
	}

	public LocalDateTime getCheckOut() {
		return checkOut;
	}

	public boolean isActive() {
		return active;
	}


	public Vehicle getVehicle() {
		return vehicle;
	}


	public LocalDateTime getCheckIn() {
		return checkIn;
	}

}