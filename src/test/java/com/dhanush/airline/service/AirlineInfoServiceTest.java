package com.dhanush.airline.service;

import com.dhanush.airline.dao.AirlineInfoDao;
import com.dhanush.airline.entity.AirlineInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
class AirlineInfoServiceTest {

    @Mock
    private AirlineInfoDao infoDaoMock;

    @Autowired
    @InjectMocks
    private AirlineInfoService airlineInfoService;


    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void givenAirlineInfoService_whenIsCalled_thenReturnNameOfAirline() {
        final String nameAirline = "TestName";

        when(infoDaoMock.findByNameOfAirline(nameAirline)).thenReturn(testData());
        AirlineInfo airlineInfo = airlineInfoService.findByNameOfAirline(nameAirline);

        verify(infoDaoMock).findByNameOfAirline(nameAirline);
        assertEquals(testData().getNameOfAirline(), airlineInfo.getNameOfAirline());
    }

    private AirlineInfo testData() {
        AirlineInfo airlineInfo = new AirlineInfo();
        airlineInfo.setNameOfAirline("TestName");
        airlineInfo.setAirlineId(1L);
        airlineInfo.setAirlineLogo("TestLogo");

        return airlineInfo;
    }
}