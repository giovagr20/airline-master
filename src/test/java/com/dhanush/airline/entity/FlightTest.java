package com.dhanush.airline.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    @Test
    void givenEntity_thenCreateFlight() {
        final Flight flight = createFlight();

        assertEquals(flight.getFlightNumber(), "4");
        assertEquals(flight.getId(), 1L);
        assertNotNull(flight.getFlightDate());
        assertNotNull(flight.getFlightTime());
        assertEquals(flight.getDestination(), "Destination");
        assertEquals(flight.getDuration(), "4h");
        assertEquals(flight.getOrigin(), "Origin");
        assertNotNull(flight.getInventory());
        assertNotNull(flight.getFlightInfo());
        assertNotNull(flight.getFare());
        assertTrue(flight.toString().contains("Flight"));
    }

    @Test
    void givenEntityContructor_thenCreateFlight() {
        LocalDateTime localDateTime = LocalDateTime.now();
        final Flight flight = new Flight("Destination", "4h",localDateTime.toLocalDate(),"4",localDateTime.toLocalTime(), "Origin", new Fare(), new FlightInfo(), new Inventory(2));

        assertEquals(flight.getFlightNumber(), "4");
        assertNotNull(flight.getFlightDate());
        assertNotNull(flight.getFlightTime());
        assertEquals(flight.getDestination(), "Destination");
        assertEquals(flight.getDuration(), "4h");
        assertEquals(flight.getOrigin(), "Origin");
        assertEquals(flight.getInventory().getInventoryId(), 0);
        assertNotNull(flight.getFlightInfo());
        assertNotNull(flight.getFare());
        assertTrue(flight.toString().contains("Flight"));

    }
    private static Flight createFlight() {
        Flight flight = new Flight();

        LocalDateTime localDateTime = LocalDateTime.now();
        flight.setFlightTime(localDateTime.toLocalTime());
        flight.setFlightDate(localDateTime.toLocalDate());
        flight.setId(1L);
        flight.setFlightNumber("4");
        flight.setFlightInfo(new FlightInfo());
        flight.setDestination("Destination");
        flight.setOrigin("Origin");
        Inventory inventory = new Inventory();
        inventory.setInventoryId(2);
        flight.setInventory(inventory);
        flight.setFare(new Fare());
        flight.setDuration("4h");

        return flight;
    }
}