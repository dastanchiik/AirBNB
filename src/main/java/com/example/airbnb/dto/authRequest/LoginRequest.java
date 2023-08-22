package com.example.airbnb.dto.authRequest;

import lombok.Data;

@Data
public class LoginRequest {
    private String emailOrUserName;
    private String password;
}
