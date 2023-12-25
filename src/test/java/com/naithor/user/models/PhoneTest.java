package com.naithor.user.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhoneTest {

    @Test
    void givenNumber_shouldReturnNumber() {
        long number = 123L;
        Phone phone = Phone.builder()
                .number(number)
                .build();

        long result = phone.getNumber();

        assertEquals(number, result);
    }

    @Test
    void givenCityCode_shouldReturnCityCode() {
        int cityCode = 1;
        Phone phone = Phone.builder()
                .cityCode(cityCode)
                .build();

        int result = phone.getCityCode();

        assertEquals(cityCode, result);
    }

    @Test
    void givenCountryCode_shouldReturnCountryCode() {
        String countryCode = "CC";
        Phone phone = Phone.builder()
                .countryCode(countryCode)
                .build();

        String result = phone.getCountryCode();

        assertEquals(countryCode, result);
    }

}
