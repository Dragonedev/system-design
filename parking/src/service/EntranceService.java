package service;

import java.time.LocalDateTime;

import entities.Parking;
import entities.Ticket;
import entities.Vehicle;

public class EntranceService {

	private Parking parking;

	public EntranceService(Parking parking) {
		this.parking = parking;
	}

	public void vehicleEntrance(Vehicle vehicle) {
		// Verificar disponibilidade de vagas
		if (parking.getFreeSpots() == 0) {
			System.out.println("Sem vagas disponíveis.");
			return;
		}

		// Verificar duplicidade reaproveitando o método auxiliar
		if (findActiveTicket(vehicle.getPlate()) != null) {
			System.out.println("Ticket ainda ativo.");
			return;
		}

		// Criar Ticket e adicionar no estacionamento
		Ticket ticket = new Ticket(vehicle);
		parking.getActiveTickets().add(ticket);
		System.out.println("Veículo adicionado ao estacionamento.");
	}

	// Apenas consulta o valor estimado sem alterar o estado (Query)
	public double estimateFee(String plate, LocalDateTime checkOut) {
		Ticket ticket = findActiveTicket(plate);
		if (ticket == null) {
			System.out.println("Veículo não encontrado ou ticket inativo.");
			return 0.0;
		}
		return ticket.calculateFee(checkOut);
	}

	// Saída rápida usando o horário atual
	public void vehicleExit(String plate) {
		vehicleExit(plate, LocalDateTime.now());
	}

	// Saída processando o checkout com horário informado (Command)
	public void vehicleExit(String plate, LocalDateTime checkOut) {
		Ticket ticket = findActiveTicket(plate);
		if (ticket == null) {
			System.out.println("Veículo não encontrado ou ticket inativo.");
			return;
		}

		// Delega o encerramento dos dados ao próprio Ticket
		ticket.processCheckout(checkOut);

		// Atualiza faturamento e remove das vagas ativas
		parking.addInvoice(ticket.getFee());
		parking.getActiveTickets().remove(ticket);

		System.out.printf("Saída realizada! Total a pagar: R$ %.2f%n", ticket.getFee());
	}

	// Método auxiliar reutilizado por entrada, consulta e saída
	private Ticket findActiveTicket(String plate) {
		for (Ticket t : parking.getActiveTickets()) {
			if (t.getVehicle().getPlate().equalsIgnoreCase(plate) && t.isActive()) {
				return t;
			}
		}
		return null;
	}
}