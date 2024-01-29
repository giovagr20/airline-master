package com.dhanush.airline.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;


@ExtendWith(MockitoExtension.class)
class AppControllerTest {
    @Spy private AppController appController;


    @Test
    void whenHomePageIsCalled_thenReturnSearchFlightsView() {
        final String searchFlights = "searchFlights";

        String homepage = appController.homepage();

        assertEquals(searchFlights, homepage);
    }

    @Test
    void whenLoginPageIsCalled_thenReturnLoginView() {
        final String login = "login";

        String loginView = appController.login();

        assertEquals(login, loginView);
    }

    @Test
    void whenRegisterPageIsCalled_thenReturnRegisterView() {
        final String register = "register";

        String registerView = appController.register();

        assertEquals(register, registerView);
    }

    @Test
    void whenBookFlightPageIsCalled_thenReturnBookFlightView() {
        final String bookFlight = "c";

        String bookFlightView = appController.bookFlight();

        assertEquals(bookFlightView, bookFlight);
    }

    @Test
    void whenCheckingIsCalled_thenReturnCheckingView() {
        final String checking = "checkin";

        String checkingView = appController.checkinPage();

        assertEquals(checkingView, checking);
    }
}