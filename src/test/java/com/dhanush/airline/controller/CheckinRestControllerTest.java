package com.dhanush.airline.controller;

import com.dhanush.airline.entity.Checkin;
import com.dhanush.airline.entity.Passenger;
import com.dhanush.airline.service.PassengerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.parameters.P;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
class CheckinRestControllerTest {

    @Mock private PassengerService mockPassengerService;

    @InjectMocks private CheckinRestController checkinRestController;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void givenCheckingController_whenCheckingIsCalled_thenOk() {
        Passenger passenger = createPassenger();

        when(mockPassengerService.findById(1L)).thenReturn(passenger);
        doNothing().when(mockPassengerService).save(passenger);

        Passenger passengerResponse = checkinRestController.checkinFormSubmit(passenger);

        verify(mockPassengerService).findById(1L);
        verify(mockPassengerService, times(1)).save(passenger);

        assertEquals(passenger.getPassengerId(), passengerResponse.getPassengerId());
        assertEquals(passenger.getFirstName(), passengerResponse.getFirstName());

    }

    private Passenger createPassenger() {
        Passenger passenger = new Passenger();

        passenger.setPassengerId(1L);
        passenger.setFirstName("TestName");
        passenger.setLastName("TestLastName");

        passenger.setCheckIn(createCheckin());
        return passenger;
    }

    private Checkin createCheckin() {
        Checkin checkin = new Checkin();

        checkin.setCheckinId(1L);
        checkin.setSeatNumber("3");
        checkin.setGateNumber("TestGate3");

        return checkin;
    }
}