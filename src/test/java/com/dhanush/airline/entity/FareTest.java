package com.dhanush.airline.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FareTest {

    @Test
    void givenEntity_thenCreateFare() {
        final Fare fare = createFare();

        assertEquals(fare.getFare(), 2.0);
        assertEquals(fare.getFareId(), 1L);
        assertEquals(fare.getCurrency(), "USD");
        assertTrue(fare.toString().contains("Fare"));
    }

    @Test
    void givenEntityConstructorTwo_thenCreateFare() {
        final Fare fare =new Fare("USD", 1.0);

        assertEquals(fare.getFare(), 1.0);
        assertEquals(fare.getCurrency(), "USD");
        assertTrue(fare.toString().contains("Fare"));
    }

    private static Fare createFare() {
        Fare fare = new Fare();
        fare.setFare(2.0);
        fare.setFareId(1L);
        fare.setCurrency("USD");

        return fare;
    }
}