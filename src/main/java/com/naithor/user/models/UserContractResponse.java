package com.naithor.user.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserContractResponse {

    private UserRequest userRequest;

    private UserCreatedResponse userCreatedResponse;

}
