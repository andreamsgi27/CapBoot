package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddressTest {

    private Address address;

    @BeforeEach
    void setUp() {
        address = new Address();
        address.setAddressId(1);
        address.setAddress("123 Main St");
    }

    @Test
    void testGettersAndSetters() {
        address.setAddressId(2);
        address.setAddress("456 Elm St");

        assertEquals(2, address.getAddressId());
        assertEquals("456 Elm St", address.getAddress());
    }
}