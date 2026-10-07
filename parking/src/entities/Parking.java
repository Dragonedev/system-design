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
    
    public int getOccupiedSpots() {
    	return activeTickets.size();
    }
    
    public int getFreeSpots() {
    	return capacity - activeTickets.size();
    }
    
    public void printStatus() {
        System.out.println("====== STATUS DO ESTACIONAMENTO ======");
        System.out.println("Capacidade Total : " + capacity);
        System.out.println("Vagas Ocupadas   : " + getOccupiedSpots());
        System.out.println("Vagas Livres     : " + getFreeSpots());
        System.out.printf("Faturamento Total : R$ %.1f%n", invoice);
        System.out.println("======================================");
    }
}