package com.naithor.user.repositories;

import com.naithor.user.entities.PhoneEntity;
import com.naithor.user.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void givenEmail_shouldReturnUserEntityList() {
        String email = "user@email.com";
        List<PhoneEntity> phoneEntities = Collections.singletonList(PhoneEntity.builder()
                .id(UUID.randomUUID())
                .build());
        UserEntity userEntity = UserEntity.builder()
                .id(UUID.randomUUID())
                .createdAt(LocalDate.now())
                .lastLogin(LocalDate.now())
                .jsonWebToken("token")
                .isActive(true)
                .name("name")
                .email(email)
                .password("password")
                .phones(phoneEntities)
                .build();
        userRepository.save(userEntity);

        List<UserEntity> result = userRepository.findByEmail(email);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(userEntity.getId(), result.get(0).getId());
        assertEquals(userEntity.getCreatedAt(), result.get(0).getCreatedAt());
        assertEquals(userEntity.getLastLogin(), result.get(0).getLastLogin());
        assertEquals(userEntity.getJsonWebToken(), result.get(0).getJsonWebToken());
        assertEquals(userEntity.isActive(), result.get(0).isActive());
        assertEquals(userEntity.getName(), result.get(0).getName());
        assertEquals(userEntity.getEmail(), result.get(0).getEmail());
        assertEquals(userEntity.getPassword(), result.get(0).getPassword());
        assertEquals(userEntity.getPhones().get(0).getId(), result.get(0).getPhones().get(0).getId());
    }

}
