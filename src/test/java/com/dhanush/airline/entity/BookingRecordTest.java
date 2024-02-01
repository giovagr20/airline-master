package com.dhanush.airline.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BookingRecordTest {

    private static final BookingRecord BOOKING_RECORD = createBookingRecord();

    @Test
    void givenEntity_thenCreateBookingRecord() {
        assertEquals(BOOKING_RECORD.getBookingId(), 1L);
        assertTrue(BOOKING_RECORD.toString().contains("BookingRecord"));
        assertNotNull(BOOKING_RECORD.getBookingDate());
    }

    private static BookingRecord createBookingRecord() {
        BookingRecord bookingRecord = new BookingRecord();

        bookingRecord.setBookingId(1L);
        bookingRecord.setPassengers(new ArrayList<>());
        bookingRecord.setBookingDate(LocalDateTime.now());
        bookingRecord.setDestination("Destination");
        bookingRecord.setFare(1.0);
        bookingRecord.setFlightDate(LocalDateTime.now().toLocalDate());
        bookingRecord.setOrigin("Origin");
        bookingRecord.setFlightNumber("Flight");
        bookingRecord.setStatus("P");
        bookingRecord.setFlightTime(LocalDateTime.now().toLocalTime());

        return bookingRecord;
    }
}