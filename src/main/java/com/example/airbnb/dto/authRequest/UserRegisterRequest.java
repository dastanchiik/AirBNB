package com.example.airbnb.dto.authRequest;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String email;
    private String password;
}
