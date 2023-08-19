package com.example.airbnb.models;

import lombok.*;
import javax.persistence.*;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.EAGER;

@Entity
//@NoArgsConstructor
//@Builder
//@Getter
//@Setter
//@ToString
@Table(name = "feedbacks")
//@AllArgsConstructor
public class FeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feedback_id_generator")
    @SequenceGenerator(name = "feedback_id_generator", sequenceName = "feedback_seq", allocationSize = 1, initialValue = 137)
    private Long id;
    private Integer star;
    private String feedback;
    private  Long likeCount;
    private  Long dislikeCount;
//    @OneToOne(cascade = {REFRESH, PERSIST, DETACH, MERGE}, fetch = EAGER)
//    private User owner;
//    @ManyToOne
//    private House house;

//    public int incrementLikes() {
//        return like++;
//    }
//
//    public int decrementLikes() {
//        return like--;
//    }
//
//    public int incrementDisLikes() {
//        return dislike++;
//    }
//
//    public int decrementDisLikes() {
//        return dislike--;
//    }
}
