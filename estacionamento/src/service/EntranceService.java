package service;

import java.time.Duration;
import java.time.LocalDateTime;

import entities.Estacionamento;
import entities.Ticket;
import entities.Vehicle;

public class EntranceService {

	private Estacionamento estacionamento;

	public EntranceService(Estacionamento estacionamento) {
		this.estacionamento = estacionamento;
	}

	public void vehicleEntrance(Vehicle vehicle) {

		// Verificar capacidade máxima
		if (estacionamento.getTickesAtivos().size() >= estacionamento.getCapacidade()) {
			System.out.println("Capacidade máxima já atingida.");
			return;
		}

		// Verificar duplicidade
		for (Ticket t : estacionamento.getTickesAtivos()) {
			if (t.getVehicle().getPlate().equals(vehicle.getPlate())) {
				if (t.isActive()) {
					System.out.println("Ticket ainda ativo");
					return;
				}
			}
		}

		Ticket ticket = new Ticket(vehicle);
		estacionamento.getTickesAtivos().add(ticket);
	}

	public void vehicleExit(String plate) {
		Ticket ticketEncontrado = null;

		// 1. Busca pelo ticket ativo
		for (Ticket t : estacionamento.getTickesAtivos()) {
			if (t.getVehicle().getPlate().equalsIgnoreCase(plate) && t.isActive()) {
				ticketEncontrado = t;
				break; // Parar a busca pois já encontrou
			}
		}

		// 2. Validação se encontrou
		if (ticketEncontrado == null) {
			System.out.println("Veículo não encontrado ou ticket inativo.");
			return;
		}

		// 3. Processar a saída
		LocalDateTime horaSaida = LocalDateTime.now();
		double amount = ticketEncontrado.calcularValor(horaSaida);

		estacionamento.adcFaturamento(amount);
		ticketEncontrado.setActive(false);
		estacionamento.getTickesAtivos().remove(ticketEncontrado);

		System.out.println("Saída realizada! Total a pagar: R$ " + amount);
	}
}
