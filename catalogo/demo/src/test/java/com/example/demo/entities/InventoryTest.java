package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InventoryTest {

    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        inventory.setInventoryId(1);
        inventory.setLastUpdate(new Date(System.currentTimeMillis()));
        inventory.setFilm(new Film());
        inventory.setStore(new Store());
        inventory.setRentals(new ArrayList<>());
    }

    @Test
    void testGettersAndSetters() {
        inventory.setInventoryId(2);
        inventory.setLastUpdate(new Date(System.currentTimeMillis()));
        Film film = new Film();
        Store store = new Store();
        inventory.setFilm(film);
        inventory.setStore(store);

        assertEquals(2, inventory.getInventoryId());
        assertNotNull(inventory.getLastUpdate());
        assertEquals(film, inventory.getFilm());
        assertEquals(store, inventory.getStore());
    }

    @Test
    void testAddRental() {
        Rental rental = new Rental();
        inventory.addRental(rental);

        assertEquals(1, inventory.getRentals().size());
        assertEquals(inventory, rental.getInventory());
    }

    @Test
    void testRemoveRental() {
        Rental rental = new Rental();
        inventory.addRental(rental);
        inventory.removeRental(rental);

        assertTrue(inventory.getRentals().isEmpty());
        assertNull(rental.getInventory());
    }
}