package com.example.airbnb.models;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "favorite_users",
            joinColumns = {@JoinColumn(name = "favorite_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> users = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "favorite_houses",
            joinColumns = {@JoinColumn(name = "favorite_id")},
            inverseJoinColumns = {@JoinColumn(name = "house_id")}
    )
    private List<House> houses = new ArrayList<>();
    private LocalDateTime createdAt = LocalDateTime.now();

    public Favorite() {
    }
}
