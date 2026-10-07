package service;

import java.util.ArrayList;
import java.util.List;

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
		Vehicle vehicle = null;
		boolean found = false;
		for (Vehicle v : vehicles) {
			if (v.getPlate().equals(plate)) {
				found = true;
				vehicle = v;
				if (vehicle.getTicket().getPayment().getPay()) {
					vehicle.getTicket().setActive(false);
					System.out.println("Liberado");
					vehicles.remove(vehicle);
				} else {
					System.out.println("Pagamento pendente");
				}

			}

		}
		if (!found) {
			System.out.println("Placa não encontrada.");
		}
	}
}
