package com.example.airbnb.SecurityConfig;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;


    public String generateToken(String userName) {
        Date expirationDate = Date.from(ZonedDateTime.now().plusMonths(1).toInstant());
        return JWT.create()
                .withSubject("User details")
                .withClaim("username", userName)
                .withIssuedAt(new Date()).withIssuer("I'm taksyr")
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveClaim(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User details")
                .withIssuer("dastan")
                .build();
        DecodedJWT verify = jwtVerifier.verify(token);
        return verify.getClaim("userName").asString();
    }

    public JwtUtils() {
    }
}
