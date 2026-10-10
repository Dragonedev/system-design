package application;

import java.math.BigDecimal;
import java.util.Scanner;

import entities.parking.ParkingLot;
import entities.parking.ParkingTicket;
import entities.vehicle.Car;
import repository.ParkingRepository;
import repository.VehicleRepository;
import service.FeeCalculator;
import service.ParkingService;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		ParkingLot parking = new ParkingLot("Eduardo's Parking", 10);
		ParkingRepository parkingRepository = new ParkingRepository();
		VehicleRepository vehicleRepository = new VehicleRepository();
		FeeCalculator feeCalculator = new FeeCalculator(new BigDecimal("10.0"), new BigDecimal("7.5"));

		ParkingService parkingService = new ParkingService(parking, parkingRepository, vehicleRepository,
				feeCalculator);

		ParkingTicket t1 = parkingService.registerEntry(new Car("PQR-3A21", "911 Carrera", "Porsche"));
		ParkingTicket t2 = parkingService.registerEntry(new Car("LMB-2D54", "Huracán EVO", "Lamborghini"));
		ParkingTicket t3 = parkingService.registerEntry(new Car("HND-4G32", "Civic Type R", "Honda"));
		ParkingTicket t4 = parkingService.registerEntry(new Car("JPL-6H85", "Supra GR", "Toyota"));
		ParkingTicket t5 = parkingService.registerEntry(new Car("MCR-1J47", "M3 Competition", "BMW"));
		ParkingTicket t6 = parkingService.registerEntry(new Car("BGT-2L18", "Ferrari 488 GTB", "Ferrari"));
		ParkingTicket t7 = parkingService.registerEntry(new Car("NVR-5M94", "McLaren 720S", "McLaren"));
		ParkingTicket t8 = parkingService.registerEntry(new Car("XLM-3P51", "911 Turbo S", "Porsche"));
		ParkingTicket t9 = parkingService.registerEntry(new Car("GHT-6T39", "Aventador SVJ", "Lamborghini"));
		ParkingTicket t10 = parkingService.registerEntry(new Car("ZPV-1U84", "C 63 S AMG", "Mercedes-Benz"));
		
		parkingService.registerExit("PQR-3A21");
		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t3);
		parkingService.registerExit("HND-4G32");
		System.out.println(t3);
		

		sc.close();

	}

}
