package com.example.demo.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NotFoundExceptionTest {

    @Test
    void testDefaultConstructor() {
        NotFoundException exception = new NotFoundException();
        assertEquals("Not found", exception.getMessage());
    }

    @Test
    void testMessageConstructor() {
        String customMessage = "Custom not found message";
        NotFoundException exception = new NotFoundException(customMessage);
        assertEquals(customMessage, exception.getMessage());
    }

    @Test
    void testCauseConstructor() {
        Throwable cause = new RuntimeException("Cause");
        NotFoundException exception = new NotFoundException(cause);
        assertEquals("Not found", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testMessageAndCauseConstructor() {
        String customMessage = "Custom not found message";
        Throwable cause = new RuntimeException("Cause");
        NotFoundException exception = new NotFoundException(customMessage, cause);
        assertEquals(customMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testFullConstructor() {
        String customMessage = "Custom not found message";
        Throwable cause = new RuntimeException("Cause");
        NotFoundException exception = new NotFoundException(customMessage, cause, true, false);
        assertEquals(customMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
