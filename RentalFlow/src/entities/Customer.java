package entities;

public class Customer {

	private final long id;
	private String name;
	private String cpf;
	private String cnh;

	public Customer(long id, String name, String cpf, String cnh) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.cnh = cnh;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public String getCnh() {
		return cnh;
	}

}
