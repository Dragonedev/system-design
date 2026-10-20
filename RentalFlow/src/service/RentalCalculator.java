
package service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import entities.Vehicle;
import exception.InvalidRentalException;

public class RentalCalculator {

	public BigDecimal calculateTotalAmount(LocalDate pickupDate, LocalDate expectedReturnDate,
			LocalDate actualReturnDate, Vehicle vehicle) {

		if (pickupDate == null || expectedReturnDate == null || actualReturnDate == null) {
			throw new InvalidRentalException("Rental dates cannot be null");
		}

		if (expectedReturnDate.isBefore(pickupDate) || actualReturnDate.isBefore(pickupDate)) {
			throw new InvalidRentalException("Return dates cannot be before pickup date");
		}

		if (vehicle == null) {
			throw new InvalidRentalException("vehicle cannot be null");
		}

		if (vehicle.getDailyRate() == null || vehicle.getDailyRate().compareTo(BigDecimal.ZERO) <= 0) {
			throw new InvalidRentalException("vehicle cannot be null or negative");
		}

		long returnDays = Math.max(1, ChronoUnit.DAYS.between(pickupDate, actualReturnDate));
		long expectedDays = Math.max(1, ChronoUnit.DAYS.between(pickupDate, expectedReturnDate));
		BigDecimal rentalAmount = BigDecimal.ZERO;
		if (returnDays > expectedDays) {
			rentalAmount = vehicle.getDailyRate().multiply(BigDecimal.valueOf(expectedDays));
		} else {
			rentalAmount = vehicle.getDailyRate().multiply(BigDecimal.valueOf(returnDays));

		}

		BigDecimal fineAmount = calculateTotalFine(pickupDate, expectedReturnDate, actualReturnDate, vehicle);

		return rentalAmount.add(fineAmount);
	}

	public BigDecimal calculateTotalFine(LocalDate pickupDate, LocalDate expectedReturnDate, LocalDate actualReturnDate,
			Vehicle vehicle) {

		long expectedDays = Math.max(1, ChronoUnit.DAYS.between(pickupDate, expectedReturnDate));

		long returnDays = Math.max(1, ChronoUnit.DAYS.between(pickupDate, actualReturnDate));

		long lateDays = Math.max(0, returnDays - expectedDays);

		BigDecimal dailyFine = vehicle.getDailyRate().multiply(new BigDecimal("1.10"));

		return dailyFine.multiply(BigDecimal.valueOf(lateDays));
	}

}
