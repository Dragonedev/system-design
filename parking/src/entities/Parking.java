package entities;

import java.util.ArrayList;
import java.util.List;

public class Parking {

    private int capacity;
    private List<Ticket> activeTickets = new ArrayList<>();
    private double invoice = 0.0;

    public Parking(int capacity) {
        this.capacity = capacity;
    }

    public void addInvoice(double amount) {
        this.invoice += amount;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Ticket> getActiveTickets() {
        return activeTickets;
    }

    public double getInvoice() {
        return invoice;
    }
}