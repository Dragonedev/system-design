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

        // Verificar capacidade máxima
        if (parking.getActiveTickets().size() >= parking.getCapacity()) {
            System.out.println("Capacidade máxima já atingida.");
            return;
        }

        // Verificar duplicidade
        for (Ticket t : parking.getActiveTickets()) {
            if (t.getVehicle().getPlate().equalsIgnoreCase(vehicle.getPlate())) {
                if (t.isActive()) {
                    System.out.println("Ticket ainda ativo.");
                    return;
                }
            }
        }

        // Criar Ticket e adicionar no estacionamento
        Ticket ticket = new Ticket(vehicle);
        parking.getActiveTickets().add(ticket);
        System.out.println("Veículo adicionado ao estacionamento.");
    }

    public void vehicleExit(String plate) {
        Ticket ticket = null;

        // Buscar ticket
        for (Ticket t : parking.getActiveTickets()) {
            if (t.getVehicle().getPlate().equalsIgnoreCase(plate) && t.isActive()) {
                ticket = t;
                break;
            }
        }

        // Caso não encontrado
        if (ticket == null) {
            System.out.println("Veículo não encontrado ou ticket inativo.");
            return;
        }

        // Setar Check-out e calcular valor
        LocalDateTime checkOut = LocalDateTime.now();
        double amount = ticket.calculateFee(checkOut);

        // Adicionar no faturamento, desativar ticket e remover do estacionamento
        parking.addInvoice(amount);
        ticket.setActive(false);
        parking.getActiveTickets().remove(ticket);

        System.out.printf("Saída realizada! Total a pagar: R$ %.2f%n", amount);
    }
}