package com.example.demo.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="rental")
@NamedQuery(name="Rental.findAll", query="SELECT r FROM Rental r")
public class Rental implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rental_id", unique=true, nullable=false)
	private int rentalId;

	@Column(name="last_update", nullable=false)
	private Timestamp lastUpdate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="rental_date", nullable=false)
	private Date rentalDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="return_date")
	private Date returnDate;

	//bi-directional many-to-one association to Payment
	@OneToMany(mappedBy="rental")
	private List<Payment> payments;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customer_id", nullable=false)
	private Customer customer;

	//bi-directional many-to-one association to Inventory
	@ManyToOne
	@JoinColumn(name="inventory_id", nullable=false)
	private Inventory inventory;

	//bi-directional many-to-one association to Staff
	@ManyToOne
	@JoinColumn(name="staff_id", nullable=false)
	private Staff staff;

	public Payment addPayment(Payment payment) {
		getPayments().add(payment);
		payment.setRental(this);

		return payment;
	}

	public Payment removePayment(Payment payment) {
		getPayments().remove(payment);
		payment.setRental(null);

		return payment;
	}
}