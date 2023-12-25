package com.naithor.user.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static com.naithor.user.config.JsonWebTokenConfig.ISSUER;
import static com.naithor.user.config.JsonWebTokenConfig.SECRET;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JsonWebTokenConfigTest {

    @InjectMocks
    JsonWebTokenConfig jsonWebTokenConfig;


    @Test
    void initialize() {
    }

    @Test
    void shouldCreateJWT() {
        jsonWebTokenConfig.initialize();

        String result = jsonWebTokenConfig.createJWT();

        assertNotNull(result);
    }

    @Test
    void givenJsonWebToken_shouldVerifyJWTAndReturnDecodedJWT() {
        jsonWebTokenConfig.initialize();
        String token = jsonWebTokenConfig.createJWT();

        DecodedJWT result = jsonWebTokenConfig.verifyJWT(token);

        assertNotNull(result);
    }

    @Test
    void givenJsonWebToken_shouldThrowJWTVerificationException() {
        jsonWebTokenConfig.initialize();
        String token = "token";
        String errorMessage = "Error: token verifying: The token was expected to have 3 parts, but got 0.";

        JWTVerificationException result = assertThrows(JWTVerificationException.class,
                () -> jsonWebTokenConfig.verifyJWT(token));

        assertTrue(result.getMessage().contains(errorMessage));
    }

    @Test
    void givenJsonWebToken_shouldDecodedJWTAndReturnDecodedJWT() {
        jsonWebTokenConfig.initialize();
        String token = jsonWebTokenConfig.createJWT();

        DecodedJWT result = jsonWebTokenConfig.decodedJWT(token);

        assertNotNull(result);
    }

    @Test
    void givenJsonWebToken_shouldThrowJWTDecodeException() {
        jsonWebTokenConfig.initialize();
        String token = "token";
        String errorMessage = "Error: token decoding: The token was expected to have 3 parts, but got 0.";

        JWTDecodeException result = assertThrows(JWTDecodeException.class, () -> jsonWebTokenConfig.decodedJWT(token));

        assertTrue(result.getMessage().contains(errorMessage));
    }

    @Test
    void givenDecodedJWT_shouldReturnFalse() {
        jsonWebTokenConfig.initialize();
        String token = jsonWebTokenConfig.createJWT();
        DecodedJWT decodedJWT = jsonWebTokenConfig.decodedJWT(token);

        boolean result = jsonWebTokenConfig.isJWTExpired(decodedJWT);

        assertFalse(result);
    }

    @Test
    void givenDecodedJWT_shouldReturnTrue() {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        jsonWebTokenConfig.initialize();
        String token = JWT.create()
                .withIssuer(ISSUER)
                .withExpiresAt(new Date(System.currentTimeMillis()))
                .sign(algorithm);
        DecodedJWT decodedJWT = jsonWebTokenConfig.decodedJWT(token);

        boolean result = jsonWebTokenConfig.isJWTExpired(decodedJWT);

        assertTrue(result);
    }

}
