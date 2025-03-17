package com.example.demo.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="payment")
@NamedQuery(name="Payment.findAll", query="SELECT p FROM Payment p")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="payment_id", unique=true, nullable=false)
	private int paymentId;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal amount;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="payment_date", nullable=false)
	private Date paymentDate;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customer_id", nullable=false)
	private Customer customer;

	//bi-directional many-to-one association to Rental
	@ManyToOne
	@JoinColumn(name="rental_id")
	private Rental rental;

	//bi-directional many-to-one association to Staff
	@ManyToOne
	@JoinColumn(name="staff_id", nullable=false)
	private Staff staff;
}