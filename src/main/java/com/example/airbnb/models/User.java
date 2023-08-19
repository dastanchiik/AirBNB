package com.example.airbnb.models;

import lombok.*;
import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import static javax.persistence.CascadeType.*;

@Entity
//@Builder
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
@Table(name = "users")
//@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String email;
    private String password;
//    @ElementCollection
//    @Cascade(org.hibernate.annotations.CascadeType.ALL)
//    private Set<Long> likedFeedbacks = ConcurrentHashMap.newKeySet();
//
//    @ElementCollection
//    @Cascade({org.hibernate.annotations.CascadeType.ALL})
//    private Set<Long> disLikedFeedbacks = ConcurrentHashMap.newKeySet();
//    @OneToMany
//    @ToString.Exclude
//    private List<House>houses;
//    @OneToOne(fetch = FetchType.LAZY,cascade = {DETACH, REFRESH, MERGE, PERSIST},mappedBy = "user")
//    @JoinColumn(name = "feedbacks_id")
//    private FeedBack feedBack;
}
