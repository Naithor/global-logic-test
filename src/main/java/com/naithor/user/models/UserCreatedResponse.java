package com.naithor.user.models;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
public class UserCreatedResponse {

    private UUID id;

    private LocalDate created;

    private LocalDate lastLogin;

    private String token;

    private boolean isActive;

}
