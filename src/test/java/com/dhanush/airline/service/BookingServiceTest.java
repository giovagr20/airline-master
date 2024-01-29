package com.dhanush.airline.service;

import com.dhanush.airline.dao.BookingDao;
import com.dhanush.airline.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock private BookingDao bookingDao;

    @Mock private PassengerService passengerService;

    @Mock private FlightService flightService;

    @Mock private UserService userService;
    @InjectMocks private BookingService bookingService;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void givenBookingService_whenIsSearchForId_thenReturnABook() {
        when(bookingDao.findByBookingId(1L)).thenReturn(testLookingByIdData());

        BookingRecord bookingRecord = bookingService.getBookingById(1L);

        verify(bookingDao).findByBookingId(1L);

        assertEquals(1L, bookingRecord.getBookingId());
    }

    @Test
    void givenBookingService_whenIsSearchByUserId_thenReturnABookRecord() {
        final String userName = "UserTest";
        User userTest = testUserData();
        when(userService.findByUserName(userName)).thenReturn(userTest);
        when(bookingDao.getBookingRecordsByUserId(userTest.getId()))
                .thenReturn(testLookByUserDataBookingRecord());

        List<BookingRecord> bookingRecordListResponse = bookingService.findBookingRecordByUserId(userName);

        verify(userService).findByUserName(userName);
        verify(bookingDao).getBookingRecordsByUserId(userTest.getId());

        assertNotNull(bookingRecordListResponse);
        assertEquals(bookingRecordListResponse.size(), 2);
    }

    @Test
    void givenBookingService_whenFindAllIsCalled_thenReturnAllBooks() {
        when(bookingDao.findAll()).thenReturn(testLookByUserDataBookingRecord());

        List<BookingRecord> bookingRecordList = bookingService.findAll();

        verify(bookingDao).findAll();

        assertEquals(bookingRecordList.size(), 2);
    }

    @Test
    void givenBookingService_whenBookFlighIsCalled_thenIsSaved() {
        BookingRecord inputRecord = new BookingRecord(LocalDateTime.now(), "Destination", 100.0,
                LocalDateTime.now().toLocalDate().plusDays(1), "ABC123", LocalDateTime.now().plusDays(2).toLocalTime(), "Origin", "Booked", testListPassenger());

        when(bookingDao.save(any(BookingRecord.class))).thenReturn(inputRecord);
        when(flightService.findByFlightNumberAndFlightDateAndFlightTime(any(), any(), any())).thenReturn(createTestFlight());
        when(flightService.saveFlight(any())).thenReturn(createTestFlight());

        BookingRecord bookingRecordResult = bookingService.bookFlight(inputRecord);

        assertNotNull(bookingRecordResult);
    }

    @Test
    void givenBookingService_whenBookFlightIsCalled_thenPassengerIsInvalid() {
        BookingRecord inputRecord = new BookingRecord(LocalDateTime.now(), "Destination", 100.0,
                LocalDateTime.now().plusDays(1).toLocalDate(), "ABC123", LocalDateTime.now().toLocalTime(), "Origin", "Booked", createInvalidPassengerList());

        when(bookingDao.save(any(BookingRecord.class))).thenReturn(inputRecord);
        when(flightService.findByFlightNumberAndFlightDateAndFlightTime(any(), any(), any())).thenReturn(createTestFlight());
        when(flightService.saveFlight(any())).thenReturn(createTestFlight());
        BookingRecord resultRecord = bookingService.bookFlight(inputRecord);

        assertNotNull(resultRecord);
        assertEquals("Booked", resultRecord.getStatus());
        assertEquals(0, resultRecord.getPassengers().size());
    }

//    @Test
//    void givenBookingService_thenUserNameIsEmpty() {
//        BookingRecord bookingRecord = bookingRecordNotFirstNameUser();
//        bookingService.bookFlight(bookingRecord);
//    }

    private BookingRecord bookingRecordNotFirstNameUser() {
        BookingRecord bookingRecord = new BookingRecord();
        bookingRecord.setBookingId(1L);

        List<Passenger> passengers = new ArrayList<>();

        Passenger passenger = new Passenger();
        passenger.setFirstName("");
        passengers.add(passenger);
        bookingRecord.setPassengers(passengers);
        return bookingRecord;
    }
    private BookingRecord bookingRecordNotLastNameUser() {
        BookingRecord bookingRecord = new BookingRecord();
        bookingRecord.setBookingId(1L);

        List<Passenger> passengers = new ArrayList<>();

        Passenger passenger = new Passenger();
        passenger.setLastName("");
        passengers.add(passenger);
        bookingRecord.setPassengers(passengers);
        return bookingRecord;
    }


    private BookingRecord testLookingByIdData() {
        BookingRecord bookingRecord = new BookingRecord();
        bookingRecord.setBookingId(1L);
        return bookingRecord;
    }

    private List<BookingRecord> testLookByUserDataBookingRecord() {
        List<BookingRecord> bookingRecordList = new ArrayList<>();
        BookingRecord bookingRecord1 = new BookingRecord();
        bookingRecord1.setBookingId(1L);
        BookingRecord bookingRecord2 = new BookingRecord();
        bookingRecord2.setBookingId(2L);
        bookingRecordList.add(bookingRecord1);
        bookingRecordList.add(bookingRecord2);
        return bookingRecordList;
    }

    private User testUserData() {
        final String username = "UserTest";
        final String password = "PasswordTest";
        final Long idUser = 1L;
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        user.setId(idUser);

        return user;
    }

    private List<Passenger> testListPassenger() {
        Passenger passenger1 = new Passenger();
        Passenger passenger2 = new Passenger();

        passenger1.setPassengerId(1L);
        passenger1.setFirstName("TestFirstName1");
        passenger1.setLastName("TestLastName1");
        passenger1.setGender("M");
        passenger1.setMobileNumber(39L);


        passenger2.setPassengerId(2L);
        passenger2.setFirstName("TestFirstName2");
        passenger2.setLastName("TestLastName2");
        passenger2.setGender("F");
        passenger2.setMobileNumber(40L);

        List<Passenger> passengerList = new ArrayList<>();
        passengerList.add(passenger1);
        passengerList.add(passenger2);

        return passengerList;
    }

    private BookingRecord testBookingRecord() {
        BookingRecord bookingRecord = new BookingRecord();

        bookingRecord.setBookingId(1L);
        bookingRecord.setPassengers(testListPassenger());

        return bookingRecord;
    }

    private Flight createTestFlight() {
        // Create a test Flight object for mocking
        Inventory inventory = new Inventory();
        inventory.setCount(10);

        Flight flight = new Flight();
        flight.setFlightNumber("ABC123");
        flight.setFlightDate(LocalDate.from(LocalDateTime.now().plusDays(1)));
        flight.setFlightTime(LocalDateTime.now().toLocalTime());
        flight.setInventory(inventory);

        return flight;
    }
    private List<Passenger> createInvalidPassengerList() {
        List<Passenger> passengers = new ArrayList<>();
        Passenger passenger1 = new Passenger();
        passenger1.setFirstName("");
        Passenger passenger2 = new Passenger();
        passenger2.setLastName("");
        Passenger passenger3 = new Passenger();
        passenger3.setGender("");
        Passenger passenger4 = new Passenger();

        passengers.add(passenger1);
        passengers.add(passenger2);
        passengers.add(passenger3);
        passengers.add(passenger4);
        return passengers;
    }

}