package com.example.demo.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentTest {

    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment();
        payment.setPaymentId(1);
    }

    @Test
    void testGettersAndSetters() {
        payment.setPaymentId(2);

        assertEquals(2, payment.getPaymentId());
    }

    /* @Test
    void testToString() {
        String expected = "Payment [paymentId=1]";
        assertEquals(expected, payment.toString());
    } */
}