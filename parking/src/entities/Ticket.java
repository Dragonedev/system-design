package entities;

import java.time.Duration;
import java.time.LocalDateTime;

public class Ticket {

    private boolean active;
    private LocalDateTime checkIn;
    private Vehicle vehicle;

    public Ticket(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.checkIn = LocalDateTime.now();
        this.active = true;
    }

    public double calculateFee(LocalDateTime checkOut) {
        long minutes = Duration.between(this.checkIn, checkOut).toMinutes();
        long hours = (long) Math.ceil(minutes / 60.0);
        if (hours < 1) {
            hours = 1;
        }

        double amount = 10.00;
        if (hours > 1) {
            amount += (hours - 1) * 5.00;
        }
        return amount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }
}