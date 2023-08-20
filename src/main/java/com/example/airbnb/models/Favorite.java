package com.example.airbnb.models;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
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

    public Favorite(Long id, List<User> users, List<House> houses) {
        this.id = id;
        this.users = users;
        this.houses = houses;
    }

    public Favorite() {
    }
}
