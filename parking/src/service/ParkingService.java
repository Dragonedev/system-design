package service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import entities.parking.ParkingLot;
import entities.parking.ParkingSpace;
import entities.parking.ParkingTicket;
import entities.vehicle.Vehicle;
import exceptions.InvalidParkingOperationException;
import repository.ParkingRepository;
import repository.VehicleRepository;

public class ParkingService {

	private final ParkingLot parkingLot;
	private final ParkingRepository parkingRepository;
	private final VehicleRepository vehicleRepository;
	private final FeeCalculator feeCalculator;

	private long nextTicketId = 1;

	public ParkingService(ParkingLot parkingLot, ParkingRepository parkingRepository,
			VehicleRepository vehicleRepository, FeeCalculator feeCalculator) {
		this.parkingLot = parkingLot;
		this.parkingRepository = parkingRepository;
		this.vehicleRepository = vehicleRepository;
		this.feeCalculator = feeCalculator;
	}

	public ParkingTicket registerEntry(Vehicle vehicle) {
		if (vehicle == null) {
			throw new InvalidParkingOperationException("Vehicle cannot be null.");
		}

		if (findActiveTicketByPlateOrNull(vehicle.getPlate()) != null) {
			throw new InvalidParkingOperationException("This vehicle already has an active parking ticket.");
		}

		ParkingSpace space = parkingLot.findAvailableSpace();

		if (space == null) {
			throw new InvalidParkingOperationException("No parking spaces available.");
		}

		vehicleRepository.save(vehicle);

		space.parkVehicle(vehicle);

		ParkingTicket ticket = new ParkingTicket(nextTicketId++, vehicle, space, LocalDateTime.now());

		parkingRepository.save(ticket);

		return ticket;
	}

	public ParkingTicket registerExit(String plate) {
		ParkingTicket ticket = findActiveTicketByPlate(plate);

		LocalDateTime exitTime = LocalDateTime.now();

		BigDecimal fee = feeCalculator.calculate(ticket, exitTime);

		ParkingSpace space = ticket.getSpace();

		if (space.getVehicle() == null || !space.getVehicle().getPlate().equalsIgnoreCase(plate)) {
			throw new InvalidParkingOperationException("The vehicle is not occupying its ticket's parking space.");
		}

		ticket.close(exitTime, fee);
		space.removeVehicle();

		parkingRepository.save(ticket);

		return ticket;
	}

	public ParkingTicket findActiveTicketByPlate(String plate) {
		if (plate == null || plate.isBlank()) {
			throw new InvalidParkingOperationException("Vehicle plate cannot be empty.");
		}

		return parkingRepository.findActiveByPlate(plate).orElseThrow(
				() -> new InvalidParkingOperationException("No active parking ticket found for plate: " + plate));
	}

	private ParkingTicket findActiveTicketByPlateOrNull(String plate) {
		if (plate == null || plate.isBlank()) {
			throw new InvalidParkingOperationException("Vehicle plate cannot be empty.");
		}

		return parkingRepository.findActiveByPlate(plate).orElse(null);
	}

	public double getOccupancyRate() {
		int capacity = parkingLot.getSpaces().size();

		if (capacity == 0) {
			return 0.0;
		}

		int available = parkingLot.getAvailableSpaceCount();

		return (double) (capacity - available) / capacity;
	}

	public int getAvailableSpaces() {
		return parkingLot.getAvailableSpaceCount();
	}
}
