package com.naithor.user.services;

import com.naithor.user.models.UserRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

import static com.naithor.user.services.PasswordService.ERROR_EMPTY_PASSWORD;
import static com.naithor.user.services.PasswordService.ERROR_INVALID_PASSWORD;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PasswordServiceTest {

    @Spy
    PasswordService passwordService;

    @Test
    void givenUserRequest_shouldCallValidationMethods() {
        UserRequest userRequest = UserRequest.builder()
                .email("user@email.com")
                .password("password")
                .build();
        doNothing().when(passwordService).validateEmptyPassword(anyString());
        doNothing().when(passwordService).validateValidPassword(anyString());

        passwordService.validatePassword(userRequest);

        verify(passwordService, times(1)).validateEmptyPassword(anyString());
        verify(passwordService, times(1)).validateValidPassword(anyString());
    }

    @Test
    void givenEmptyPassword_shouldThrowNoSuchElementException() {
        String password = "";

        NoSuchElementException result = assertThrows(NoSuchElementException.class,
                () -> passwordService.validateEmptyPassword(password));

        assertTrue(result.getMessage().contains(ERROR_EMPTY_PASSWORD));
    }

    @Test
    void givenInvalidPassword_shouldThrowIllegalArgumentException() {
        String password = "123";

        IllegalArgumentException result = assertThrows(IllegalArgumentException.class,
                () -> passwordService.validateValidPassword(password));

        assertTrue(result.getMessage().contains(ERROR_INVALID_PASSWORD));
    }

}
