package service;

import java.time.LocalDateTime;

import entities.Parking;
import entities.Vehicle;

public class EntranceService {

	private Parking parking;

	public EntranceService(Parking parking) {
		this.parking = parking;
	}

	public void vehicleEntrance(Vehicle vehicle) {
		parking.createTicket(vehicle);
	}

	public double estimateFee(String plate, LocalDateTime checkOut) {
		return parking.estimateFee(plate, checkOut);
	}

	public void vehicleExit(String plate) {
		vehicleExit(plate, LocalDateTime.now());
	}

	public void vehicleExit(String plate, LocalDateTime checkOut) {
		parking.checkout(plate, checkOut);
	}

}