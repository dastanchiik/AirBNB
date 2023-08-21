package com.example.airbnb.dto.authRequest;

import lombok.Data;

@Data
public class LoginRequest {

    private String EmailOrUserName;
    private String email;
    private String password;
}
