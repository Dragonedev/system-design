
package entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Rental {

    private final int id;
    private final LocalDate pickupDate;
    private final LocalDate expectedReturnDate;
    private LocalDate actualReturnDate;
    private final BigDecimal dailyRate;
    private final BigDecimal fine;
    private final Customer customer;
    private final Vehicle vehicle;

    public Rental(
            int id,
            LocalDate pickupDate,
            LocalDate expectedReturnDate,
            BigDecimal dailyRate,
            BigDecimal fine,
            Customer customer,
            Vehicle vehicle) {

        this.id = id;
        this.pickupDate = pickupDate;
        this.expectedReturnDate = expectedReturnDate;
        this.dailyRate = dailyRate;
        this.fine = fine;
        this.customer = customer;
        this.vehicle = vehicle;
    }

    public void closeLoan(LocalDate actualReturnDate) {
        if (this.actualReturnDate != null) {
            throw new IllegalStateException(
                    "This loan has already been closed.");
        }

        if (actualReturnDate == null) {
            throw new IllegalArgumentException(
                    "Return date cannot be null.");
        }

        if (actualReturnDate.isBefore(pickupDate)) {
            throw new IllegalArgumentException(
                    "Return date cannot be before pickup date.");
        }

        this.actualReturnDate = actualReturnDate;
    }

    public boolean isActive() {
        return actualReturnDate == null;
    }

    public int getId() {
        return id;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }

    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    public BigDecimal getFine() {
        return fine;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String status = isActive() ? "ACTIVE" : "CLOSED";

        String actualReturn = actualReturnDate == null
                ? "Not returned"
                : actualReturnDate.format(formatter);

        String totalAmount = "Pending";

        if (!isActive()) {
            long rentalDays = Math.max(
                    1,
                    ChronoUnit.DAYS.between(
                            pickupDate, actualReturnDate));

            long expectedDays = Math.max(
                    1,
                    ChronoUnit.DAYS.between(
                            pickupDate, expectedReturnDate));

            long lateDays = Math.max(
                    0, rentalDays - expectedDays);

            BigDecimal rentalAmount = dailyRate.multiply(
                    BigDecimal.valueOf(rentalDays));

            BigDecimal fineAmount = fine.multiply(
                    BigDecimal.valueOf(lateDays));

            totalAmount = rentalAmount.add(fineAmount)
                    .toPlainString();
        }

        return String.format("""
                ══════════════════════════════════════════
                          VEHICLE RENTAL RECEIPT
                ══════════════════════════════════════════
                Rental ID:          %d
                Status:             %s

                CUSTOMER
                Name:               %s
                CPF:                %s
                CNH:                %s

                VEHICLE
                License Plate:      %s
                Brand:              %s
                Model:              %s

                RENTAL DETAILS
                Pickup Date:        %s
                Expected Return:    %s
                Actual Return:      %s
                Daily Rate:         R$ %s
                Total Amount:       %s
                ══════════════════════════════════════════
                """,
                id,
                status,
                customer.getName(),
                customer.getCpf(),
                customer.getCnh(),
                vehicle.getLicensePlate(),
                vehicle.getBrand(),
                vehicle.getModel(),
                pickupDate.format(formatter),
                expectedReturnDate.format(formatter),
                actualReturn,
                dailyRate.toPlainString(),
                totalAmount.equals("Pending")
                        ? totalAmount
                        : "R$ " + totalAmount);
    }
}
