package com.naithor.user.services;

import com.naithor.user.models.UserRequest;
import com.naithor.user.utils.RegexValidation;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PasswordService {

    private static final String REGEX_PASSWORD_PATTERN = "^(?=[^A-Z]*[A-Z])(?=(?:[^\\d]*\\d){2})(?!.*\\s)[a-zA-Z\\d]{8,12}$";
    public static final String ERROR_EMPTY_PASSWORD = "Error: Password cannot be empty!";
    public static final String ERROR_INVALID_PASSWORD = "Error: Password invalid!";

    public void validatePassword(UserRequest userRequest) {
        String password = userRequest.getPassword();
        validateEmptyPassword(password);
        validateValidPassword(password);
    }

    protected void validateEmptyPassword(String password) {
        if (password.isBlank()) {
            throw new NoSuchElementException(ERROR_EMPTY_PASSWORD);
        }
    }

    protected void validateValidPassword(String email) {
        if (RegexValidation.regexValidation(email, REGEX_PASSWORD_PATTERN)) {
            throw new IllegalArgumentException(ERROR_INVALID_PASSWORD);
        }
    }

}
