package service;

import java.util.ArrayList;
import java.util.List;

import entities.Customer;
import exception.InvalidCustomerException;

public class CustomerService {

	private final List<Customer> customers;

	private long nextCustomerId = 1;

	public CustomerService() {
		this.customers = new ArrayList<>();
	}

	public void registerCustomer(String name, String cpf, String cnh) {

		if (name == null || name.isBlank()) {
			throw new InvalidCustomerException("Customer name cannot be blank");
		}

		if (cpf == null || cpf.isBlank()) {
			throw new InvalidCustomerException("Customer CPF cannot be blank");
		}

		if (cnh == null || cnh.isBlank()) {
			throw new InvalidCustomerException("Customer CNH cannot be blank");
		}

		Customer savedCustomer = new Customer(nextCustomerId++, name, cpf, cnh);
		customers.add(savedCustomer);
	}

	public Customer findCustomerByCpf(String cpf) {
		for (Customer c : customers) {
			if (c.getCpf().equals(cpf)) {
				return c;
			}
		}
		return null;
	}
}
