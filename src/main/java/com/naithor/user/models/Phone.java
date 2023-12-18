package com.naithor.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Phone {

    private long number;

    @JsonProperty("citycode")
    private int cityCode;

    @JsonProperty("countrycode")
    private String countryCode;

}
