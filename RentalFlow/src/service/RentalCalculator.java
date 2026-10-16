
package service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RentalCalculator {

	public BigDecimal calculateTotalAmount(LocalDate pickupDate, LocalDate returnDate, BigDecimal dailyRate) {
		long days = ChronoUnit.DAYS.between(pickupDate, returnDate);

		if (days <= 0) {
			days = 1;
		}

		return dailyRate.multiply(BigDecimal.valueOf(days));
	}
}
