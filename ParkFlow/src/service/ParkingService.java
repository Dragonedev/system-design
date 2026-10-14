package service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import entities.ParkingLot;
import entities.ParkingSpace;
import entities.ParkingTicket;
import entities.Vehicle;
import exceptions.InvalidParkingOperationException;
import exceptions.NoAvailableSpotException;

public class ParkingService {

	private final ParkingLot parkingLot;
	private final FeeCalculator feeCalculator;
	private final List<ParkingTicket> tickets;

	private long nextTicketId = 1;

	public ParkingService(ParkingLot parkingLot, FeeCalculator feeCalculator) {
		this.parkingLot = parkingLot;
		this.feeCalculator = feeCalculator;
		this.tickets = new ArrayList<>();
	}

	public ParkingTicket registerEntry(Vehicle vehicle) {
		if (vehicle == null) {
			throw new InvalidParkingOperationException("Vehicle cannot be null");
		}

		if (isVehicleParked(vehicle.getPlate())) {
			throw new InvalidParkingOperationException("Vehicle is already parked.");
		}

		ParkingSpace ps = parkingLot.findAvailableSpace();

		if (ps == null) {
			throw new NoAvailableSpotException("No available parking spaces.");
		}

		ps.parkVehicle(vehicle);
		ParkingTicket pt = new ParkingTicket(nextTicketId++, vehicle, ps, LocalDateTime.now());
		tickets.add(pt);
		return pt;
	}

	public ParkingTicket registerExit(String plate) {
		ParkingTicket pt = findActiveTicket(plate);

		if (pt == null) {
			throw new InvalidParkingOperationException("No active ticket for this vehicle.");
		}

		LocalDateTime exitTime = LocalDateTime.now();

		BigDecimal fee = feeCalculator.calculateFee(Duration.between(pt.getEntryTime(), exitTime));

		pt.close(LocalDateTime.now(), fee);
		pt.getSpace().removeVehicle();
		return pt;
	}

	public ParkingTicket findActiveTicket(String plate) {
		for (ParkingTicket pt : tickets) {
			Vehicle vehicle = pt.getVehicle();

			if (vehicle.getPlate().equals(plate) && pt.isActive()) {
				return pt;
			}
		}
		return null;
	}

	public boolean isVehicleParked(String plate) {
		return findActiveTicket(plate) != null;
	}

	public int getOccupancy() {
		return parkingLot.getOccupiedSpaces();
	}

	public List<ParkingTicket> getTickets() {
		return Collections.unmodifiableList(tickets);
	}

}
