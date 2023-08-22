package com.example.airbnb.Service;

import com.example.airbnb.dto.response.FeedbackResponse;
import com.example.airbnb.dto.response.HouseResponseForGetAll;
import com.example.airbnb.models.FeedBack;
import com.example.airbnb.models.House;
import com.example.airbnb.repositories.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public FeedBack saveFeedback(FeedBack feedBack) {
        try {
            return feedbackRepository.save(feedBack);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public void deleteById(Long id) {
        try {
            feedbackRepository.deleteById(id);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public FeedBack findById(Long id) {
        try {
            return feedbackRepository.findById(id).orElseThrow(() -> new RuntimeException(String.valueOf(+id)));
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public FeedBack update(Long id, FeedBack feedBack) {
        try {
            FeedBack feedBack1 = feedbackRepository.findById(id).orElseThrow();
            feedBack1.setFeedback(feedBack.getFeedback());
            feedBack1.setFeedback(feedBack.getFeedback());
            feedbackRepository.save(feedBack1);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

        return feedBack;
    }

    public List<FeedbackResponse> findAll() {
        List<FeedbackResponse> list = new ArrayList<>();
        for (FeedBack feedBack : feedbackRepository.findAll()) {
            FeedbackResponse feedbackResponse = new FeedbackResponse();
            feedbackResponse.setStar(feedBack.getStar());
            feedbackResponse.setLocalDateTime(feedBack.getFeedback());
            feedbackResponse.setDislikeCount(feedBack.getDislikeCount());
            feedbackResponse.setLikeCount(feedBack.getLikeCount());

        }
        return list;
    }
}
