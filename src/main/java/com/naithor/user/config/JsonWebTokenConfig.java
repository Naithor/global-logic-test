package com.naithor.user.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class JsonWebTokenConfig {

    protected static final String SECRET = "globallogic";
    protected static final String ISSUER = "globallogic";
    private static final String SUBJECT = "globallogic details";
    private static final long TOKEN_VALIDITY_IN_MILLIS = 900000;
    private static final String ERROR_TOKEN_VERIFYING = "Error: token verifying: %s";
    private static final String ERROR_TOKEN_DECODING = "Error: token decoding: %s";

    private static Algorithm algorithm;
    private static JWTVerifier verifier;

    public void initialize() {
        algorithm = Algorithm.HMAC256(SECRET);

        verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();
    }

    public String createJWT() {
        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(SUBJECT)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_VALIDITY_IN_MILLIS))
                .sign(algorithm);
    }

    protected DecodedJWT verifyJWT(String jwtToken) {
        try {
            return verifier.verify(jwtToken);
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException(String.format(ERROR_TOKEN_VERIFYING, e.getMessage()));
        }
    }

    protected DecodedJWT decodedJWT(String jwtToken) {
        try {
            return JWT.decode(jwtToken);
        } catch (JWTDecodeException e) {
            throw new JWTDecodeException(String.format(ERROR_TOKEN_DECODING, e.getMessage()));
        }
    }

    protected boolean isJWTExpired(DecodedJWT decodedJWT) {
        return decodedJWT.getExpiresAt()
                .before(new Date());
    }

}
