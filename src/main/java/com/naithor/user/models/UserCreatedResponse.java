package com.naithor.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
public class UserCreatedResponse {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("created")
    private LocalDate createdAt;

    @JsonProperty("lastLogin")
    private LocalDate lastLogin;

    @JsonProperty("token")
    private String jsonWebToken;

    @JsonProperty("isActive")
    private boolean isActive;

}
