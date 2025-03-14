package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("John");
        customer.setLastName("Doe");
    }

    @Test
    void testGettersAndSetters() {
        customer.setCustomerId(2);
        customer.setFirstName("Jane");
        customer.setLastName("Smith");

        assertEquals(2, customer.getCustomerId());
        assertEquals("Jane", customer.getFirstName());
        assertEquals("Smith", customer.getLastName());
    }

    /* @Test
    void testToString() {
        String expected = "Customer [customerId=1, firstName=John, lastName=Doe]";
        assertEquals(expected, customer.toString());
    } */
}