package com.example.airbnb.models;

import com.example.airbnb.models.enums.Role;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Entity
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String email;
    private String password;
    private Role role;
    @ElementCollection
    private Set<Long> likedFeedbacks = ConcurrentHashMap.newKeySet();
    @ElementCollection
    private Set<Long> disLikedFeedbacks = ConcurrentHashMap.newKeySet();
    @OneToMany(orphanRemoval = true, mappedBy = "user", fetch = FetchType.LAZY)
    private List<House> houses = new ArrayList<>();
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<FeedBack> feedBack;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Booking> bookings = new ArrayList<>();

    public void addToLikedFeedbacks(Long feedbackId) {
        likedFeedbacks.add(feedbackId);
    }

    public void removeFromLikedFeedbacks(Long feedbackId) {
        likedFeedbacks.remove(feedbackId);
    }

    public void removeFromDisLikedFeedbacks(Long feedbackId) {
        disLikedFeedbacks.remove(feedbackId);
    }

    public void addToDisLikedFeedbacks(Long feedbackId) {
        disLikedFeedbacks.add(feedbackId);
    }

    public User(Long id, String userName, String email, String password, Set<Long> likedFeedbacks, Set<Long> disLikedFeedbacks, List<House> houses, List<FeedBack> feedBack, List<Booking> bookings) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.likedFeedbacks = likedFeedbacks;
        this.disLikedFeedbacks = disLikedFeedbacks;
        this.houses = houses;
        this.feedBack = feedBack;
        this.bookings = bookings;
    }

    public User() {
    }
}
