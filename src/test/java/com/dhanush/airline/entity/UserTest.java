package com.dhanush.airline.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void givenEntityConstructor_thenCreateUser() {
        final User user = createUserViaConstructor();

        assertTrue(user.toString().contains("User"));
        assertEquals(user.getGender(), "M");
        assertEquals(user.getUserName(), "User");
        assertEquals(user.getPassword(), "Password");
        assertEquals(user.getFirstName(), "FirstName");
        assertEquals(user.getLastName(), "LastName");
        assertEquals(user.getMobileNumber(), 21L);
    }

    @Test
    void givenEntity_thenCreateUser() {
        final User user = createUser();

        assertTrue(user.toString().contains("User"));
        assertEquals(user.getUserName(), "User");
        assertEquals(user.getPassword(), "Password");
        assertEquals(user.getFirstName(), "FirstName");
        assertEquals(user.getLastName(), "LastName");
        assertEquals(user.getMobileNumber(), 21L);
        assertEquals(user.getId(), 1L);
    }
    private static User createUserViaConstructor() {
        return new User("FirstName", "LastName", 21L, "M", "User", "Password");
    }

    private static User createUser() {
        User user = new User();
        user.setMobileNumber(21L);
        user.setId(1L);
        user.setUserName("User");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setGender("M");
        user.setPassword("Password");

        return user;
    }
}