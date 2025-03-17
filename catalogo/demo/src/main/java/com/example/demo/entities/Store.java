package com.example.demo.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="store")
@NamedQuery(name="Store.findAll", query="SELECT s FROM Store s")
public class Store implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="store_id", unique=true, nullable=false)
	private byte storeId;

	@Column(name="last_update", nullable=false)
	private Timestamp lastUpdate;

	//bi-directional many-to-one association to Customer
	@OneToMany(mappedBy="store")
	private List<Customer> customers;

	//bi-directional many-to-one association to Inventory
	@OneToMany(mappedBy="store")
	private List<Inventory> inventories;

	//bi-directional many-to-one association to Staff
	@OneToMany(mappedBy="store")
	private List<Staff> staffs;

	//bi-directional many-to-one association to Address
	@ManyToOne
	@JoinColumn(name="address_id", nullable=false)
	private Address address;

	//bi-directional many-to-one association to Staff
	@ManyToOne
	@JoinColumn(name="manager_staff_id", nullable=false)
	private Staff staff;

	public Customer addCustomer(Customer customer) {
		getCustomers().add(customer);
		customer.setStore(this);

		return customer;
	}

	public Customer removeCustomer(Customer customer) {
		getCustomers().remove(customer);
		customer.setStore(null);

		return customer;
	}

	public Inventory addInventory(Inventory inventory) {
		getInventories().add(inventory);
		inventory.setStore(this);

		return inventory;
	}

	public Inventory removeInventory(Inventory inventory) {
		getInventories().remove(inventory);
		inventory.setStore(null);

		return inventory;
	}

	public Staff addStaff(Staff staff) {
		getStaffs().add(staff);
		staff.setStore(this);

		return staff;
	}

	public Staff removeStaff(Staff staff) {
		getStaffs().remove(staff);
		staff.setStore(null);

		return staff;
	}
}