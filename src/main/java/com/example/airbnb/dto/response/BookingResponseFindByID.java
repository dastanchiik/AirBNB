package com.example.airbnb.dto.response;

import lombok.Data;
import org.apache.catalina.User;


@Data
public class BookingResponseFindByID {
    private String id;
    private String name;
    private  String  addresses;
    private String description;
    private Long MaxGuest;
    private int type;
    private  String userId;
    private String HomeType;

}
