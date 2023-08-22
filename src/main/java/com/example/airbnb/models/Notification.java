package com.example.airbnb.models;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameHouse;
    private String description;
    @OneToOne
    private User user;

    public Notification() {
    }
}
