package com.dhanush.airline.controller;

import com.dhanush.airline.entity.BookingRecord;
import com.dhanush.airline.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
class BookingRestControllerTest {

    @Mock private BookingService mockBookingService;

    @InjectMocks private BookingRestController bookingRestController;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void givenBookingController_whenAllBooksIsCalled_thenReturnOkStatus() {
        List<BookingRecord> books = createListBooking();
        when(mockBookingService.findAll()).thenReturn(books);

        List<BookingRecord> bookingRecords = bookingRestController.findAll();

        verify(mockBookingService).findAll();

        assertArrayEquals(books.toArray(), bookingRecords.toArray());
    }

    @Test
    void givenBookingController_whenBookFlightIsCalled_thenReturnOk() {
        BookingRecord bookingRecord = createBookingRecord();

        when(mockBookingService.bookFlight(any())).thenReturn(bookingRecord);

        BookingRecord bookingRecordResponse = bookingRestController.bookFlight(bookingRecord);

        verify(mockBookingService).bookFlight(any());

        assertEquals(bookingRecordResponse.getBookingId(), bookingRecord.getBookingId());
    }

    @Test
    void givenBookingController_whenGetBookByIdIsCalled_thenReturnOk() {
        BookingRecord bookingRecord = createBookingRecord();
        final long id = 1L;
        when(mockBookingService.getBookingById(id)).thenReturn(bookingRecord);

        BookingRecord bookingRecordResponse = bookingRestController.getBookingById(id);

        verify(mockBookingService).getBookingById(id);

        assertEquals(bookingRecordResponse.getBookingId(), bookingRecord.getBookingId());
    }

    private List<BookingRecord> createListBooking() {
        List<BookingRecord> bookingRecordList = new ArrayList<>();
        BookingRecord bookingRecord1 = new BookingRecord();
        bookingRecord1.setBookingId(1L);
        BookingRecord bookingRecord2 = new BookingRecord();
        bookingRecord2.setBookingId(2L);
        bookingRecordList.add(bookingRecord1);
        bookingRecordList.add(bookingRecord2);
        return bookingRecordList;
    }

    private BookingRecord createBookingRecord() {
        BookingRecord bookingRecord = new BookingRecord();
        bookingRecord.setBookingId(1L);
        return bookingRecord;
    }
}