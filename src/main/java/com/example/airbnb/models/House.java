package com.example.airbnb.models;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
//@Builder
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
@Table(name = "houses")
//@AllArgsConstructor
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    private List<String>photos;
    private String title;
    private Long price;
    private Long maxGuests;
    private String description;
    private String region;
    private String town;
    private String address;
    private Double rating;
//    @OneToMany
//    private List<FeedBack> feedBacks;
//    @ManyToOne
//    private User user;
}
