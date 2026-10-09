package entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import entities.enums.PaymentStatus;
import exceptions.InvalidParkingOperationException;

public class Payment {

	private final Long id;
	private final ParkingTicket ticket;
	private final BigDecimal amount;
	private PaymentStatus status;
	private LocalDateTime paymentDate;

	public Payment(Long id, ParkingTicket ticket, BigDecimal amount) {
		this.id = id;
		this.ticket = ticket;
		this.amount = amount;

		if (amount.signum() < 0) {
			throw new IllegalArgumentException("Payment amount cannot be negative.");
		}

		this.status = PaymentStatus.PENDING;
	}

	public void confirmPayment() {
		if (status != PaymentStatus.PENDING) {
			throw new InvalidParkingOperationException("Only pending payments can be confirmed.");
		}

		this.status = PaymentStatus.PAID;
		this.paymentDate = LocalDateTime.now();

	}

	public void cancelPayment() {
		if (status != PaymentStatus.PENDING) {
			throw new InvalidParkingOperationException("Only pending payments can be cancelled.");
		}

		this.status = PaymentStatus.CANCELLED;
	}

	public boolean isPaid() {
		return status == PaymentStatus.PAID;
	}

	public Long getId() {
		return id;
	}

	public ParkingTicket getTicket() {
		return ticket;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

}
