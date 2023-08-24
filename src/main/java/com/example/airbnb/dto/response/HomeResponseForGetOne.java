package com.example.airbnb.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class HomeResponseForGetOne {
    private String id;
    private String title;
    private List<String>photos;
    private String rating;
    private String homeType;
    private String maxGuests;
    private String address;
    private String description;
    private String userId;
}
