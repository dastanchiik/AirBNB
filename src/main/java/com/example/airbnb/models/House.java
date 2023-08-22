package com.example.airbnb.models;

import com.example.airbnb.models.enums.HomeType;
import com.example.airbnb.models.enums.Status;
import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> photos;
    private String title;
    private BigDecimal price;
    private Long maxGuests;
    private String description;
    private String region;
    private String town;
    private String address;
    private boolean blocked;
    private Double rating;
    @Enumerated(EnumType.STRING)
    private HomeType homeType;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(orphanRemoval = true, mappedBy = "house", fetch = FetchType.LAZY)
    private List<FeedBack> feedBacks = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private LocalDateTime createdAt = LocalDateTime.now();
    @OneToMany(orphanRemoval = true, mappedBy = "house", fetch = FetchType.LAZY)
    private List<Booking> bookings = new ArrayList<>();

    public House() {
    }
}
