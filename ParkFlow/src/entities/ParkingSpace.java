package entities;

public class ParkingSpace {

	private int id;
	private Vehicle vehicle;

	ParkingSpace(int id) {
		this.id = id;
	}

	public boolean isAvailable() {
		return vehicle == null;
	}

	public void parkVehicle() {
		//Ocupar vaga caso disponível
	}

	public Vehicle removeVehicle() {
		// Liberar vaga e retornar o veículo
		return null;
	}

	public int getId() {
		return id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

}
