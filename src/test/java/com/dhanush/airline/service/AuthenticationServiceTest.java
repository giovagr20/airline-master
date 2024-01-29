package com.dhanush.airline.service;

import com.dhanush.airline.dao.UserDao;
import com.dhanush.airline.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock private UserDao userDaoMock;
    @InjectMocks private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void givenAuthenticationService_whenIsCalled_thenReturnUserLogged() {
        final String userName = "testName";
        final String password = "password";
        final String roleName = "ROLE_USER";
        final Set<String> rolesMock = new HashSet<>();
        rolesMock.add(roleName);
        User mockedUser = new User();

        mockedUser.setPassword(password);
        mockedUser.setUserName(userName);

        when(userDaoMock.findByUserName(userName)).thenReturn(mockedUser);

        UserDetails userDetails = authenticationService.loadUserByUsername(userName);

        verify(userDaoMock, times(1)).findByUserName(userName);

        assertEquals(userDetails.getUsername(), userName);
        assertEquals(userDetails.getPassword(), password);

        assertTrue(userDetails.getAuthorities()
                .stream().allMatch(aut -> rolesMock.contains(aut.getAuthority())));
    }

    @Test
    void givenAuthenticationService_whenIsCalled_thenThrowsAnException() {
        final String notExistUsername = "noExistUser";

        doThrow(UsernameNotFoundException.class)
                .when(userDaoMock).findByUserName(notExistUsername);

        assertThrows(UsernameNotFoundException.class, () -> authenticationService.loadUserByUsername(notExistUsername));
    }

}