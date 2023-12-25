package com.naithor.user.services;

import com.naithor.user.entities.UserEntity;
import com.naithor.user.models.UserRequest;
import com.naithor.user.repositories.UserRepository;
import com.naithor.user.utils.RegexValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class
EmailService {

    public static final String REGEX_EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    public static final String ERROR_EMPTY_EMAIL = "Error: Email cannot be empty!";
    public static final String ERROR_INVALID_EMAIL = "Error: Email invalid!";
    public static final String ERROR_EXIST_USER = "Error: User already exist!";

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateEmail(UserRequest userRequest) {
        String email = userRequest.getEmail();
        validateEmptyEmail(email);
        validateValidEmail(email);
        validateExistingEmail(email);
    }

    protected void validateEmptyEmail(String email) {
        if (email.isBlank()) {
            throw new NoSuchElementException(ERROR_EMPTY_EMAIL);
        }
    }

    protected void validateValidEmail(String email) {
        if (RegexValidation.regexValidation(email, REGEX_EMAIL_PATTERN)) {
            throw new IllegalArgumentException(ERROR_INVALID_EMAIL);
        }
    }

    protected void validateExistingEmail(String email) {
        List<UserEntity> userEntityList = userRepository.findByEmail(email);
        if (!userEntityList.isEmpty()) {
            throw new DuplicateKeyException(ERROR_EXIST_USER);
        }
    }

}
