package com.dhanush.airline.service;

import com.dhanush.airline.dao.UserDao;
import com.dhanush.airline.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock private UserDao userDaoMock;

    @InjectMocks private UserService userService;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void givenUserService_whenSaveUserIsCalled_thenReturnUser() {
        final User user = userData();

        when(userDaoMock.save(user)).thenReturn(user);
        userService.registerForm(user);
        verify(userDaoMock, times(1)).save(user);
    }

    @Test
    void givenUserService_whenSearchAnUser_thenReturnAnUser() {
        final User user = userData();
        final String username = "TestUser";
        when(userDaoMock.findByUserName(username)).thenReturn(user);

        User userResponse = userService.findByUserName(username);

        verify(userDaoMock).findByUserName(username);

        assertEquals(user.getUserName(), userResponse.getUserName());
    }
    private User userData() {
        User user = new User();

        user.setUserName("TestUser");
        user.setId(1L);
        user.setPassword("TestPassword");

        return user;
    }
}