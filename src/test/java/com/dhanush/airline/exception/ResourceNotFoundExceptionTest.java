package com.dhanush.airline.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ResourceNotFoundExceptionTest {

    private ResourceNotFoundException resourceNotFoundException;

    @BeforeEach
    void setUp() {
        resourceNotFoundException = new ResourceNotFoundException();
    }
    @Test
    void exception() {
        resourceNotFoundException.setErrorCode("400");
        resourceNotFoundException.setErrorMessage("An error test");
        resourceNotFoundException.setErrorTimestamp(LocalDateTime.now());

        assertEquals(resourceNotFoundException.getErrorCode(), "400");
        assertEquals(resourceNotFoundException.getErrorMessage(), "An error test");
        assertNotNull(resourceNotFoundException.getErrorTimestamp());

    }

    @Test
    void exceptionConstructor() {
        final ResourceNotFoundException resourceNotFoundException =
                new ResourceNotFoundException("400", "An error test", LocalDateTime.now());

        assertEquals(resourceNotFoundException.getErrorCode(), "400");
        assertEquals(resourceNotFoundException.getErrorMessage(), "An error test");
        assertNotNull(resourceNotFoundException.getErrorTimestamp());
    }

}