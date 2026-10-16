package entities;

public class ProdutoImportado extends Produto {

	private Double taxa;

	public ProdutoImportado(String nome, Double preco, Double taxa) {
		super(nome, preco);
		this.taxa = taxa;
	}

	public String etiqueta() {
		return super.getNome() + " R$ " + precoTotal() + " (Taxa alfandegária: R$ " + (precoTotal() - super.getPreco())
				+ ")";
	}

	public Double precoTotal() {
		return super.getPreco() + (1 * taxa / 100);
	}

	public Double getTaxa() {
		return taxa;
	}

	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}

}
