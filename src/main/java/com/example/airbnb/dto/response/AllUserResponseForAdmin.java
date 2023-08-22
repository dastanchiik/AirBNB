package com.example.airbnb.dto.response;

import lombok.Data;

@Data
public class AllUserResponseForAdmin {
    private String id;
    private String name;
    private String email;
    private String countBooking;
    private String countAnnouncement;
}
