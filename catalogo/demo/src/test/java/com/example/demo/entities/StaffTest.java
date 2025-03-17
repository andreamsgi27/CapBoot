package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StaffTest {

    private Staff staff;

    @BeforeEach
    void setUp() {
        staff = new Staff();
        staff.setStaffId((byte) 1);
        staff.setActive((byte) 1);
        staff.setEmail("test@example.com");
        staff.setFirstName("John");
        staff.setLastName("Doe");
        staff.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        staff.setPassword("password123");
        staff.setPicture(new byte[]{1, 2, 3});
        staff.setUsername("johndoe");
        staff.setAddress(new Address());
        staff.setStore(new Store());
        staff.setPayments(new ArrayList<>());
        staff.setRentals(new ArrayList<>());
        staff.setStores(new ArrayList<>());
    }

    @Test
    void testGettersAndSetters() {
        staff.setStaffId((byte) 2);
        staff.setActive((byte) 0);
        staff.setEmail("updated@example.com");
        staff.setFirstName("Jane");
        staff.setLastName("Smith");
        staff.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        staff.setPassword("newpassword");
        staff.setPicture(new byte[]{4, 5, 6});
        staff.setUsername("janesmith");
        Address address = new Address();
        Store store = new Store();
        staff.setAddress(address);
        staff.setStore(store);

        assertEquals((byte) 2, staff.getStaffId());
        assertEquals((byte) 0, staff.getActive());
        assertEquals("updated@example.com", staff.getEmail());
        assertEquals("Jane", staff.getFirstName());
        assertEquals("Smith", staff.getLastName());
        assertNotNull(staff.getLastUpdate());
        assertEquals("newpassword", staff.getPassword());
        assertArrayEquals(new byte[]{4, 5, 6}, staff.getPicture());
        assertEquals("janesmith", staff.getUsername());
        assertEquals(address, staff.getAddress());
        assertEquals(store, staff.getStore());
    }

    @Test
    void testAddPayment() {
        Payment payment = new Payment();
        staff.addPayment(payment);

        assertEquals(1, staff.getPayments().size());
        assertEquals(staff, payment.getStaff());
    }

    @Test
    void testRemovePayment() {
        Payment payment = new Payment();
        staff.addPayment(payment);
        staff.removePayment(payment);

        assertTrue(staff.getPayments().isEmpty());
        assertNull(payment.getStaff());
    }

    @Test
    void testAddRental() {
        Rental rental = new Rental();
        staff.addRental(rental);

        assertEquals(1, staff.getRentals().size());
        assertEquals(staff, rental.getStaff());
    }

    @Test
    void testRemoveRental() {
        Rental rental = new Rental();
        staff.addRental(rental);
        staff.removeRental(rental);

        assertTrue(staff.getRentals().isEmpty());
        assertNull(rental.getStaff());
    }

    @Test
    void testAddStore() {
        Store store = new Store();
        staff.addStore(store);

        assertEquals(1, staff.getStores().size());
        assertEquals(staff, store.getStaff());
    }

    @Test
    void testRemoveStore() {
        Store store = new Store();
        staff.addStore(store);
        staff.removeStore(store);

        assertTrue(staff.getStores().isEmpty());
        assertNull(store.getStaff());
    }
}