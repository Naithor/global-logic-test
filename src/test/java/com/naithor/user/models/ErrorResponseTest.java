package com.naithor.user.models;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ErrorResponseTest {

    @Test
    void givenTimestamp_shouldReturnTimestamp() {
        Date timestamp = new Date();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(timestamp)
                .build();

        Date result = errorResponse.getTimestamp();

        assertEquals(timestamp, result);
    }

    @Test
    void givenStatusCode_shouldReturnStatusCode() {
        int statusCode = 1;
        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(statusCode)
                .build();

        int result = errorResponse.getStatusCode();

        assertEquals(statusCode, result);
    }

    @Test
    void givenMessage_shouldReturnMessage() {
        String message = "message";
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(message)
                .build();

        String result = errorResponse.getMessage();

        assertEquals(message, result);
    }

}
