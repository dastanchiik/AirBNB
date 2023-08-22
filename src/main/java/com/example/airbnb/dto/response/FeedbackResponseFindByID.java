package com.example.airbnb.dto.response;

import lombok.Data;

@Data
public class FeedbackResponseFindByID {
    private String id;
    private String name;
    private String description;
    private  Long userId;
   private int rating;
   private int dislikeCount;
   private int likeCount;
}
