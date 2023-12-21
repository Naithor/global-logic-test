package com.naithor.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserContractResponse {

    @JsonProperty("userInfo")
    private UserRequest userRequest;

    @JsonProperty("userMetadata")
    private UserCreatedResponse userCreatedResponse;

}
