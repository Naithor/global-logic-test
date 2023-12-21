package com.naithor.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;


@Builder
@Getter
public class ErrorResponse {

    @JsonProperty("timestamp")
    private Date timestamp;

    @JsonProperty("code")
    private int statusCode;

    @JsonProperty("message")
    private String message;

}
