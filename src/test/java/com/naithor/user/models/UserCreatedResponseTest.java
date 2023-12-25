package com.naithor.user.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserCreatedResponseTest {

    @Test
    void givenId_shouldReturnId() {
        UUID id = UUID.randomUUID();
        UserCreatedResponse userCreatedResponse = UserCreatedResponse.builder()
                .id(id)
                .build();

        UUID result = userCreatedResponse.getId();

        assertEquals(id, result);
    }

    @Test
    void givenCreatedAtDate_shouldCreatedAtDate() {
        LocalDate createdAt = LocalDate.now();
        UserCreatedResponse userCreatedResponse = UserCreatedResponse.builder()
                .createdAt(createdAt)
                .build();

        LocalDate result = userCreatedResponse.getCreatedAt();

        assertEquals(createdAt, result);
    }

    @Test
    void givenLastLoginDate_shouldLastLoginDate() {
        LocalDate lastLogin = LocalDate.now();
        UserCreatedResponse userCreatedResponse = UserCreatedResponse.builder()
                .lastLogin(lastLogin)
                .build();

        LocalDate result = userCreatedResponse.getLastLogin();

        assertEquals(lastLogin, result);
    }

    @Test
    void givenJsonWebToken_shouldReturnJsonWebToken() {
        String jsonWebToken = "token";
        UserCreatedResponse userCreatedResponse = UserCreatedResponse.builder()
                .jsonWebToken(jsonWebToken)
                .build();

        String result = userCreatedResponse.getJsonWebToken();

        assertEquals(jsonWebToken, result);
    }

    @Test
    void givenIsActiveStatus_shouldReturnIsActiveStatus() {
        boolean isActive = true;
        UserCreatedResponse userCreatedResponse = UserCreatedResponse.builder()
                .isActive(isActive)
                .build();

        boolean result = userCreatedResponse.isActive();

        assertTrue(result);
    }

}
