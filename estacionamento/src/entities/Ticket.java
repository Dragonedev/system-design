package entities;

import java.time.LocalDateTime;

public class Ticket {

	private Boolean active;
	private LocalDateTime time;
	private Vehicle vehicle;

	public Ticket(Vehicle vehicle) {
		this.vehicle = vehicle;
		this.time = LocalDateTime.now();
		this.active = true;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Payment getPayment() {
		return payment;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}
