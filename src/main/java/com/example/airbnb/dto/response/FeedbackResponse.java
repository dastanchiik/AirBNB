package com.example.airbnb.dto.response;

import lombok.Data;

@Data
public class FeedbackResponse {
    private Integer star;
    private String LocalDateTime;
    private int  DislikeCount;
    private int  LikeCount;

}
