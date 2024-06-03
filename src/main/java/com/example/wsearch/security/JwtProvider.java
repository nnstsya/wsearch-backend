package com.example.wsearch.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.wsearch.entity.DbRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

@Component
public class JwtProvider {

    @Value("{@jwt.secret}")
    private String jwtSecret;

    @Value("{@jwt.issuer}")
    private String jwtIssuer;

    public String generateToken(Long id, String username, DbRole role) {
        return JWT.create()
                .withIssuer(jwtIssuer)
                .withClaim("roles", role.getName())
                .withClaim("id", id)
                .withSubject(username)
                .withExpiresAt(LocalDate.now()
                        .plusDays(15)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant())
                .sign(Algorithm.HMAC256(jwtSecret));
    }

    public Optional<DecodedJWT> toDecodedJWT(String token) {
        try {
            return Optional.of(JWT.require(Algorithm.HMAC256(jwtSecret))
                    .withIssuer(jwtIssuer)
                    .build()
                    .verify(token));
        } catch (JWTVerificationException exception) {
            return Optional.empty();
        }
    }

    public String getUsernameFromToken(String token) {
        return JWT.require(Algorithm.HMAC256(jwtSecret))
                .withIssuer(jwtIssuer)
                .build()
                .verify(token)
                .getSubject();
    }

}
