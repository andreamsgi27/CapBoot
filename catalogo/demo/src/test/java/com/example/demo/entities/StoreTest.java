package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StoreTest {

    private Store store;

    @BeforeEach
    void setUp() {
        store = new Store();
        store.setStoreId((byte) 1);
        store.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        store.setAddress(new Address());
        store.setStaff(new Staff());
        store.setCustomers(new ArrayList<>());
        store.setInventories(new ArrayList<>());
        store.setStaffs(new ArrayList<>());
    }

    @Test
    void testGettersAndSetters() {
        store.setStoreId((byte) 2);
        store.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        Address address = new Address();
        Staff manager = new Staff();
        store.setAddress(address);
        store.setStaff(manager);

        assertEquals((byte) 2, store.getStoreId());
        assertNotNull(store.getLastUpdate());
        assertEquals(address, store.getAddress());
        assertEquals(manager, store.getStaff());
    }

    @Test
    void testAddCustomer() {
        Customer customer = new Customer();
        store.addCustomer(customer);

        assertEquals(1, store.getCustomers().size());
        assertEquals(store, customer.getStore());
    }

    @Test
    void testRemoveCustomer() {
        Customer customer = new Customer();
        store.addCustomer(customer);
        store.removeCustomer(customer);

        assertTrue(store.getCustomers().isEmpty());
        assertNull(customer.getStore());
    }

    @Test
    void testAddInventory() {
        Inventory inventory = new Inventory();
        store.addInventory(inventory);

        assertEquals(1, store.getInventories().size());
        assertEquals(store, inventory.getStore());
    }

    @Test
    void testRemoveInventory() {
        Inventory inventory = new Inventory();
        store.addInventory(inventory);
        store.removeInventory(inventory);

        assertTrue(store.getInventories().isEmpty());
        assertNull(inventory.getStore());
    }

    @Test
    void testAddStaff() {
        Staff staff = new Staff();
        store.addStaff(staff);

        assertEquals(1, store.getStaffs().size());
        assertEquals(store, staff.getStore());
    }

    @Test
    void testRemoveStaff() {
        Staff staff = new Staff();
        store.addStaff(staff);
        store.removeStaff(staff);

        assertTrue(store.getStaffs().isEmpty());
        assertNull(staff.getStore());
    }
}