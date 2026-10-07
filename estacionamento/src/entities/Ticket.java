package entities;

import java.time.Duration;
import java.time.LocalDateTime;

public class Ticket {

	private Boolean active;
	private LocalDateTime time;
	private Vehicle vehicle;

	public Ticket(Vehicle vehicle) {
		this.vehicle = vehicle;
		this.time = LocalDateTime.now();
		this.active = true;
	}
	
	// Dentro da classe Ticket
	public double calcularValor(LocalDateTime horaSaida) {
	    long minutos = Duration.between(this.time, horaSaida).toMinutes();
	    long horas = (long) Math.ceil(minutos / 60.0);
	    if (horas < 1) horas = 1;

	    double valor = 10.00;
	    if (horas > 1) {
	        valor += (horas - 1) * 5.00;
	    }
	    return valor;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	

}
