package com.example.airbnb.dto.response;

import lombok.Data;

@Data
public class HouseResponseFindByID {
    private long id;
    private String name;
    private  String  addresses;
    private String description;
    private Long MaxGuest;
    private int type;
    private  Long userId;
    private String HomeType;


}
