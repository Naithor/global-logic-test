package com.naithor.user.entities;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PhoneEntityTest {

    @Test
    void givenId_shouldReturnId() {
        UUID id = UUID.randomUUID();
        PhoneEntity phoneEntity = PhoneEntity.builder()
                .id(id)
                .build();

        UUID result = phoneEntity.getId();

        assertEquals(id, result);
    }

    @Test
    void givenNumber_shouldReturnNumber() {
        long number = 123L;
        PhoneEntity phoneEntity = PhoneEntity.builder()
                .number(number)
                .build();

        long result = phoneEntity.getNumber();

        assertEquals(number, result);
    }

    @Test
    void givenCityCode_shouldReturnCityCode() {
        int cityCode = 1;
        PhoneEntity phoneEntity = PhoneEntity.builder()
                .cityCode(cityCode)
                .build();

        int result = phoneEntity.getCityCode();

        assertEquals(cityCode, result);
    }

    @Test
    void givenCountryCode_shouldReturnCountryCode() {
        String countryCode = "CC";

        PhoneEntity phoneEntity = PhoneEntity.builder()
                .countryCode(countryCode)
                .build();

        String result = phoneEntity.getCountryCode();

        assertEquals(countryCode, result);
    }

    @Test
    void givenEmptyPhoneEntity_shouldReturnEmptyPhoneEntity() {
        PhoneEntity result = new PhoneEntity();

        assertNull(result.getId());
        assertNull(result.getCountryCode());
        assertEquals(0L, result.getNumber());
        assertEquals(0, result.getCityCode());
    }

}
