package com.dhanush.airline.controller;

import com.dhanush.airline.entity.Flight;
import com.dhanush.airline.service.FlightService;
import com.dhanush.airline.vo.SearchCriteria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
class FlightRestControllerTest {

    @Mock private FlightService mockFlightService;
    @InjectMocks private FlightRestController flightRestController;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void givenFlightController_whenSearchFlightIsCalled_thenReturnOk() {
        List<Flight> flights = createListFlight();
        SearchCriteria searchCriteria = createSearchCriteria();
        LocalDate flightDate = Instant.ofEpochMilli(searchCriteria.getFlightDate().getTime())
                .atZone(ZoneId.systemDefault()).toLocalDate();
        when(mockFlightService.getFlightsByOriginAndDestinationAndFlightDateOrderByFareFare(
                searchCriteria.getOrigin(), searchCriteria.getDestination(),flightDate,searchCriteria.getCount()
        )).thenReturn(flights);

        List<Flight> flightsResponse = flightRestController.searchFlights(searchCriteria);

        verify(mockFlightService).getFlightsByOriginAndDestinationAndFlightDateOrderByFareFare(searchCriteria.getOrigin(), searchCriteria.getDestination(),flightDate,searchCriteria.getCount());

        assertArrayEquals(flightsResponse.toArray(), flights.toArray());
    }

    @Test
    void givenFlightController_whenGetFlightByNumberFlightDateFlightTimeIsCalled_thenOk() {

        Flight flight = cretaeFlight();
        when(mockFlightService.findByFlightNumberAndFlightDateAndFlightTime(
                flight.getFlightNumber(),
                flight.getFlightDate(),
                flight.getFlightTime())).thenReturn(flight);

        Flight flightResponse = flightRestController.getFlightByNumberFlightDateFlightTime(flight);

        verify(mockFlightService).findByFlightNumberAndFlightDateAndFlightTime(
                flight.getFlightNumber(),
                flight.getFlightDate(),
                flight.getFlightTime());

        assertEquals(flight.getFlightNumber(), flightResponse.getFlightNumber());
    }

    private SearchCriteria createSearchCriteria() {
        return new SearchCriteria("TestOrigin",
                "TestDestination",
                new Date(2024, 01, 01),
                1
                );
    }

    private List<Flight> createListFlight() {
        Flight flight1 = new Flight();
        flight1.setId(1L);
        Flight flight2 = new Flight();
        flight2.setId(2L);

        List<Flight> flights = new ArrayList<>();

        flights.add(flight1);
        flights.add(flight2);

        return flights;
    }

    private Flight cretaeFlight() {
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setFlightNumber("FlightNumberTest");
        flight.setFlightDate(LocalDate.of(2024, 1, 1));
        flight.setFlightTime(LocalTime.of(1, 1, 1));

        return flight;
    }
}