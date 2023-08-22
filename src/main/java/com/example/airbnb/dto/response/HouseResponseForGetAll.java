package com.example.airbnb.dto.response;

import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


@Data
public class HouseResponseForGetAll {
    private String title;
    private String region;
    private String price;
    private Long  MaxGuest;
    private double rating;
    private List<String> images = new ArrayList<>();

}
