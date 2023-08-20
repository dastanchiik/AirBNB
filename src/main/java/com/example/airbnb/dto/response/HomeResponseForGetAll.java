package com.example.airbnb.dto.response;

import lombok.Data;

@Data
public class HomeResponseForGetAll {
    private String id;
    private String price;
    private String rate;
    private String title;
    private String address;
    private String maxGuests;
}
