package com.dhanush.airline.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightInfoTest {


    @Test
    void givenEntity_thenCreateFlightInfo() {
        final FlightInfo flightInfo = createFlightInfo();

        assertNotNull(flightInfo.getAirlineInfo());
        assertEquals(flightInfo.getFlightNumber(), "123");
        assertEquals(flightInfo.getFlightType() , "Short");
        assertEquals(flightInfo.getNumberofSeats(), 45);
        assertTrue(flightInfo.toString().contains("FlightInfo"));
    }

    @Test
    void givenEntityConstructor_thenCreateFlightInfo() {
        final FlightInfo flightInfo = new FlightInfo("1", "Short", 30, new AirlineInfo());

        assertNotNull(flightInfo.getAirlineInfo());
        assertEquals(flightInfo.getFlightNumber(), "1");
        assertEquals(flightInfo.getFlightType() , "Short");
        assertEquals(flightInfo.getNumberofSeats(), 30);
        assertTrue(flightInfo.toString().contains("FlightInfo"));
    }
    private static FlightInfo createFlightInfo() {
        FlightInfo flightInfo = new FlightInfo();

        flightInfo.setAirlineInfo(new AirlineInfo());
        flightInfo.setFlightNumber("123");
        flightInfo.setFlightType("Short");
        flightInfo.setNumberofSeats(45);

        return flightInfo;
    }
}