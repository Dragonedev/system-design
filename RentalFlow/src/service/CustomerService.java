package service;

import java.util.ArrayList;
import java.util.List;

import entities.Customer;
import exception.InvalidCustomerException;

public class CustomerService {

	private final List<Customer> customers;

	public CustomerService() {
		this.customers = new ArrayList<>();
	}

	public void registerCustomer(Customer customer) {
		if (customer == null) {
			throw new InvalidCustomerException("Customer cannot be null");
		}

		if (customer.getName() == null || customer.getName().isBlank()) {
			throw new InvalidCustomerException("Customer name cannot be blank");
		}

		if (customer.getCpf() == null || customer.getCpf().isBlank()) {
			throw new InvalidCustomerException("Customer CPF cannot be blank");
		}

		if (customer.getCnh() == null || customer.getCnh().isBlank()) {
			throw new InvalidCustomerException("Customer CNH cannot be blank");
		}

		customers.add(customer);
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
