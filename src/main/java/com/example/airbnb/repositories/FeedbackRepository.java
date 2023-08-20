package com.example.airbnb.repositories;

import com.example.airbnb.models.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedBack,Long> {
}
