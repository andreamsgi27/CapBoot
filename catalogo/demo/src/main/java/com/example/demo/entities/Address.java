package com.example.demo.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="address")
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="address_id", unique=true, nullable=false)
	private int addressId;

	@Column(nullable=false, length=50)
	private String address;

	@Column(length=50)
	private String address2;

	@Column(nullable=false, length=20)
	private String district;

	@Column(name="last_update", nullable=false)
	private Timestamp lastUpdate;

	@Column(nullable=false, length=20)
	private String phone;

	@Column(name="postal_code", length=10)
	private String postalCode;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="city_id", nullable=false)
	private City city;

	//bi-directional many-to-one association to Customer
	@OneToMany(mappedBy="address")
	private List<Customer> customers;

	//bi-directional many-to-one association to Staff
	@OneToMany(mappedBy="address")
	private List<Staff> staffs;

	//bi-directional many-to-one association to Store
	@OneToMany(mappedBy="address")
	private List<Store> stores;

	public Customer addCustomer(Customer customer) {
		getCustomers().add(customer);
		customer.setAddress(this);

		return customer;
	}

	public Customer removeCustomer(Customer customer) {
		getCustomers().remove(customer);
		customer.setAddress(null);

		return customer;
	}

	public Staff addStaff(Staff staff) {
		getStaffs().add(staff);
		staff.setAddress(this);

		return staff;
	}

	public Staff removeStaff(Staff staff) {
		getStaffs().remove(staff);
		staff.setAddress(null);

		return staff;
	}

	public Store addStore(Store store) {
		getStores().add(store);
		store.setAddress(this);

		return store;
	}

	public Store removeStore(Store store) {
		getStores().remove(store);
		store.setAddress(null);

		return store;
	}

}