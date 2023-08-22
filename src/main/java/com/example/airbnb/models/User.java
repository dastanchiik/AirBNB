package com.example.airbnb.models;

import com.example.airbnb.models.enums.Role;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String photo;
    private String userName;
    private String name;
    private String email;
    private LocalDateTime createdAt = LocalDateTime.now();
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ElementCollection(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Long> likedFeedbacks = ConcurrentHashMap.newKeySet();
    @ElementCollection(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
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

    public User() {
    }
}
