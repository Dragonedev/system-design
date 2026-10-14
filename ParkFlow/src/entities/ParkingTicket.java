package entities;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import entities.enums.TicketStatus;
import exceptions.InvalidParkingOperationException;

public class ParkingTicket {
	private final long id;
	private final Vehicle vehicle;
	private final ParkingSpace space;
	private final LocalDateTime entryTime;

	private LocalDateTime exitTime;
	private BigDecimal fee;
	private TicketStatus status;

	public ParkingTicket(long id, Vehicle vehicle, ParkingSpace space, LocalDateTime entryTime) {
		this.id = id;
		this.vehicle = vehicle;
		this.space = space;
		this.entryTime = entryTime;
	}

	public void close(LocalDateTime exitTime, BigDecimal fee) {
		if (!isActive()) {
			throw new InvalidParkingOperationException("Ticket is already closed.");
		}

		if (exitTime.isBefore(entryTime)) {
			throw new InvalidParkingOperationException("Exit time cannot be before entry time.");
		}

		if (fee.compareTo(BigDecimal.ZERO) < 0) {
			throw new InvalidParkingOperationException("Fee cannot be negative.");
		}

		this.exitTime = exitTime;
		this.fee = fee;
		this.status = TicketStatus.CLOSED;
	}

	public boolean isActive() {
		return status == TicketStatus.ACTIVE;
	}

	public Duration getDuration() {
		LocalDateTime endTime = (exitTime == null) ? LocalDateTime.now() : exitTime;

		return Duration.between(entryTime, endTime);
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

	public TicketStatus getStatus() {
		return status;
	}

	public long getId() {
		return id;
	}

}
