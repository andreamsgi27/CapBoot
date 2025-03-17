package com.example.demo.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="staff")
@NamedQuery(name="Staff.findAll", query="SELECT s FROM Staff s")
public class Staff implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="staff_id", unique=true, nullable=false)
	private byte staffId;

	@Column(nullable=false)
	private byte active;

	@Email(message = "El correo electrónico no es válido")
	@Column(length=50)
	private String email;

	@NotBlank(message = "El nombre no puede estar vacío")
	@Size(min = 2, max = 45, message = "El nombre debe tener entre 2 y 45 caracteres")
	@Pattern(regexp = "^[A-Za-z ]+$", message = "El nombre solo puede contener letras y espacios")
	@Column(name="first_name", nullable=false, length=45)
	private String firstName;

	@NotBlank(message = "El apellido no puede estar vacío")
	@Size(min = 2, max = 45, message = "El apellido debe tener entre 2 y 45 caracteres")
	@Pattern(regexp = "^[A-Za-z ]+$", message = "El apellido solo puede contener letras y espacios")
	@Column(name="last_name", nullable=false, length=45)
	private String lastName;

	@PastOrPresent(message = "La fecha de última actualización no puede ser futura")
	@Column(name="last_update", nullable=false)
	private Timestamp lastUpdate;

	@Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
	@Column(length=40)
	private String password;

	@Lob
	private byte[] picture;

	@NotBlank(message = "El nombre de usuario no puede estar vacío")
	@Size(min = 5, max = 16, message = "El nombre de usuario debe tener entre 5 y 16 caracteres")
	@Column(nullable=false, length=16)
	private String username;

	//bi-directional many-to-one association to Payment
	@OneToMany(mappedBy="staff")
	private List<Payment> payments;

	//bi-directional many-to-one association to Rental
	@OneToMany(mappedBy="staff")
	private List<Rental> rentals;

	//bi-directional many-to-one association to Address
	@ManyToOne
	@JoinColumn(name="address_id", nullable=false)
	private Address address;

	//bi-directional many-to-one association to Store
	@ManyToOne
	@JoinColumn(name="store_id", nullable=false)
	private Store store;

	//bi-directional many-to-one association to Store
	@OneToMany(mappedBy="staff")
	private List<Store> stores;

	public Payment addPayment(Payment payment) {
		getPayments().add(payment);
		payment.setStaff(this);

		return payment;
	}

	public Payment removePayment(Payment payment) {
		getPayments().remove(payment);
		payment.setStaff(null);

		return payment;
	}

	public Rental addRental(Rental rental) {
		getRentals().add(rental);
		rental.setStaff(this);

		return rental;
	}

	public Rental removeRental(Rental rental) {
		getRentals().remove(rental);
		rental.setStaff(null);

		return rental;
	}

	public Store addStore(Store store) {
		getStores().add(store);
		store.setStaff(this);

		return store;
	}

	public Store removeStore(Store store) {
		getStores().remove(store);
		store.setStaff(null);

		return store;
	}
}