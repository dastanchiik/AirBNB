package com.example.airbnb.dto.response;

import com.example.airbnb.models.enums.Status;
import lombok.Data;

import java.time.LocalDate;
@Data
public class BookingResponse {
    private String id;
    private String photo;
    private Long totalMoney;
    private String rating;
   private String name;
   private String address;
   private String maxGuests;
}
