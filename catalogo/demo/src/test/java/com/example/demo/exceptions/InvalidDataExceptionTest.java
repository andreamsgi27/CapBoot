package com.example.demo.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class InvalidDataExceptionTest {

    @Test
    void testDefaultConstructor() {
        InvalidDataException exception = new InvalidDataException();
        assertEquals("Invalid data", exception.getMessage());
        assertFalse(exception.hasErrors());
    }

    @Test
    void testMessageConstructor() {
        String customMessage = "Custom invalid data message";
        InvalidDataException exception = new InvalidDataException(customMessage);
        assertEquals(customMessage, exception.getMessage());
        assertFalse(exception.hasErrors());
    }

    @Test
    void testErrorsConstructor() {
        Map<String, String> errors = new HashMap<>();
        errors.put("field1", "error1");
        InvalidDataException exception = new InvalidDataException(errors);
        assertEquals("Invalid data", exception.getMessage());
        assertTrue(exception.hasErrors());
        assertEquals(errors, exception.getErrors());
    }

    @Test
    void testMessageAndErrorsConstructor() {
        String customMessage = "Custom invalid data message";
        Map<String, String> errors = new HashMap<>();
        errors.put("field1", "error1");
        InvalidDataException exception = new InvalidDataException(customMessage, errors);
        assertEquals(customMessage, exception.getMessage());
        assertTrue(exception.hasErrors());
        assertEquals(errors, exception.getErrors());
    }

    @Test
    void testCauseConstructor() {
        Throwable cause = new RuntimeException("Cause");
        InvalidDataException exception = new InvalidDataException(cause);
        assertEquals("Invalid data", exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertFalse(exception.hasErrors());
    }

    @Test
    void testMessageAndCauseConstructor() {
        String customMessage = "Custom invalid data message";
        Throwable cause = new RuntimeException("Cause");
        InvalidDataException exception = new InvalidDataException(customMessage, cause);
        assertEquals(customMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertFalse(exception.hasErrors());
    }

    @Test
    void testMessageCauseAndErrorsConstructor() {
        String customMessage = "Custom invalid data message";
        Throwable cause = new RuntimeException("Cause");
        Map<String, String> errors = new HashMap<>();
        errors.put("field1", "error1");
        InvalidDataException exception = new InvalidDataException(customMessage, cause, errors);
        assertEquals(customMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertTrue(exception.hasErrors());
        assertEquals(errors, exception.getErrors());
    }

    @Test
    void testFullConstructor() {
        String customMessage = "Custom invalid data message";
        Throwable cause = new RuntimeException("Cause");
        Map<String, String> errors = new HashMap<>();
        errors.put("field1", "error1");
        InvalidDataException exception = new InvalidDataException(customMessage, cause, errors, true, false);
        assertEquals(customMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertTrue(exception.hasErrors());
        assertEquals(errors, exception.getErrors());
    }
}
