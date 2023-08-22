package com.example.airbnb.dto.response;

import lombok.Data;

@Data
public class OneUserResponseForAdmin {
    private String id;
    private String photo;
    private String name;
    private String email;
}
