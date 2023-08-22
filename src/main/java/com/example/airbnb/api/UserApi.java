package com.example.airbnb.api;

import com.example.airbnb.dto.response.NotificationResponse;
import com.example.airbnb.models.Notification;
import com.example.airbnb.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@PermitAll
public class UserApi {
    private final NotificationRepository notificationRepository;

    @GetMapping("/getAll/notifications")
    public List<NotificationResponse> getAllNotification(){
        List<NotificationResponse>notifications = new ArrayList<>();
        for (Notification notification:notificationRepository.findAll()) {
            NotificationResponse response = new NotificationResponse();
            response.setId(String.valueOf(notification.getId()));
            response.setNameOfHouse(notification.getNameHouse());
            response.setDescription(notification.getDescription());
            notifications.add(response);
        }
        return notifications;
    }
}
