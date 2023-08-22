package com.example.airbnb.repositories;

import com.example.airbnb.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
