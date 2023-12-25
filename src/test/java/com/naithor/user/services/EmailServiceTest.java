package com.naithor.user.services;

import com.naithor.user.entities.UserEntity;
import com.naithor.user.models.UserRequest;
import com.naithor.user.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import java.util.Collections;
import java.util.NoSuchElementException;

import static com.naithor.user.services.EmailService.ERROR_EMPTY_EMAIL;
import static com.naithor.user.services.EmailService.ERROR_INVALID_EMAIL;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Spy
    EmailService emailService;

    @Mock
    UserRepository userRepository;

    @Test
    void givenUserRequest_shouldCallValidationMethods() {
        UserRequest userRequest = UserRequest.builder()
                .email("user@email.com")
                .password("password")
                .build();
        doNothing().when(emailService).validateEmptyEmail(anyString());
        doNothing().when(emailService).validateValidEmail(anyString());
        doNothing().when(emailService).validateExistingEmail(anyString());

        emailService.validateEmail(userRequest);

        verify(emailService, times(1)).validateEmptyEmail(anyString());
        verify(emailService, times(1)).validateValidEmail(anyString());
        verify(emailService, times(1)).validateExistingEmail(anyString());
    }

    @Test
    void givenEmptyEmail_shouldThrowNoSuchElementException() {
        String email = "";

        NoSuchElementException result = assertThrows(NoSuchElementException.class,
                () -> emailService.validateEmptyEmail(email));

        assertTrue(result.getMessage().contains(ERROR_EMPTY_EMAIL));
    }

    @Test
    void givenInvalidEmail_shouldThrowIllegalArgumentException() {
        String email = "";

        IllegalArgumentException result = assertThrows(IllegalArgumentException.class,
                () -> emailService.validateValidEmail(email));

        assertTrue(result.getMessage().contains(ERROR_INVALID_EMAIL));
    }

    @Test
    void givenExistingEmail_shouldThrowDuplicateKeyException() {
        emailService.setUserRepository(userRepository);
        String email = "user@email.com";
        doReturn(Collections.singletonList(new UserEntity())).when(userRepository).findByEmail(anyString());

        assertThrows(DuplicateKeyException.class, () -> emailService.validateExistingEmail(email));

        verify(userRepository, times(1)).findByEmail(anyString());
    }

}
