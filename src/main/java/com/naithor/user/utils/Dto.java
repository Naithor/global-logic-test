package com.naithor.user.utils;

import com.naithor.user.entities.PhoneEntity;
import com.naithor.user.entities.UserEntity;
import com.naithor.user.models.Phone;
import com.naithor.user.models.UserContractResponse;
import com.naithor.user.models.UserCreatedResponse;
import com.naithor.user.models.UserRequest;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@UtilityClass
public class Dto {

    public static UserEntity userRequestModelToUserEntity(UserRequest userRequest,
                                                          List<PhoneEntity> phones,
                                                          String jsonWebToken) {
        return UserEntity.builder()
                .id(UUID.randomUUID())
                .createdAt(LocalDate.now())
                .jsonWebToken(jsonWebToken)
                .isActive(true)
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .phones(phones)
                .build();
    }

    public static PhoneEntity phoneModelToPhoneEntity(Phone phone) {
        return PhoneEntity.builder()
                .id(UUID.randomUUID())
                .number(phone.getNumber())
                .cityCode(phone.getCityCode())
                .countryCode(phone.getCountryCode())
                .build();
    }

    public static UserCreatedResponse userEntityToUserResponse(UserEntity userEntity) {
        return UserCreatedResponse.builder()
                .id(userEntity.getId())
                .createdAt(userEntity.getCreatedAt())
                .lastLogin(userEntity.getLastLogin())
                .jsonWebToken(userEntity.getJsonWebToken())
                .isActive(userEntity.isActive())
                .build();
    }

    public static UserContractResponse userEntityToUserContractResponse(UserEntity userEntity) {
        List<Phone> phones = userEntity.getPhones()
                .stream()
                .map(Dto::phoneEntityToPhoneModel)
                .collect(Collectors.toList());

        UserRequest userRequest = UserRequest.builder()
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .phones(phones)
                .build();

        UserCreatedResponse userCreatedResponse = UserCreatedResponse.builder()
                .id(userEntity.getId())
                .createdAt(userEntity.getCreatedAt())
                .lastLogin(LocalDate.now())
                .jsonWebToken(userEntity.getJsonWebToken())
                .isActive(userEntity.isActive())
                .build();

        return UserContractResponse.builder()
                .userRequest(userRequest)
                .userCreatedResponse(userCreatedResponse)
                .build();
    }

    public static Phone phoneEntityToPhoneModel(PhoneEntity phoneEntity) {
        return Phone.builder()
                .number(phoneEntity.getNumber())
                .cityCode(phoneEntity.getCityCode())
                .countryCode(phoneEntity.getCountryCode())
                .build();
    }

}
