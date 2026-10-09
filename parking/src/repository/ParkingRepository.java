package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import entities.parking.ParkingTicket;

public class ParkingRepository {

	private final List<ParkingTicket> tickets = new ArrayList<>();

	public ParkingTicket save(ParkingTicket ticket) {
		tickets.removeIf(existing -> existing.getId().equals(ticket.getId()));
		tickets.add(ticket);
		return ticket;
	}

	public Optional<ParkingTicket> findById(Long id) {
		return tickets.stream().filter(ticket -> ticket.getId().equals(id)).findFirst();
	}

	public Optional<ParkingTicket> findActiveByPlate(String plate) {
		return tickets.stream().filter(ticket -> ticket.isOpen())
				.filter(ticket -> ticket.getVehicle().getPlate().equalsIgnoreCase(plate)).findFirst();
	}

	public List<ParkingTicket> findAll() {
		return List.copyOf(tickets);
	}

	public List<ParkingTicket> findActiveTickets() {
		return tickets.stream().filter(ParkingTicket::isOpen).toList();
	}
}