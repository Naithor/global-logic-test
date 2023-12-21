package com.naithor.user.services;

import com.naithor.user.entities.PhoneEntity;
import com.naithor.user.models.UserRequest;
import com.naithor.user.utils.Dto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneService {

    public List<PhoneEntity> getPhoneEntities(UserRequest userRequest) {
        return userRequest.getPhones()
                .stream()
                .map(Dto::phoneModelToPhoneEntity)
                .collect(Collectors.toList());
    }

}
