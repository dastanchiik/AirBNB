package com.example.airbnb.repositories;

import com.example.airbnb.models.Notification;
import com.example.airbnb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
