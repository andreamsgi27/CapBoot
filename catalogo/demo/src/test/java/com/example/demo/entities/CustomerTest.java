package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setCustomerId(1);
        customer.setActive((byte) 1);
        customer.setCreateDate(new Date());
        customer.setEmail("test@example.com");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setLastUpdate(new Date(System.currentTimeMillis()));
        customer.setAddress(new Address());
        customer.setStore(new Store());
        customer.setPayments(new ArrayList<>());
        customer.setRentals(new ArrayList<>());
    }

    @Test
    void testAddPayment() {
        Payment payment = new Payment();
        customer.addPayment(payment);

        assertEquals(1, customer.getPayments().size());
        assertEquals(customer, payment.getCustomer());
    }

    @Test
    void testRemovePayment() {
        Payment payment = new Payment();
        customer.addPayment(payment);
        customer.removePayment(payment);

        assertTrue(customer.getPayments().isEmpty());
        assertNull(payment.getCustomer());
    }

    @Test
    void testAddRental() {
        Rental rental = new Rental();
        customer.addRental(rental);

        assertEquals(1, customer.getRentals().size());
        assertEquals(customer, rental.getCustomer());
    }

    @Test
    void testRemoveRental() {
        Rental rental = new Rental();
        customer.addRental(rental);
        customer.removeRental(rental);

        assertTrue(customer.getRentals().isEmpty());
        assertNull(rental.getCustomer());
    }
}