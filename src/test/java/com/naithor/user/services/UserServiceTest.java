package com.naithor.user.services;

import com.naithor.user.config.JsonWebTokenConfig;
import com.naithor.user.entities.PhoneEntity;
import com.naithor.user.entities.UserEntity;
import com.naithor.user.models.Phone;
import com.naithor.user.models.UserContractResponse;
import com.naithor.user.models.UserCreatedResponse;
import com.naithor.user.models.UserRequest;
import com.naithor.user.repositories.UserRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.naithor.user.services.UserService.ERROR_USER_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService
            userService;

    @Mock
    UserRepository userRepository;

    @Mock
    EmailService emailService;

    @Mock
    PasswordService passwordService;

    @Mock
    PhoneService phoneService;

    @Mock
    JsonWebTokenConfig jsonWebTokenConfig;

    @Test
    void givenUserRequest_shouldCreateNewUserAndReturnUserCreatedResponse() {
        UUID id = UUID.randomUUID();
        long number = 123L;
        int cityCode = 1;
        String countryCode = "CC";
        List<Phone> phones = Collections.singletonList(Phone.builder()
                .number(number)
                .cityCode(cityCode)
                .countryCode(countryCode)
                .build());
        String name = "name";
        String email = "user@email.com";
        String password = "password";
        String jsonWebToken = "token";
        UserRequest userRequest = UserRequest.builder()
                .name(name)
                .email(email)
                .password(password)
                .phones(phones)
                .build();
        List<PhoneEntity> phoneEntities = Collections.singletonList(PhoneEntity.builder()
                .id(id)
                .number(number)
                .cityCode(cityCode)
                .countryCode(countryCode)
                .build());
        UserEntity userEntity = UserEntity.builder().build();
        doNothing().when(emailService).validateEmail(any());
        doNothing().when(passwordService).validatePassword(any());
        when(phoneService.getPhoneEntities(any())).thenReturn(phoneEntities);
        doNothing().when(jsonWebTokenConfig).initialize();
        when(jsonWebTokenConfig.createJWT()).thenReturn(jsonWebToken);
        when(userRepository.save(any())).thenReturn(userEntity);

        UserCreatedResponse result = userService.create(userRequest);

        verify(emailService, times(1)).validateEmail(any());
        verify(passwordService, times(1)).validatePassword(any());
        verify(phoneService, times(1)).getPhoneEntities(any());
        verify(jsonWebTokenConfig, times(1)).initialize();
        verify(jsonWebTokenConfig, times(1)).createJWT();
        verify(userRepository, times(1)).save(any());
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getCreatedAt());
        assertNull(result.getLastLogin());
        assertEquals(jsonWebToken, result.getJsonWebToken());
        assertTrue(result.isActive());
    }

    @Test
    void givenId_shouldReturnUserContractResponse() throws NotFoundException {
        UUID id = UUID.randomUUID();
        LocalDate createdAt = LocalDate.now();
        LocalDate lastLogin = LocalDate.now();
        String jsonWebToken = "token";
        boolean isActive = true;
        String name = "name";
        String email = "user@email.com";
        String password = "password";
        long number = 123L;
        int cityCode = 1;
        String countryCode = "CC";
        List<PhoneEntity> phoneEntities = Collections.singletonList(PhoneEntity.builder()
                .id(id)
                .number(number)
                .cityCode(cityCode)
                .countryCode(countryCode)
                .build());
        Optional<UserEntity> optionalUserEntity = Optional.of(UserEntity.builder()
                .id(id)
                .createdAt(createdAt)
                .lastLogin(lastLogin)
                .jsonWebToken(jsonWebToken)
                .isActive(isActive)
                .name(name)
                .email(email)
                .password(password)
                .phones(phoneEntities)
                .build());
        when(userRepository.findById(any())).thenReturn(optionalUserEntity);

        UserContractResponse result = userService.read(id);

        verify(userRepository, times(1)).findById(any());
        assertNotNull(result);
        assertEquals(name, result.getUserRequest().getName());
        assertEquals(email, result.getUserRequest().getEmail());
        assertEquals(password, result.getUserRequest().getPassword());
        assertFalse(result.getUserRequest().getPhones().isEmpty());
        assertEquals(1, result.getUserRequest().getPhones().size());
        assertEquals(number, result.getUserRequest().getPhones().get(0).getNumber());
        assertEquals(cityCode, result.getUserRequest().getPhones().get(0).getCityCode());
        assertEquals(countryCode, result.getUserRequest().getPhones().get(0).getCountryCode());
        assertEquals(id, result.getUserCreatedResponse().getId());
        assertEquals(createdAt, result.getUserCreatedResponse().getCreatedAt());
        assertEquals(lastLogin, result.getUserCreatedResponse().getLastLogin());
        assertEquals(jsonWebToken, result.getUserCreatedResponse().getJsonWebToken());
        assertEquals(isActive, result.getUserCreatedResponse().isActive());
    }

    @Test
    void givenId_shouldThrowNotFoundException() throws NotFoundException {
        UUID id = UUID.randomUUID();
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        NotFoundException result = assertThrows(NotFoundException.class, () -> userService.read(id));

        verify(userRepository, times(1)).findById(any());
        assertTrue(result.getMessage().contains(ERROR_USER_NOT_FOUND));
    }

}
