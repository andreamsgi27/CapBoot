package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StoreTest {

    private Store store;

    @BeforeEach
    void setUp() {
        store = new Store();
        store.setStoreId((byte) 1);
    }

    @Test
    void testGettersAndSetters() {
        store.setStoreId((byte) 2);

        assertEquals(2, store.getStoreId());
    }

    /* @Test
    void testToString() {
        String expected = "Store [storeId=1]";
        assertEquals(expected, store.toString());
    } */
}