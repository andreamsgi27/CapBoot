package com.example.demo.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BadRequestExceptionTest {

    @Test
    void testMessageConstructor() {
        String customMessage = "Custom bad request message";
        BadRequestException exception = new BadRequestException(customMessage);
        assertEquals(customMessage, exception.getMessage());
    }

    @Test
    void testMessageAndCauseConstructor() {
        String customMessage = "Custom bad request message";
        Throwable cause = new RuntimeException("Cause");
        BadRequestException exception = new BadRequestException(customMessage, cause);
        assertEquals(customMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testFullConstructor() {
        String customMessage = "Custom bad request message";
        Throwable cause = new RuntimeException("Cause");
        BadRequestException exception = new BadRequestException(customMessage, cause, true, false);
        assertEquals(customMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
