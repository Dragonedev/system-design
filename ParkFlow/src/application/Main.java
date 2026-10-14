package application;

import java.math.BigDecimal;

import entities.ParkingLot;
import entities.ParkingTicket;
import entities.Vehicle;
import service.FeeCalculator;
import service.ParkingService;

public class Main {
	public static void main(String[] args) {

		ParkingLot parkingLot = new ParkingLot("Eduardo's Parking", 7);
		FeeCalculator feeCalculator = new FeeCalculator(BigDecimal.valueOf(20.0), BigDecimal.valueOf(8.0));
		ParkingService ps = new ParkingService(parkingLot, feeCalculator);
		
		ps.registerEntry(new Vehicle("BRA-3201", "BMW", "320i"));
		ps.registerEntry(new Vehicle("JET-0001", "Volkswagen", "Jetta GLI"));
		ps.registerEntry(new Vehicle("POR-0718", "Porsche", "718 Cayman"));
		ps.registerEntry(new Vehicle("MCL-0720", "McLaren", "720S"));
		ps.registerEntry(new Vehicle("AUD-0001", "Audi", "RS5"));
		ps.registerEntry(new Vehicle("MER-0001", "Mercedes-Benz", "AMG C63"));
		
		ps.registerExit("BRA-3201");
		ps.registerExit("POR-0718");
		
		System.out.println("\n=== TICKETS DO ESTACIONAMENTO ===");

		for (ParkingTicket ticket : ps.getTickets()) {
		    System.out.println(ticket);
		    System.out.println("---------------------------------");
		}
		// Registrar entradas e saídas.
		// Exibir os tickets e os resultados.
	}
}
