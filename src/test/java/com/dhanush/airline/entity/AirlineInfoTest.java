package com.dhanush.airline.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirlineInfoTest {

    private static final AirlineInfo AIRLINE_INFO = createAirlineInfo();

    @Test
    void givenEntity_thenCreateAirlineInfo() {

        assertEquals(AIRLINE_INFO.getNameOfAirline(), "Name");
        assertEquals(AIRLINE_INFO.getAirlineId(), 1L);
        assertEquals(AIRLINE_INFO.getAirlineLogo(), "Logo");
        assertTrue(AIRLINE_INFO.toString().contains("AirlineInfo"));
    }

    @Test
    void givenEntityConstructorThree_thenCreateAirlineInfo() {

        final AirlineInfo airlineInfo = new AirlineInfo(1L, "LogoTest", "NameTest");
        assertEquals(airlineInfo.getNameOfAirline(), "NameTest");
        assertEquals(airlineInfo.getAirlineId(), 1L);
        assertEquals(airlineInfo.getAirlineLogo(), "LogoTest");
        assertTrue(airlineInfo.toString().contains("AirlineInfo"));
    }

    @Test
    void givenEntityConstructorTwo_thenCreateAirlineInfo() {

        final AirlineInfo airlineInfo = new AirlineInfo( "LogoTest", "NameTest");
        assertEquals(airlineInfo.getNameOfAirline(), "NameTest");
        assertEquals(airlineInfo.getAirlineLogo(), "LogoTest");
        assertTrue(airlineInfo.toString().contains("AirlineInfo"));
    }

    private static AirlineInfo createAirlineInfo() {
        AirlineInfo airlineInfo = new AirlineInfo();
        airlineInfo.setAirlineLogo("Logo");
        airlineInfo.setAirlineId(1L);
        airlineInfo.setNameOfAirline("Name");

        return airlineInfo;
    }

}