package com.example.airbnb.models;

import lombok.*;
import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@ToString
@Table(name = "feedbacks")
public class FeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feedback_id_generator")
    @SequenceGenerator(name = "feedback_id_generator", sequenceName = "feedback_seq", allocationSize = 1, initialValue = 137)
    private Long id;
    private Integer star;
    private String feedback;
    private volatile int likeCount;
    private volatile int dislikeCount;
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;
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

    public FeedBack(Long id, Integer star, String feedback, int likeCount, int dislikeCount, User owner, House house) {
        this.id = id;
        this.star = star;
        this.feedback = feedback;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.owner = owner;
        this.house = house;
    }

    public FeedBack() {
    }
}
