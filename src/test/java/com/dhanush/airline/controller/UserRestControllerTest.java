package com.dhanush.airline.controller;

import com.dhanush.airline.entity.User;
import com.dhanush.airline.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


@ExtendWith(MockitoExtension.class)
class UserRestControllerTest {

    @Mock private UserService mockUserService;
    @Mock private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    @InjectMocks private UserRestController userRestController;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void givenUserController_whenRegisterIsCalled_thenOk(){
        User user = createUser();
        doNothing().when(mockUserService).registerForm(user);

        User userResponse = userRestController.registerFormSubmit(user);

        verify(mockUserService).registerForm(user);

        assertEquals(user.getUserName(), userResponse.getUserName());
    }

    private User createUser() {
        User user = new User();

        user.setUserName("UserTest");
        user.setFirstName("TestName");
        user.setPassword("PasswordTest");
        user.setGender("TestGender");
        user.setLastName("TestLastName");
        user.setId(1L);
        user.setMobileNumber(1L);

        return user;
    }
}