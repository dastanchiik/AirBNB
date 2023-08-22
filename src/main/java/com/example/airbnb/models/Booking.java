package com.example.airbnb.models;

import com.example.airbnb.models.enums.Status;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@AllArgsConstructor
@Setter
@ToString
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer days;
    private Long totalMoney;
    private Long cardNumber;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDate checkin;
    private LocalDate checkout;
    private LocalDateTime createdAt = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private House house;


    public Booking() {
    }
}


