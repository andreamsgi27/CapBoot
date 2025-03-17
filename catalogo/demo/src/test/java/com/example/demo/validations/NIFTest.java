package com.example.demo.validations;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintValidatorContext;

class NIFTest {

    private NIF.Validator validator;

    @BeforeEach
    void setUp() {
        validator = new NIF.Validator();
    }

    @Test
    void testIsValid_ValidNIF() {
        assertTrue(validator.isValid("12345678Z", mockConstraintValidatorContext()));
    }

    @Test
    void testIsValid_InvalidNIF() {
        assertFalse(validator.isValid("12345678A", mockConstraintValidatorContext()));
    }

    @Test
    void testIsValid_NullValue() {
        assertTrue(validator.isValid(null, mockConstraintValidatorContext())); // Null is valid
    }

    @Test
    void testIsValid_InvalidFormat() {
        assertFalse(validator.isValid("ABCDEFGH", mockConstraintValidatorContext()));
        assertFalse(validator.isValid("123456789", mockConstraintValidatorContext()));
    }

    private ConstraintValidatorContext mockConstraintValidatorContext() {
        return null; // Replace with a mock if needed
    }
}