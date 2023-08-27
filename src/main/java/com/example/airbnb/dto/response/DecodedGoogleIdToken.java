package com.example.airbnb.dto.response;

import lombok.Data;

@Data
public class DecodedGoogleIdToken {

    private String email;
    private String userId;
    private boolean isValid;
}