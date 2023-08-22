package com.example.airbnb.dto.response;

import com.example.airbnb.models.enums.Status;
import lombok.Data;

import java.time.LocalDate;
@Data
public class BookingResponse {
    private Integer Days;
    private Long totalMoney;
   private Long carNumber;
   private Status status;
   private LocalDate checkin;
   private LocalDate checkout;
}
