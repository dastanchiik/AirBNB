package com.example.airbnb.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private BigDecimal days;
    private Long totalMoney;
    private Long cardNumber;
    private LocalDate checkin;
    private LocalDate checkout;
//    @ManyToOne
//    private User user;
//    @ManyToOne
//    private House house;
}
