package service;

import java.util.Objects;

import entities.parking.ParkingTicket;
import entities.payment.Payment;
import exceptions.InvalidParkingOperationException;
import repository.PaymentRepository;

public class PaymentService {

	private final PaymentRepository paymentRepository;

	private long nextPaymentId = 1;

	public PaymentService(PaymentRepository paymentRepository) {
		this.paymentRepository = Objects.requireNonNull(paymentRepository, "Payment repository cannot be null.");
	}

	public Payment pay(ParkingTicket ticket) {
		Objects.requireNonNull(ticket, "Ticket cannot be null.");

		if (ticket.isOpen() || ticket.getFee() == null) {
			throw new InvalidParkingOperationException("The ticket must be closed before payment.");
		}

		Payment payment = new Payment(nextPaymentId++, ticket, ticket.getFee());

		return paymentRepository.save(payment);
	}

	public Payment confirmPayment(Long paymentId) {
		Payment payment = findPaymentById(paymentId);

		payment.confirmPayment();

		return paymentRepository.save(payment);
	}

	public Payment findPaymentById(Long paymentId) {
		if (paymentId == null) {
			throw new InvalidParkingOperationException("Payment ID cannot be null.");
		}

		return paymentRepository.findById(paymentId)
				.orElseThrow(() -> new InvalidParkingOperationException("Payment not found: " + paymentId));
	}
}