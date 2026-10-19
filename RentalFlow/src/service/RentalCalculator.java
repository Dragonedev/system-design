
package service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import exception.InvalidRentalException;

public class RentalCalculator {

	public BigDecimal calculateTotalAmount(LocalDate pickupDate, LocalDate expectedReturnDate,
			LocalDate actualReturnDate, BigDecimal dailyRate, BigDecimal fine) {

		if (pickupDate == null || expectedReturnDate == null || actualReturnDate == null) {
			throw new InvalidRentalException("Rental dates cannot be null");
		}

		if (expectedReturnDate.isBefore(pickupDate) || actualReturnDate.isBefore(pickupDate)) {
			throw new InvalidRentalException("Return dates cannot be before pickup date");
		}

		if (dailyRate == null || dailyRate.signum() < 0 || fine == null || fine.signum() < 0) {
			throw new InvalidRentalException("Rates cannot be null or negative");
		}

		long returnDays = Math.max(1, ChronoUnit.DAYS.between(pickupDate, actualReturnDate));

		long expectedDays = Math.max(1, ChronoUnit.DAYS.between(pickupDate, expectedReturnDate));

		long lateDays = Math.max(0, returnDays - expectedDays);

		BigDecimal rentalAmount = dailyRate.multiply(BigDecimal.valueOf(returnDays));

		BigDecimal fineAmount = fine.multiply(BigDecimal.valueOf(lateDays));

		return rentalAmount.add(fineAmount);
	}
}
