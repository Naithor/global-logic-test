package com.naithor.user.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {

    @Test
    void givenId_shouldReturnID() {
        UUID id = UUID.randomUUID();
        UserEntity userEntity = UserEntity.builder()
                .id(id)
                .build();

        UUID result = userEntity.getId();

        assertEquals(id, result);
    }

    @Test
    void givenCreatedAtDate_shouldReturnCreatedAtDate() {
        LocalDate createdAt = LocalDate.now();
        UserEntity userEntity = UserEntity.builder()
                .createdAt(createdAt)
                .build();

        LocalDate result = userEntity.getCreatedAt();

        assertEquals(createdAt, result);
    }

    @Test
    void givenLastLoginDate_shouldReturnLastLoginDate() {
        LocalDate lastLogin = LocalDate.now();
        UserEntity userEntity = UserEntity.builder()
                .lastLogin(lastLogin)
                .build();

        LocalDate result = userEntity.getLastLogin();

        assertEquals(lastLogin, result);
    }

    @Test
    void givenJsonWebToken_shouldReturnJsonWebToken() {
        String jsonWebToken = "token";
        UserEntity userEntity = UserEntity.builder()
                .jsonWebToken(jsonWebToken)
                .build();

        String result = userEntity.getJsonWebToken();

        assertEquals(jsonWebToken, result);
    }

    @Test
    void givenIsActiveStatus_shouldReturnIsActiveStatus() {
        boolean isActive = true;
        UserEntity userEntity = UserEntity.builder()
                .isActive(isActive)
                .build();

        boolean result = userEntity.isActive();

        assertEquals(isActive, result);
    }

    @Test
    void givenName_shouldReturnName() {
        String name = "name";
        UserEntity userEntity = UserEntity.builder()
                .name(name)
                .build();

        String result = userEntity.getName();

        assertEquals(name, result);
    }

    @Test
    void givenEmail_shouldReturnEmail() {
        String email = "user@email.com";
        UserEntity userEntity = UserEntity.builder()
                .email(email)
                .build();

        String result = userEntity.getEmail();

        assertEquals(email, result);
    }

    @Test
    void givenPassword_shouldReturnPassword() {
        String password = "password";
        UserEntity userEntity = UserEntity.builder()
                .password(password)
                .build();

        String result = userEntity.getPassword();

        assertEquals(password, result);
    }

    @Test
    void givenPhones_shouldReturnPhones() {
        UUID id = UUID.randomUUID();
        PhoneEntity phoneEntity = PhoneEntity.builder()
                .id(id)
                .build();

        UserEntity userEntity = UserEntity.builder()
                .phones(Collections.singletonList(phoneEntity))
                .build();

        List<PhoneEntity> result = userEntity.getPhones();

        assertEquals(1, result.size());
        assertEquals(id, result.get(0).getId());
    }

    @Test
    void givenEmptyUserEntity_shouldReturnEmptyUserEntity() {
        UserEntity result = new UserEntity();

        assertNull(result.getId());
        assertNull(result.getCreatedAt());
        assertNull(result.getLastLogin());
        assertNull(result.getJsonWebToken());
        assertNull(result.getName());
        assertNull(result.getEmail());
        assertNull(result.getPassword());
        assertNull(result.getPhones());
        assertFalse(result.isActive());
    }

}
