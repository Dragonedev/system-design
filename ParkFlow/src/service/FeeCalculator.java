package service;

import java.math.BigDecimal;
import java.time.Duration;

public class FeeCalculator {

	private final BigDecimal entryFee;
	private final BigDecimal hourlyRate;

	public FeeCalculator(BigDecimal entryFee, BigDecimal hourlyRate) {
		this.entryFee = entryFee;
		this.hourlyRate = hourlyRate;
	}

	public BigDecimal calculateFee(Duration duration) {
		if (duration == null || duration.isNegative()) {
			throw new IllegalArgumentException("IllegalArgumentException");
		}

		long hours = duration.toHours();

		if (!duration.minusHours(hours).isZero()) {
			hours++;
		}

		BigDecimal parkingFee = hourlyRate.multiply(BigDecimal.valueOf(hours));

		return entryFee.add(parkingFee);
	}

}
