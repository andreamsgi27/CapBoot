package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CountryTest {

    private Country country;

    @BeforeEach
    void setUp() {
        country = new Country();
        country.setCountryId(1);
        country.setCountry("Spain");
        country.setLastUpdate(new Date(System.currentTimeMillis()));
        country.setCities(new ArrayList<>());
    }

    @Test
    void testGettersAndSetters() {
        country.setCountryId(2);
        country.setCountry("France");
        country.setLastUpdate(new Date(System.currentTimeMillis()));

        assertEquals(2, country.getCountryId());
        assertEquals("France", country.getCountry());
        assertNotNull(country.getLastUpdate());
    }

    @Test
    void testAddCity() {
        City city = new City();
        country.addCity(city);

        assertEquals(1, country.getCities().size());
        assertEquals(country, city.getCountry());
    }

    @Test
    void testRemoveCity() {
        City city = new City();
        country.addCity(city);
        country.removeCity(city);

        assertTrue(country.getCities().isEmpty());
        assertNull(city.getCountry());
    }
}