package entities;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
	private Integer capacidade;
	private List<Ticket> tickesAtivos = new ArrayList<>();
	private Double faturamento;

	public Estacionamento(int capacidade) {
		this.capacidade = capacidade;
	}

	public void adcFaturamento(double amount) {
		faturamento += amount;
	}

	public Integer getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}

	public List<Ticket> getTickesAtivos() {
		return tickesAtivos;
	}

	public void setTickesAtivos(List<Ticket> tickesAtivos) {
		this.tickesAtivos = tickesAtivos;
	}
	
	public Double getFaturamento() {
		return faturamento;
	}

}
