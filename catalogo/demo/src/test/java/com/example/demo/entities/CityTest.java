package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class CityTest {

    @Test
    void testCityConstructor() {
        City city = new City(1, "New York", Timestamp.valueOf("2023-01-01 00:00:00"), null, null);
        assertEquals(1, city.getCityId());
        assertEquals("New York", city.getCity());
        assertEquals(Timestamp.valueOf("2023-01-01 00:00:00"), city.getLastUpdate());
    }

    @Test
    void testAddAddress() {
        City city = new City();
        Address address = new Address();
        city.setAddresses(new ArrayList<>());

        city.addAddress(address);

        assertTrue(city.getAddresses().contains(address));
        assertEquals(city, address.getCity());
    }

    @Test
    void testRemoveAddress() {
        City city = new City();
        Address address = new Address();
        city.setAddresses(new ArrayList<>());
        city.addAddress(address);

        city.removeAddress(address);

        assertFalse(city.getAddresses().contains(address));
        assertNull(address.getCity());
    }

    @Test
    void testEqualsAndHashCode() {
        City city1 = new City(1, "New York", null, null, null);
        City city2 = new City(1, "New York", null, null, null);
        City city3 = new City(2, "Los Angeles", null, null, null);

        assertEquals(city1, city2);
        assertNotEquals(city1, city3);
        assertEquals(city1.hashCode(), city2.hashCode());
        assertNotEquals(city1.hashCode(), city3.hashCode());
    }
}