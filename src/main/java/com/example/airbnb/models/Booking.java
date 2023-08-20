package com.example.airbnb.models;

import com.example.airbnb.models.enums.Status;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

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
    private Status status;
    private LocalDate checkin;
    private LocalDate checkout;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private House house;

//    public Booking(Long id, Integer days, Long totalMoney, Long cardNumber, LocalDate checkin, LocalDate checkout, User user, House house) {
//        this.id = id;
//        this.days = days;
//        this.totalMoney = totalMoney;
//        this.cardNumber = cardNumber;
//        this.checkin = checkin;
//        this.checkout = checkout;
//        this.user = user;
//        this.house = house;
//    }

    public Booking() {
    }
}


