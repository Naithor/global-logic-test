package com.naithor.user.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserContractResponseTest {

    @Test
    void givenUserRequest_shouldReturnUserRequest() {
        String email = "email";
        String password = "password";
        UserRequest userRequest = UserRequest.builder()
                .email(email)
                .password(password)
                .build();
        UserContractResponse userContractResponse = UserContractResponse.builder()
                .userRequest(userRequest)
                .build();

        UserRequest result = userContractResponse.getUserRequest();

        assertEquals(userRequest, result);
    }

    @Test
    void givenUserCreatedResponse_shouldReturnUserCreatedResponse() {
        UserCreatedResponse userCreatedResponse = UserCreatedResponse.builder()
                .build();
        UserContractResponse userContractResponse = UserContractResponse.builder()
                .userCreatedResponse(userCreatedResponse)
                .build();

        UserCreatedResponse result = userContractResponse.getUserCreatedResponse();

        assertEquals(userCreatedResponse, result);
    }

}
