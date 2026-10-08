package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import exceptions.ActiveTicketException;
import exceptions.NoAvailableSpotException;

public class Parking {

	private int capacity;
	private double invoice = 0.0;

	private List<Ticket> activeTickets = new ArrayList<>();

	public Parking(int capacity) {
		this.capacity = capacity;
	}

	public void addInvoice(double amount) {
		this.invoice += amount;
	}

	public int getOccupiedSpots() {
		return activeTickets.size();
	}

	public int getFreeSpots() {
		return capacity - activeTickets.size();
	}

	public boolean hasAvaliableSpot() {
		return getFreeSpots() > 0;
	}

	public void printStatus() {
		System.out.println("====== STATUS DO ESTACIONAMENTO ======");
		System.out.println("Capacidade Total : " + capacity);
		System.out.println("Vagas Ocupadas   : " + getOccupiedSpots());
		System.out.println("Vagas Livres     : " + getFreeSpots());
		System.out.printf("Faturamento Total : R$ %.1f%n", invoice);
		System.out.println("======================================");
	}

	public void addTicket(Ticket ticket) {
		activeTickets.add(ticket);
	}

	public void removeTicket(Ticket ticket) {
		activeTickets.remove(ticket);
	}

	public Ticket findActiveTicket(String plate) {
	    for (Ticket ticket : activeTickets) {
	        if (ticket.getVehicle().getPlate().equalsIgnoreCase(plate) && ticket.isActive()) {
	            return ticket;
	        }
	    }

	    return null;
	}

	public Ticket createTicket(Vehicle vehicle) {

	    if (!hasAvaliableSpot()) {
	        throw new NoAvailableSpotException();
	    }

	    if (findActiveTicket(vehicle.getPlate()) != null) {
	        throw new ActiveTicketException();
	    }

	    Ticket ticket = new Ticket(vehicle);
	    addTicket(ticket);

	    return ticket;
	}
	
	public void checkout(String plate, LocalDateTime checkOut) {
		Ticket ticket = findActiveTicket(plate);
		if (ticket == null) {
			return;
		}

		ticket.processCheckout(checkOut);

		addInvoice(ticket.getFee());
		removeTicket(ticket);

	}

	public double estimateFee(String plate, LocalDateTime checkOut) {
		Ticket ticket = findActiveTicket(plate);
		if (ticket == null) {
			return 0.0;
		}
		return ticket.calculateFee(checkOut);
	}
	
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public double getInvoice() {
		return invoice;
	}

}