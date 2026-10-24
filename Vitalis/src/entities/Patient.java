package entities;

import entities.enums.SeverityLevel;

public class Patient {

	private String name;
	private int age;
	private String cpf;
	private String protocol;
	private SeverityLevel severityLevel;

	public Patient(String name, int age, String cpf, String protocol) {
		this.name = name;
		this.age = age;
		this.cpf = cpf;
		this.protocol = protocol;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getCpf() {
		return cpf;
	}

	public String getProtocol() {
		return protocol;
	}

	public SeverityLevel getSeverityLevel() {
		return severityLevel;
	}

	public void setSeverityLevel(SeverityLevel severityLevel) {
		this.severityLevel = severityLevel;
	}

	@Override
	public String toString() {
		return "Patient [name=" + name + ", age=" + age + ", cpf=" + cpf + ", protocol=" + protocol + ", severityLevel="
				+ severityLevel + "]";
	}
	
	

}