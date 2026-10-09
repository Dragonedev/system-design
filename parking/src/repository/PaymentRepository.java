package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import entities.payment.Payment;

public class PaymentRepository {

	private final List<Payment> payments = new ArrayList<>();

	public Payment save(Payment payment) {
		payments.removeIf(existing -> existing.getId().equals(payment.getId()));

		payments.add(payment);

		return payment;
	}

	public Optional<Payment> findById(Long id) {
		return payments.stream().filter(payment -> payment.getId().equals(id)).findFirst();
	}

	public List<Payment> findAll() {
		return List.copyOf(payments);
	}
}