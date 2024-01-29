package com.dhanush.airline.service;

import com.dhanush.airline.dao.PassengerDao;
import com.dhanush.airline.entity.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class PassengerServiceTest {

    @Mock private PassengerDao passengerDao;

    @InjectMocks private  PassengerService passengerService;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void givenPassengerService_whenGetAllPassengerIsCalled_thenReturnOk() {
        when(passengerDao.findAll()).thenReturn(createListPassenger());

        List<Passenger> passengers = passengerService.getAllPassengers();

        verify(passengerDao).findAll();

        assertEquals(passengers.size(), 1);
    }

    @Test
    void givenPassengerService_whenGetPassengerIsCalled_thenReturnOk() {
        Optional<Passenger> passengerOptional = createPassenger();
        when(passengerDao.findById(1L)).thenReturn(passengerOptional);

        Passenger passenger = passengerService.findById(1L);

        verify(passengerDao).findById(1L);

        assertEquals(passenger.getPassengerId(), passengerOptional.get().getPassengerId());
    }

    @Test
    void givenPassengerService_whenSaveAll_thenOk() {
        List<Passenger> passengers = createListPassenger();
        when(passengerDao.saveAll(anyIterable())).thenReturn(passengers);

        passengerService.saveAll(passengers);

        verify(passengerDao).saveAll(anyIterable());

    }

    @Test
    void givenPassengerService_whenSave_thenOk() {
        Passenger passenger = createPassenger().get();
        when(passengerDao.save(any())).thenReturn(passenger);

        passengerService.save(passenger);

        verify(passengerDao).save(any());

    }

    private List<Passenger> createListPassenger() {
        List<Passenger> passengers = new ArrayList<>();

        Passenger passenger = new Passenger();
        passenger.setPassengerId(1L);

        passengers.add(passenger);

        return passengers;
    }

    private Optional<Passenger> createPassenger() {
        Passenger passenger = new Passenger();
        passenger.setPassengerId(1L);

        return Optional.of(passenger);
    }
}