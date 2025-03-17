package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InventoryTest {

    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        inventory.setInventoryId(1);
    }

    @Test
    void testGettersAndSetters() {
        inventory.setInventoryId(2);

        assertEquals(2, inventory.getInventoryId());
    }

    /* @Test
    void testToString() {
        String expected = "Inventory [inventoryId=1]";
        assertEquals(expected, inventory.toString());
    } */
}