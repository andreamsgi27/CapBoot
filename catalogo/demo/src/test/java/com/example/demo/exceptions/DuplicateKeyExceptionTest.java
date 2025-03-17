package com.example.demo.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DuplicateKeyExceptionTest {

    @Test
    void testDefaultConstructor() {
        DuplicateKeyException exception = new DuplicateKeyException();
        assertEquals("Duplicate key", exception.getMessage());
    }

    @Test
    void testMessageConstructor() {
        String customMessage = "Custom duplicate key message";
        DuplicateKeyException exception = new DuplicateKeyException(customMessage);
        assertEquals(customMessage, exception.getMessage());
    }

    @Test
    void testCauseConstructor() {
        Throwable cause = new RuntimeException("Cause");
        DuplicateKeyException exception = new DuplicateKeyException(cause);
        assertEquals("Duplicate key", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testMessageAndCauseConstructor() {
        String customMessage = "Custom duplicate key message";
        Throwable cause = new RuntimeException("Cause");
        DuplicateKeyException exception = new DuplicateKeyException(customMessage, cause);
        assertEquals(customMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testFullConstructor() {
        String customMessage = "Custom duplicate key message";
        Throwable cause = new RuntimeException("Cause");
        DuplicateKeyException exception = new DuplicateKeyException(customMessage, cause, true, false);
        assertEquals(customMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
