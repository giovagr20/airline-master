package com.dhanush.airline.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {

    @Test
    void givenEntity_thenCreatePassenger() {
        final Passenger passenger = createPassenger();

        assertEquals(passenger.getPassengerId(), 1L);
        assertNotNull(passenger.getCheckIn());
        assertEquals(passenger.getFirstName(), "FirstName");
        assertEquals(passenger.getLastName(), "LastName");
        assertEquals(passenger.getGender(), "M");
        assertEquals(passenger.getMobileNumber(), 21L);
        assertEquals(passenger.getEmailAddress(), "anyEmail");
        assertEquals(passenger.getBookingId(), 2L);
        assertTrue(passenger.toString().contains("Passenger"));
    }

    @Test
    void givenEntityConstructor_thenCreatePassenger() {
        final Passenger passenger = new Passenger("anyEmail", "FirstName", "M", "LastName", 21L, 2L, new Checkin() );

        assertNotNull(passenger.getCheckIn());
        assertEquals(passenger.getFirstName(), "FirstName");
        assertEquals(passenger.getLastName(), "LastName");
        assertEquals(passenger.getGender(), "M");
        assertEquals(passenger.getMobileNumber(), 21L);
        assertEquals(passenger.getEmailAddress(), "anyEmail");
        assertEquals(passenger.getBookingId(), 2L);
        assertTrue(passenger.toString().contains("Passenger"));
    }
    private static Passenger createPassenger() {
        Passenger passenger = new Passenger();

        passenger.setCheckIn(new Checkin());
        passenger.setPassengerId(1L);
        passenger.setLastName("LastName");
        passenger.setFirstName("FirstName");
        passenger.setGender("M");
        passenger.setMobileNumber(21L);
        passenger.setBookingId(2L);
        passenger.setEmailAddress("anyEmail");
        return passenger;
    }

}