package com.example.wsearch.dto;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;

@Data
public class JwtToken {
    private String token;
    private String type;
    private String algorithm;
    private Long expiresAt;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public Long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public JwtToken() {}

    public JwtToken(String token, String type, String algorithm, Long expiresAt) {
        this.token = token;
        this.type = type;
        this.algorithm = algorithm;
        this.expiresAt = expiresAt;
    }

    public JwtToken(DecodedJWT decodedJWT) {
        this.token = decodedJWT.getToken();
        this.type = decodedJWT.getType();
        this.algorithm = decodedJWT.getAlgorithm();
        this.expiresAt = decodedJWT.getExpiresAt().getTime();
    }
}
