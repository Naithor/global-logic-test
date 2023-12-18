package com.naithor.user.services;

import com.naithor.user.entities.PhoneEntity;
import com.naithor.user.entities.UserEntity;
import com.naithor.user.models.UserRequest;
import com.naithor.user.models.UserContractResponse;
import com.naithor.user.models.UserCreatedResponse;
import com.naithor.user.repositories.UserRepository;
import com.naithor.user.utils.Dto;
import com.naithor.user.utils.RegexValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final String REGEX_EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final String REGEX_PASSWORD_PATTERN = "^(?=[^A-Z]*[A-Z])(?=(?:\\D*\\d){2,})(?=.*[a-z])\\S{8,12}$";

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserCreatedResponse create(UserRequest userRequest) {
        String email = userRequest.getEmail();
        isEmptyEmail(email);
        isValidEmail(email);

        String password = userRequest.getPassword();
        isEmptyPassword(password);
        isValidPassword(email);

        List<PhoneEntity> phoneEntities = userRequest.getPhones()
                .stream()
                .map(Dto::phoneModelToPhoneEntity)
                .collect(Collectors.toList());

        UserEntity userEntity = Dto.userResquestModelToUserEntity(userRequest, phoneEntities);
        userRepository.save(userEntity);

        return Dto.userEntityToUserResponse(userEntity);
    }

    private static void isEmptyEmail(String email) {
        if (email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }
    }

    private static void isValidEmail(String email) {
        if (RegexValidation.regexValidation(email, REGEX_EMAIL_PATTERN)) {
            throw new IllegalArgumentException("Email invalid.");
        }
    }

    private static void isEmptyPassword(String password) {
        if (password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
    }

    private static void isValidPassword(String email) {
        if (RegexValidation.regexValidation(email, REGEX_PASSWORD_PATTERN)) {
            throw new IllegalArgumentException("Password invalid.");
        }
    }

    public UserContractResponse read(UUID uuid) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(uuid);
        UserEntity userEntity = optionalUserEntity.orElseThrow();

        return Dto.userEntityToUserContractResponse(userEntity);
    }

}
