package com.example.demo.validations;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CadenasValidatorTest {

    @Test
    void testIsNIF_ValidNIF() {
        assertTrue(CadenasValidator.isNIF("12345678Z")); // Valid NIF
    }

    @Test
    void testIsNIF_InvalidNIF() {
        assertFalse(CadenasValidator.isNIF("12345678A")); // Invalid NIF
    }

    @Test
    void testIsNIF_NullValue() {
        assertFalse(CadenasValidator.isNIF(null)); // Null value
    }

    @Test
    void testIsNIF_InvalidFormat() {
        assertFalse(CadenasValidator.isNIF("ABCDEFGH")); // Invalid format
        assertFalse(CadenasValidator.isNIF("123456789")); // Too long
    }

    @Test
    void testIsNotNIF_ValidNIF() {
        assertFalse(CadenasValidator.isNotNIF("12345678Z")); // Valid NIF
    }

    @Test
    void testIsNotNIF_InvalidNIF() {
        assertTrue(CadenasValidator.isNotNIF("12345678A")); // Invalid NIF
    }
}