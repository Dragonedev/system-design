package entities;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
		this.status = TicketStatus.ACTIVE;
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

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

		String formattedEntry = entryTime.format(formatter);

		String formattedExit = exitTime != null ? exitTime.format(formatter) : "Not exited";

		Duration duration = getDuration();

		String formattedDuration = String.format("%02dh %02dmin %02ds", duration.toHours(), duration.toMinutesPart(),
				duration.toSecondsPart());

		return String.format("""
				═══════════════ PARKING TICKET ═══════════════
				Ticket ID:  %d
				Vehicle:    %s %s
				Plate:      %s
				Space:      %d
				Entry:      %s
				Exit:       %s
				Duration:   %s
				Fee:        R$ %s
				Status:     %s
				══════════════════════════════════════════════
				""", id, vehicle.getBrand(), vehicle.getModel(), vehicle.getPlate(), space.getId(), formattedEntry,
				formattedExit, formattedDuration, fee != null ? fee.toPlainString() : "Pending", status);
	}

}
