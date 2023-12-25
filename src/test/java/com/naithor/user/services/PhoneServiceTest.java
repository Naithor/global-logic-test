package com.naithor.user.services;

import com.naithor.user.entities.PhoneEntity;
import com.naithor.user.models.Phone;
import com.naithor.user.models.UserRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class PhoneServiceTest {

    @InjectMocks
    PhoneService phoneService;

    @Test
    void givenUserRequest_shouldReturnPhoneEntityList() {
        List<Phone> phones = Collections.singletonList(Phone.builder()
                .number(123L)
                .cityCode(1)
                .countryCode("CC")
                .build());

        UserRequest userRequest = UserRequest.builder()
                .email("user@email.com")
                .password("password")
                .phones(phones)
                .build();

        List<PhoneEntity> result = phoneService.getPhoneEntities(userRequest);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertNotNull(result.get(0).getId());
        assertEquals(phones.get(0).getNumber(), result.get(0).getNumber());
        assertEquals(phones.get(0).getCityCode(), result.get(0).getCityCode());
        assertEquals(phones.get(0).getCountryCode(), result.get(0).getCountryCode());
    }

}
