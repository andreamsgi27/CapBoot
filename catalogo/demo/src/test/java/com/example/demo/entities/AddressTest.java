package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddressTest {

    private Address address;

    @BeforeEach
    void setUp() {
        address = new Address();
        address.setAddressId(1);
        address.setAddress("123 Main St");
        address.setAddress2("Apt 4B");
        address.setDistrict("Downtown");
        address.setPhone("123456789");
        address.setPostalCode("12345");
        address.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        address.setCity(new City());
        address.setCustomers(new ArrayList<>());
        address.setStaffs(new ArrayList<>());
        address.setStores(new ArrayList<>());
    }

    @Test
    void testGettersAndSetters() {
        address.setAddressId(2);
        address.setAddress("456 Elm St");
        address.setAddress2("Suite 5C");
        address.setDistrict("Uptown");
        address.setPhone("987654321");
        address.setPostalCode("54321");
        address.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        City city = new City();
        address.setCity(city);

        assertEquals(2, address.getAddressId());
        assertEquals("456 Elm St", address.getAddress());
        assertEquals("Suite 5C", address.getAddress2());
        assertEquals("Uptown", address.getDistrict());
        assertEquals("987654321", address.getPhone());
        assertEquals("54321", address.getPostalCode());
        assertNotNull(address.getLastUpdate());
        assertEquals(city, address.getCity());
    }

    @Test
    void testAddCustomer() {
        Customer customer = new Customer();
        address.addCustomer(customer);

        assertEquals(1, address.getCustomers().size());
        assertEquals(address, customer.getAddress());
    }

    @Test
    void testRemoveCustomer() {
        Customer customer = new Customer();
        address.addCustomer(customer);
        address.removeCustomer(customer);

        assertTrue(address.getCustomers().isEmpty());
        assertNull(customer.getAddress());
    }

    @Test
    void testAddStaff() {
        Staff staff = new Staff();
        address.addStaff(staff);

        assertEquals(1, address.getStaffs().size());
        assertEquals(address, staff.getAddress());
    }

    @Test
    void testRemoveStaff() {
        Staff staff = new Staff();
        address.addStaff(staff);
        address.removeStaff(staff);

        assertTrue(address.getStaffs().isEmpty());
        assertNull(staff.getAddress());
    }

    @Test
    void testAddStore() {
        Store store = new Store();
        address.addStore(store);

        assertEquals(1, address.getStores().size());
        assertEquals(address, store.getAddress());
    }

    @Test
    void testRemoveStore() {
        Store store = new Store();
        address.addStore(store);
        address.removeStore(store);

        assertTrue(address.getStores().isEmpty());
        assertNull(store.getAddress());
    }
}