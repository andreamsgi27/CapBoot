package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RentalTest {

    private Rental rental;

    @BeforeEach
    void setUp() {
        rental = new Rental();
        rental.setRentalId(1);
    }

    @Test
    void testGettersAndSetters() {
        rental.setRentalId(2);

        assertEquals(2, rental.getRentalId());
    }

    /* @Test
    void testToString() {
        String expected = "Rental [rentalId=1]";
        assertEquals(expected, rental.toString());
    } */
}