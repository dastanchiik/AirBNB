package com.example.airbnb.models;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@ToString
@Table(name = "feedbacks")
public class FeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer star;
    private String feedback;
    private volatile int likeCount;
    private volatile int dislikeCount;
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;
    private LocalDateTime createdAt = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.LAZY)
    private House house;

    public int incrementLikes() {
        return likeCount++;
    }

    public int decrementLikes() {
        return likeCount--;
    }

    public int incrementDisLikes() {
        return dislikeCount++;
    }

    public int decrementDisLikes() {
        return dislikeCount--;
    }


    public FeedBack() {
    }
}
