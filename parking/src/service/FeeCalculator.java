package service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

import entities.parking.ParkingTicket;

public class FeeCalculator {

	private final BigDecimal hourlyRate;
	private final BigDecimal minimumFee;

	public FeeCalculator(BigDecimal hourlyRate, BigDecimal minimumFee) {
		this.hourlyRate = Objects.requireNonNull(hourlyRate, "Hourly rate cannot be null.");

		this.minimumFee = Objects.requireNonNull(minimumFee, "Minimum fee cannot be null.");

		if (hourlyRate.signum() < 0 || minimumFee.signum() < 0) {
			throw new IllegalArgumentException("Rates cannot be negative.");
		}
	}

	public BigDecimal calculate(ParkingTicket ticket, LocalDateTime exitTime) {
		Objects.requireNonNull(ticket, "Ticket cannot be null.");

		Duration duration = calculateDuration(ticket.getEntryTime(), exitTime);

		long seconds = duration.getSeconds();

		if (duration.getNano() > 0) {
			seconds++;
		}

		long billableHours = (seconds + 3599) / 3600;

		BigDecimal calculatedFee = hourlyRate.multiply(BigDecimal.valueOf(billableHours));

		return calculatedFee.max(minimumFee).setScale(2, RoundingMode.HALF_UP);
	}

	public Duration calculateDuration(LocalDateTime entryTime, LocalDateTime exitTime) {
		Objects.requireNonNull(entryTime, "Entry time cannot be null.");
		Objects.requireNonNull(exitTime, "Exit time cannot be null.");

		if (exitTime.isBefore(entryTime)) {
			throw new IllegalArgumentException("Exit time cannot be before entry time.");
		}

		return Duration.between(entryTime, exitTime);
	}
}