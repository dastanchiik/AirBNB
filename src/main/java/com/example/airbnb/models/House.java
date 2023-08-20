package com.example.airbnb.models;

import com.example.airbnb.models.enums.HomeType;
import com.example.airbnb.models.enums.Status;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Table(name = "houses")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    private List<String> photos;
    private String title;
    private Long price;
    private Long maxGuests;
    private String description;
    private String region;
    private String town;
    private String address;
    private Double rating;
    private HomeType homeType;
    private Status status;
    @OneToMany(orphanRemoval = true, mappedBy = "house", fetch = FetchType.LAZY)
    private List<FeedBack> feedBacks = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @OneToMany(orphanRemoval = true, mappedBy = "house", fetch = FetchType.LAZY)
    private List<Booking> bookings = new ArrayList<>();

    public House(Long id, List<String> photos, String title, Long price, Long maxGuests, String description, String region, String town, String address, Double rating, List<FeedBack> feedBacks, User user, List<Booking> bookings) {
        this.id = id;
        this.photos = photos;
        this.title = title;
        this.price = price;
        this.maxGuests = maxGuests;
        this.description = description;
        this.region = region;
        this.town = town;
        this.address = address;
        this.rating = rating;
        this.feedBacks = feedBacks;
        this.user = user;
        this.bookings = bookings;
    }

    public House() {
    }
}
