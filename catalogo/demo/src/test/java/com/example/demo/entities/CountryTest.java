package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CountryTest {

    private Country country;

    @BeforeEach
    void setUp() {
        country = new Country();
        country.setCountryId(1);
        country.setCountry("Test Country");
    }

    @Test
    void testGettersAndSetters() {
        country.setCountryId(2);
        country.setCountry("Updated Country");

        assertEquals(2, country.getCountryId());
        assertEquals("Updated Country", country.getCountry());
    }

    /* @Test
    void testToString() {
        String expected = "Country [countryId=1, country=Test Country]";
        assertEquals(expected, country.toString());
    } */
}