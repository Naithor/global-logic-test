package com.naithor.user.utils;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.naithor.user.models.ErrorResponse;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RestExceptionHandlerTest {

    @Test
    void givenNoSuchElementException_shouldReturnResponseEntity() {
        String errorMessage = "error message";
        RestExceptionHandler exceptionHandler = new RestExceptionHandler();
        NoSuchElementException exception = new NoSuchElementException(errorMessage);

        ResponseEntity<Object> result = exceptionHandler.handleNoSuchElementException(exception);
        ErrorResponse errorResponse = (ErrorResponse) result.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertNotNull(errorResponse);
        assertNotNull(errorResponse.getTimestamp());
        assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getStatusCode());
        assertEquals(errorMessage, errorResponse.getMessage());

    }

    @Test
    void givenIllegalArgumentException_shouldReturnResponseEntity() {
        String errorMessage = "error message";
        RestExceptionHandler exceptionHandler = new RestExceptionHandler();
        IllegalArgumentException exception = new IllegalArgumentException(errorMessage);

        ResponseEntity<Object> result = exceptionHandler.handleIllegalArgumentException(exception);
        ErrorResponse errorResponse = (ErrorResponse) result.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertNotNull(errorResponse);
        assertNotNull(errorResponse.getTimestamp());
        assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getStatusCode());
        assertEquals(errorMessage, errorResponse.getMessage());
    }

    @Test
    void givenDuplicateKeyException_shouldReturnResponseEntity() {
        String errorMessage = "error message";
        RestExceptionHandler exceptionHandler = new RestExceptionHandler();
        DuplicateKeyException exception = new DuplicateKeyException(errorMessage);

        ResponseEntity<Object> result = exceptionHandler.handleDuplicateKeyException(exception);
        ErrorResponse errorResponse = (ErrorResponse) result.getBody();

        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
        assertNotNull(errorResponse);
        assertNotNull(errorResponse.getTimestamp());
        assertEquals(HttpStatus.CONFLICT.value(), errorResponse.getStatusCode());
        assertEquals(errorMessage, errorResponse.getMessage());
    }

    @Test
    void givenNotFoundException_shouldReturnResponseEntity() {
        String errorMessage = "error message";
        RestExceptionHandler exceptionHandler = new RestExceptionHandler();
        NotFoundException exception = new NotFoundException(errorMessage);

        ResponseEntity<Object> result = exceptionHandler.handleNotFoundException(exception);
        ErrorResponse errorResponse = (ErrorResponse) result.getBody();

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNotNull(errorResponse);
        assertNotNull(errorResponse.getTimestamp());
        assertEquals(HttpStatus.NOT_FOUND.value(), errorResponse.getStatusCode());
        assertEquals(errorMessage, errorResponse.getMessage());
    }

    @Test
    void givenJWTVerificationException_shouldReturnResponseEntity() {
        String errorMessage = "error message";
        RestExceptionHandler exceptionHandler = new RestExceptionHandler();
        JWTVerificationException exception = new JWTVerificationException(errorMessage);

        ResponseEntity<Object> result = exceptionHandler.handleJWTVerificationException(exception);
        ErrorResponse errorResponse = (ErrorResponse) result.getBody();

        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
        assertNotNull(errorResponse);
        assertNotNull(errorResponse.getTimestamp());
        assertEquals(HttpStatus.UNAUTHORIZED.value(), errorResponse.getStatusCode());
        assertEquals(errorMessage, errorResponse.getMessage());
    }

    @Test
    void givenJWTDecodeException_shouldReturnResponseEntity() {
        String errorMessage = "error message";
        RestExceptionHandler exceptionHandler = new RestExceptionHandler();
        JWTDecodeException exception = new JWTDecodeException(errorMessage);

        ResponseEntity<Object> result = exceptionHandler.handleJWTDecodeException(exception);
        ErrorResponse errorResponse = (ErrorResponse) result.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertNotNull(errorResponse);
        assertNotNull(errorResponse.getTimestamp());
        assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getStatusCode());
        assertEquals(errorMessage, errorResponse.getMessage());
    }

}
