package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CityTest {

    private City city;

    @BeforeEach
    void setUp() {
        city = new City();
        city.setCityId(1);
        city.setCity("Test City");
    }

    @Test
    void testGettersAndSetters() {
        city.setCityId(2);
        city.setCity("Updated City");

        assertEquals(2, city.getCityId());
        assertEquals("Updated City", city.getCity());
    }

    /* @Test
    void testToString() {
        String expected = "City [cityId=1, city=Test City]";
        assertEquals(expected, city.toString());
    } */
}