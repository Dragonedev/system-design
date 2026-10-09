package entities.parking;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import entities.vehicle.Vehicle;
import exceptions.InvalidParkingOperationException;

public class ParkingTicket {
	private final Long id;
	private final Vehicle vehicle;
	private final ParkingSpace space;
	private final LocalDateTime entryTime;
	private LocalDateTime exitTime;
	private BigDecimal fee;

	public ParkingTicket(Long id, Vehicle vehicle, ParkingSpace space, LocalDateTime entryTime) {
		this.id = id;
		this.vehicle = vehicle;
		this.space = space;
		this.entryTime = entryTime;
	}

	public void close(LocalDateTime exitTime, BigDecimal fee) {
		if (!isOpen()) {
			throw new InvalidParkingOperationException("Parking ticket is already closed.");
		}

		if (exitTime == null || fee == null || fee.signum() < 0) {
			throw new InvalidParkingOperationException("Invalid exit time or fee");
		}

		if (exitTime.isBefore(entryTime)) {
			throw new InvalidParkingOperationException("Exit time cannot be before entry time");
		}

		this.exitTime = exitTime;
		this.fee = fee;
	}

	public boolean isOpen() {
		return exitTime == null;
	}

	public Duration getDuration() {
		LocalDateTime endTime = isOpen() ? LocalDateTime.now() : exitTime;

		return Duration.between(entryTime, endTime);
	}

	public Long getId() {
		return id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public ParkingSpace getSpace() {
		return space;
	}

	public LocalDateTime getEntryTime() {
		return entryTime;
	}

	public LocalDateTime getExitTime() {
		return exitTime;
	}

	public BigDecimal getFee() {
		return fee;
	}

}
