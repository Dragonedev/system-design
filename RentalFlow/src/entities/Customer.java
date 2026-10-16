package entities;

public class Customer {

	private String name;
	private String cpf;
	private String cnh;
	
	public Customer(String name, String cpf, String cnh) {
		this.name = name;
		this.cpf = cpf;
		this.cnh = cnh;
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
