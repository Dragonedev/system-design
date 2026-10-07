package entities;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
	private int capacidade;
	private List<Ticket> tickesAtivos = new ArrayList<>();

	public Estacionamento(int capacidade) {
		this.capacidade = capacidade;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public List<Ticket> getTickesAtivos() {
		return tickesAtivos;
	}

	public void setTickesAtivos(List<Ticket> tickesAtivos) {
		this.tickesAtivos = tickesAtivos;
	}


	
	
	
	
	
}
