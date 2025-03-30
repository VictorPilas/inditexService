package com.vps.inditex.infrastructure.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {
    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void testGlobalExceptionHandler_handlePriceNotFoundException() {
        PriceNotFoundException exception = new PriceNotFoundException("Price not found");
        ResponseEntity<String> response = handler.handlePriceNotFoundException(exception);
        assertEquals(404, response.getStatusCode().value());
        assertEquals("Price not found", response.getBody());
    }

    @Test
    void testGlobalExceptionHandler_handleGenericException() {
        Exception exception = new Exception("Unexpected error");
        ResponseEntity<String> response = handler.handleGenericException(exception);
        assertEquals(500, response.getStatusCode().value());
        assertEquals("An unexpected error occurred: Unexpected error", response.getBody());
    }
}
