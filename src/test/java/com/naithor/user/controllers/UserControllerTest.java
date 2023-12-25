package com.naithor.user.controllers;

import com.naithor.user.models.Phone;
import com.naithor.user.models.UserRequest;
import com.naithor.user.services.UserService;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Test
    void givenUserRequest_shouldCallCreateMethod() {
        List<Phone> phones = Collections.singletonList(Phone.builder()
                .number(123L)
                .cityCode(1)
                .countryCode("CC")
                .build());

        UserRequest userRequest = UserRequest.builder()
                .name("name")
                .email("user@email.com")
                .password("password")
                .phones(phones)
                .build();

        when(userController.create(any())).thenReturn(any());

        userController.create(userRequest);

        verify(userService, times(1)).create(any());
    }

    @Test
    void givenUserId_shouldCallReadMethod() throws NotFoundException {
        UUID id = UUID.randomUUID();

        userController.read(id);

        verify(userService, times(1)).read(any());
    }

}
