package com.naithor.user.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "PHONE")
public class PhoneEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "number")
    private long number;

    @Column(name = "city_code")
    private int cityCode;

    @Column(name = "country_code")
    private String countryCode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

}
