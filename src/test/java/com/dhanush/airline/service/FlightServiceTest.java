package com.dhanush.airline.service;

import com.dhanush.airline.dao.FlightDao;
import com.dhanush.airline.entity.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {
    @Mock private FlightDao flightDao;
    @InjectMocks private FlightService flightService;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void givenFlightService_whenFindByFlightInfoAirlineInfoNameOfAirlineAndFlightDateIsCalled_thenOk() {
        final List<Flight> flights = createListFlight();
        final String name = "Test";
        final LocalDate localDate = LocalDateTime.now().toLocalDate();
        when(flightDao.findByFlightInfoAirlineInfoNameOfAirlineAndFlightDate(name, localDate)).thenReturn(flights);

        List<Flight> flightsResponse = flightService.findByFlightInfoAirlineInfoNameOfAirlineAndFlightDate(name, localDate);

        verify(flightDao).findByFlightInfoAirlineInfoNameOfAirlineAndFlightDate(name, localDate);

        assertArrayEquals(flights.toArray(), flightsResponse.toArray());

    }

    @Test
    void givenFlightService_whenFindByOriginAndFlightDateIsCalled_thenOk() {
        final List<Flight> flights = createListFlight();
        final String name = "Test";
        final LocalDate localDate = LocalDateTime.now().toLocalDate();
        when(flightDao.findByOriginAndFlightDate(name, localDate)).thenReturn(flights);

        List<Flight> flightsResponse = flightService.findByOriginAndFlightDate(name, localDate);

        verify(flightDao).findByOriginAndFlightDate(name, localDate);

        assertArrayEquals(flights.toArray(), flightsResponse.toArray());
    }

    @Test
    void givenFlightService_whenGetFlightsByOriginAndDestinationAndFlightDateOrderByFareFareIsCalled_thenOk() {
        final List<Flight> flights = createListFlight();
        final String origin = "OriginTest";
        final String destination = "DestinationTest";
        final LocalDate localDate = LocalDateTime.now().toLocalDate();
        when(flightDao.getFlightsByOriginAndDestinationAndFlightDateOrderByFareFare(origin, destination, localDate)).thenReturn(flights);

        List<Flight> flightsResponse = flightService.getFlightsByOriginAndDestinationAndFlightDateOrderByFareFare(origin, destination, localDate);

        verify(flightDao).getFlightsByOriginAndDestinationAndFlightDateOrderByFareFare(origin, destination, localDate);

        assertArrayEquals(flights.toArray(), flightsResponse.toArray());
    }

    @Test
    void givenFlightService_whenGetFlightsByOriginAndDestinationAndFlightDateOrderByFareFareIntIsCalled_thenOk() {
        final List<Flight> flights = createListFlight();
        final String origin = "OriginTest";
        final String destination = "DestinationTest";
        final LocalDate localDate = LocalDateTime.now().toLocalDate();
        when(flightDao.getFlightsByOriginAndDestinationAndFlightDateOrderByFareFare(origin, destination, localDate, 1)).thenReturn(flights);

        List<Flight> flightsResponse = flightService.getFlightsByOriginAndDestinationAndFlightDateOrderByFareFare(origin, destination, localDate, 1);

        verify(flightDao).getFlightsByOriginAndDestinationAndFlightDateOrderByFareFare(origin, destination, localDate, 1);

        assertArrayEquals(flights.toArray(), flightsResponse.toArray());
    }

    @Test
    void givenFlightService_whenFindByFlightNumberAndOriginAndDestinationIsCalled_thenOk() {
        final List<Flight> flights = createListFlight();
        final String origin = "OriginTest";
        final String destination = "DestinationTest";
        final String flightNumber = "FlightNumber";
        when(flightDao.findByFlightNumberAndOriginAndDestination(flightNumber, origin, destination)).thenReturn(flights);

        List<Flight> flightsResponse = flightService.findByFlightNumberAndOriginAndDestination(flightNumber, origin, destination);

        verify(flightDao).findByFlightNumberAndOriginAndDestination(flightNumber, origin, destination);

        assertArrayEquals(flights.toArray(), flightsResponse.toArray());
    }

    @Test
    void givenFlightService_whenFindByFlightNumberAndFlightDateAndFlightTimeIsCalled_thenOk() {
        final Flight flight = createListFlight().get(0);
        final String flightNumber = "Test";
        final LocalDateTime localDateTime = LocalDateTime.now();
        final LocalDate localDate = localDateTime.toLocalDate();
        final LocalTime localTime = localDateTime.toLocalTime();
        when(flightDao.findByFlightNumberAndFlightDateAndFlightTime(flightNumber, localDate, localTime)).thenReturn(flight);

        Flight flightResponse = flightService.findByFlightNumberAndFlightDateAndFlightTime(flightNumber, localDate, localTime);

        verify(flightDao).findByFlightNumberAndFlightDateAndFlightTime(flightNumber, localDate, localTime);

        assertEquals(flight.getId(), flightResponse.getId());
    }

    @Test
    void givenFlightService_whenFindByOriginAndDestinationAndFlightDateAndFlightTimeTimeIsCalled_thenOk() {
        final Flight flight = createListFlight().get(0);
        final String origin = "TestOrigin";
        final String destination = "TestDestination";

        final LocalDateTime localDateTime = LocalDateTime.now();
        final LocalDate localDate = localDateTime.toLocalDate();
        final LocalTime localTime = localDateTime.toLocalTime();
        when(flightDao.findByOriginAndDestinationAndFlightDateAndFlightTime(origin, destination, localDate, localTime)).thenReturn(flight);

        Flight flightResponse = flightService.findByOriginAndDestinationAndFlightDateAndFlightTime(origin, destination, localDate, localTime);

        verify(flightDao).findByOriginAndDestinationAndFlightDateAndFlightTime(origin, destination, localDate, localTime);

        assertEquals(flight.getId(), flightResponse.getId());
    }


    @Test
    void givenFlightService_whenSaveAllIsCalled_thenOk() {
        final List<Flight> flights = createListFlight();
        when(flightDao.saveAll(anyIterable())).thenReturn(flights);

        List<Flight> flightsResponse = flightService.saveAll(flights);

        verify(flightDao).saveAll(anyIterable());

        assertArrayEquals(flights.toArray(), flightsResponse.toArray());
    }

    @Test
    void givenFlightService_whenSaveIsCalled_thenOk() {
        final Flight flight = createListFlight().get(0);

        when(flightDao.save(any())).thenReturn(flight);

        Flight flightsResponse = flightService.saveFlight(flight);

        verify(flightDao).save(any());

        assertEquals(flight.getId(), flightsResponse.getId());
    }

    @Test
    void givenFlightService_whenFindAllIsCalled_thenOk() {
        final List<Flight> flights = createListFlight();

        when(flightDao.findAll()).thenReturn(flights);

        List<Flight> flightServiceAll = flightService.findAll();

        verify(flightDao).findAll();

        assertArrayEquals(flights.toArray(), flightServiceAll.toArray());
    }
    private List<Flight> createListFlight() {
        Flight flight = new Flight();

        flight.setId(1L);

        List<Flight> flights = new ArrayList<>();
        flights.add(flight);
        return flights;

    }
}