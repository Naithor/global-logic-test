package com.naithor.user.utils;

import com.naithor.user.entities.PhoneEntity;
import com.naithor.user.entities.UserEntity;
import com.naithor.user.models.Phone;
import com.naithor.user.models.UserContractResponse;
import com.naithor.user.models.UserCreatedResponse;
import com.naithor.user.models.UserRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DtoTest {

    @Test
    void givenUserRequestModel_shouldReturnUserEntity() {
        UUID id = UUID.randomUUID();
        long number = 123L;
        int cityCode = 1;
        String countryCode = "CC";
        String jsonWebToken = "token";
        String name = "name";
        String email = "user@email.com";
        String password = "password";
        List<PhoneEntity> phoneEntities = Collections.singletonList(PhoneEntity.builder()
                .id(id)
                .number(number)
                .cityCode(cityCode)
                .countryCode(countryCode)
                .build());
        List<Phone> phones = Collections.singletonList(Phone.builder()
                .number(number)
                .cityCode(cityCode)
                .countryCode(countryCode)
                .build());
        UserRequest userRequest = UserRequest.builder()
                .name(name)
                .email(email)
                .password(password)
                .phones(phones)
                .build();

        UserEntity result = Dto.userRequestModelToUserEntity(userRequest, phoneEntities, jsonWebToken);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getCreatedAt());
        assertEquals(jsonWebToken, result.getJsonWebToken());
        assertTrue(result.isActive());
        assertEquals(name, result.getName());
        assertEquals(email, result.getEmail());
        assertEquals(password, result.getPassword());
        assertNotNull(result.getPhones());
        assertEquals(1, result.getPhones().size());
        assertEquals(id, result.getPhones().get(0).getId());
        assertEquals(number, result.getPhones().get(0).getNumber());
        assertEquals(cityCode, result.getPhones().get(0).getCityCode());
        assertEquals(countryCode, result.getPhones().get(0).getCountryCode());
    }

    @Test
    void givenPhoneModel_shouldReturnPhoneEntity() {
        long number = 123L;
        int cityCode = 1;
        String countryCode = "CC";
        Phone phone = Phone.builder()
                .number(number)
                .cityCode(cityCode)
                .countryCode(countryCode)
                .build();

        PhoneEntity result = Dto.phoneModelToPhoneEntity(phone);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(number, result.getNumber());
        assertEquals(cityCode, result.getCityCode());
        assertEquals(countryCode, result.getCountryCode());
    }

    @Test
    void givenUserEntity_shouldReturnUserCreatedResponse() {
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
        UserEntity userEntity = UserEntity.builder()
                .id(id)
                .createdAt(createdAt)
                .lastLogin(lastLogin)
                .jsonWebToken(jsonWebToken)
                .isActive(isActive)
                .name(name)
                .email(email)
                .password(password)
                .phones(phoneEntities)
                .build();

        UserCreatedResponse result = Dto.userEntityToUserResponse(userEntity);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(createdAt, result.getCreatedAt());
        assertEquals(lastLogin, result.getLastLogin());
        assertEquals(jsonWebToken, result.getJsonWebToken());
        assertTrue(result.isActive());
    }

    @Test
    void givenUserEntity_shouldReturnUserContractResponse() {
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
        UserEntity userEntity = UserEntity.builder()
                .id(id)
                .createdAt(createdAt)
                .lastLogin(lastLogin)
                .jsonWebToken(jsonWebToken)
                .isActive(isActive)
                .name(name)
                .email(email)
                .password(password)
                .phones(phoneEntities)
                .build();

        UserContractResponse result = Dto.userEntityToUserContractResponse(userEntity);

        assertNotNull(result);
        assertEquals(name, result.getUserRequest().getName());
        assertEquals(email, result.getUserRequest().getEmail());
        assertEquals(password, result.getUserRequest().getPassword());
        assertNotNull(result.getUserRequest().getPhones());
        assertEquals(1, result.getUserRequest().getPhones().size());
        assertEquals(number, result.getUserRequest().getPhones().get(0).getNumber());
        assertEquals(cityCode, result.getUserRequest().getPhones().get(0).getCityCode());
        assertEquals(countryCode, result.getUserRequest().getPhones().get(0).getCountryCode());
        assertEquals(id, result.getUserCreatedResponse().getId());
        assertEquals(createdAt, result.getUserCreatedResponse().getCreatedAt());
        assertEquals(lastLogin, result.getUserCreatedResponse().getLastLogin());
        assertEquals(jsonWebToken, result.getUserCreatedResponse().getJsonWebToken());
        assertTrue(result.getUserCreatedResponse().isActive());
    }

    @Test
    void givenPhoneEntity_shouldReturnPhoneModel() {
        UUID id = UUID.randomUUID();
        long number = 123L;
        int cityCode = 1;
        String countryCode = "CC";
        PhoneEntity phoneEntity = PhoneEntity.builder()
                .id(id)
                .number(number)
                .cityCode(cityCode)
                .countryCode(countryCode)
                .build();

        Phone result = Dto.phoneEntityToPhoneModel(phoneEntity);

        assertNotNull(result);
        assertEquals(number, result.getNumber());
        assertEquals(cityCode, result.getCityCode());
        assertEquals(countryCode, result.getCountryCode());
    }

}
