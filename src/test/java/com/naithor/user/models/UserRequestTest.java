package com.naithor.user.models;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRequestTest {

    @Test
    void giveName_shouldReturnName() {
        String email = "user@email.com";
        String password = "password";
        String name = "name";
        UserRequest userRequest = UserRequest.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();

        String result = userRequest.getName();

        assertEquals(name, result);
    }

    @Test
    void givenEmail_shouldReturnEmail() {
        String email = "user@email.com";
        String password = "password";
        UserRequest userRequest = UserRequest.builder()
                .email(email)
                .password(password)
                .build();

        String result = userRequest.getEmail();

        assertEquals(email, result);
    }

    @Test
    void givenPassword_shouldReturnPassword() {
        String email = "user@email.com";
        String password = "password";
        UserRequest userRequest = UserRequest.builder()
                .email(email)
                .password(password)
                .build();

        String result = userRequest.getPassword();

        assertEquals(password, result);
    }

    @Test
    void givenPhones_shouldReturnPhones() {
        String email = "email";
        String password = "password";
        List<Phone> phones = Collections.singletonList(Phone.builder().build());
        UserRequest userRequest = UserRequest.builder()
                .email(email)
                .password(password)
                .phones(phones)
                .build();

        List<Phone> result = userRequest.getPhones();

        assertEquals(1, result.size());
        assertEquals(phones, result);
    }

}
