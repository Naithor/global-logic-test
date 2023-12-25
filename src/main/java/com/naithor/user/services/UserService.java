package com.naithor.user.services;

import com.naithor.user.config.JsonWebTokenConfig;
import com.naithor.user.entities.PhoneEntity;
import com.naithor.user.entities.UserEntity;
import com.naithor.user.models.UserContractResponse;
import com.naithor.user.models.UserCreatedResponse;
import com.naithor.user.models.UserRequest;
import com.naithor.user.repositories.UserRepository;
import com.naithor.user.utils.Dto;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    public static final String ERROR_USER_NOT_FOUND = "Error: User not found!";
    private UserRepository userRepository;

    private JsonWebTokenConfig jsonWebTokenConfig;

    private EmailService emailService;

    private PasswordService passwordService;

    private PhoneService phoneService;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setJsonWebTokenConfig(JsonWebTokenConfig jsonWebTokenConfig) {
        this.jsonWebTokenConfig = jsonWebTokenConfig;
    }

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Autowired
    public void setPasswordService(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @Autowired
    public void setPhoneService(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    public UserCreatedResponse create(UserRequest userRequest) {
        emailService.validateEmail(userRequest);
        passwordService.validatePassword(userRequest);

        List<PhoneEntity> phoneEntities = phoneService.getPhoneEntities(userRequest);
        jsonWebTokenConfig.initialize();
        String jsonWebToken = jsonWebTokenConfig.createJWT();
        UserEntity userEntity = Dto.userRequestModelToUserEntity(userRequest, phoneEntities, jsonWebToken);
        userRepository.save(userEntity);

        return Dto.userEntityToUserResponse(userEntity);
    }

    public UserContractResponse read(UUID uuid) throws NotFoundException {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(uuid);
        UserEntity userEntity = optionalUserEntity.orElseThrow(() -> new NotFoundException(ERROR_USER_NOT_FOUND));

        return Dto.userEntityToUserContractResponse(userEntity);
    }

}
