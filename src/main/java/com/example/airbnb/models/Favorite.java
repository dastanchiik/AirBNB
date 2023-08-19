package com.example.airbnb.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
@Table(name = "favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @ManyToMany
//    @ToString.Exclude
//    private List<User>users;
//    @ManyToMany
//    @ToString.Exclude
//    private List<House>houses;
}
