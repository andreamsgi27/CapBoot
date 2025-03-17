package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RentalTest {

    private Rental rental;

    @BeforeEach
    void setUp() {
        rental = new Rental();
        rental.setRentalId(1);
        rental.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        rental.setRentalDate(new Date());
        rental.setReturnDate(new Date());
        rental.setCustomer(new Customer());
        rental.setInventory(new Inventory());
        rental.setStaff(new Staff());
        rental.setPayments(new ArrayList<>());
    }

    @Test
    void testGettersAndSetters() {
        rental.setRentalId(2);
        rental.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        rental.setRentalDate(new Date());
        rental.setReturnDate(new Date());
        Customer customer = new Customer();
        Inventory inventory = new Inventory();
        Staff staff = new Staff();
        rental.setCustomer(customer);
        rental.setInventory(inventory);
        rental.setStaff(staff);

        assertEquals(2, rental.getRentalId());
        assertNotNull(rental.getLastUpdate());
        assertNotNull(rental.getRentalDate());
        assertNotNull(rental.getReturnDate());
        assertEquals(customer, rental.getCustomer());
        assertEquals(inventory, rental.getInventory());
        assertEquals(staff, rental.getStaff());
    }

    @Test
    void testAddPayment() {
        Payment payment = new Payment();
        rental.addPayment(payment);

        assertEquals(1, rental.getPayments().size());
        assertEquals(rental, payment.getRental());
    }

    @Test
    void testRemovePayment() {
        Payment payment = new Payment();
        rental.addPayment(payment);
        rental.removePayment(payment);

        assertTrue(rental.getPayments().isEmpty());
        assertNull(payment.getRental());
    }
}