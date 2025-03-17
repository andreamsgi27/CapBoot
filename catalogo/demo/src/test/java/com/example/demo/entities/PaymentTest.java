package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentTest {

    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment();
        payment.setPaymentId(1);
        payment.setAmount(new BigDecimal("100.50"));
        payment.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        payment.setPaymentDate(new Date());
        payment.setCustomer(new Customer());
        payment.setRental(new Rental());
        payment.setStaff(new Staff());
    }

    @Test
    void testGettersAndSetters() {
        payment.setPaymentId(2);
        payment.setAmount(new BigDecimal("200.75"));
        payment.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        payment.setPaymentDate(new Date());
        Customer customer = new Customer();
        Rental rental = new Rental();
        Staff staff = new Staff();
        payment.setCustomer(customer);
        payment.setRental(rental);
        payment.setStaff(staff);

        assertEquals(2, payment.getPaymentId());
        assertEquals(new BigDecimal("200.75"), payment.getAmount());
        assertNotNull(payment.getLastUpdate());
        assertNotNull(payment.getPaymentDate());
        assertEquals(customer, payment.getCustomer());
        assertEquals(rental, payment.getRental());
        assertEquals(staff, payment.getStaff());
    }
}