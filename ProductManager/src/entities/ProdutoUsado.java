package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProdutoUsado extends Produto {

	private LocalDate data;
	DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public ProdutoUsado(String nome, Double preco, LocalDate data) {
		super(nome, preco);
		this.data = data;
	}

	public String etiqueta() {
		String dataFormata = data.format(formato);
		return super.etiqueta() + " (Data:" + dataFormata + ")";
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

}
