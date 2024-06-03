package com.example.wsearch.dto;

import lombok.Data;

@Data
public class Credentials {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setUEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Credentials() {    }

    public Credentials(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
