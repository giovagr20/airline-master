package com.dhanush.airline.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckinTest {

    private static final Checkin CHECKIN = createCheckin();

    @Test
    void givenEntity_thenCreateCheckin() {
        assertEquals(CHECKIN.getCheckinId(), 1L);
        assertEquals(CHECKIN.getGateNumber(), "1");
        assertTrue(CHECKIN.toString().contains( "Checkin"));
    }
    private static Checkin createCheckin() {
        Checkin checkin = new Checkin();

        checkin.setGateNumber("1");
        checkin.setSeatNumber("2");
        checkin.setCheckinId(1L);

        return  checkin;
    }

}