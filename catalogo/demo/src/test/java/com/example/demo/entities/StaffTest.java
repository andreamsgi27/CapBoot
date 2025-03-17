package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StaffTest {

    private Staff staff;

    @BeforeEach
    void setUp() {
        staff = new Staff();
        staff.setStaffId((byte) 1);
        staff.setFirstName("John");
    }

    @Test
    void testGettersAndSetters() {
        staff.setStaffId((byte) 2);
        staff.setFirstName("Jane");

        assertEquals(2, staff.getStaffId());
        assertEquals("Jane", staff.getFirstName());
    }

    /* @Test
    void testToString() {
        String expected = "Staff [staffId=1, firstName=John]";
        assertEquals(expected, staff.toString());
    } */
}