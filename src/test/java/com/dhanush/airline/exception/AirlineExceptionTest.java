package com.dhanush.airline.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AirlineExceptionTest {

    private AirlineException airlineException;

    @BeforeEach
    void setUp() {
        airlineException = new AirlineException();
    }

    @Test
    void exception() {
        airlineException.setErrorInfo(new ErrorInfo("400", "An error test", LocalDateTime.now()));

        assertEquals(airlineException.getErrorInfo().getErrorCode(), "400");
    }

    @Test
    void exceptionConstructor() {
        final AirlineException airlineException = new AirlineException(new ErrorInfo("400", "An error test", LocalDateTime.now()));

        assertEquals(airlineException.getErrorInfo().getErrorCode(), "400");
    }

    @Test
    void errorInfo() {
        final ErrorInfo errorInf = new ErrorInfo();

        errorInf.setErrorCode("401");
        errorInf.setErrorMessage("Error message test");
        errorInf.setErrorTimestamp(LocalDateTime.now());

        assertEquals(errorInf.getErrorCode(), "401");
        assertNotNull(errorInf.getErrorTimestamp());
        assertEquals(errorInf.getErrorMessage(), "Error message test");
    }
}