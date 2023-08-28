package com.example.airbnb.api;

import com.example.airbnb.dto.response.HomeResponseForGetAll;
import com.example.airbnb.dto.response.NotificationResponse;
import com.example.airbnb.models.Notification;
import com.example.airbnb.repositories.NotificationRepository;
import com.example.airbnb.repositories.UserRepository;
import com.example.airbnb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@PermitAll
public class UserApi {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/getAll/notifications")
    public List<NotificationResponse> getAllNotification() {
        List<NotificationResponse> notifications = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        for (Notification notification : notificationRepository.findAll()) {
            if (notification.getUser().getId()==userRepository.findByEmail(authentication.getName()).get().getId()) {
                NotificationResponse response = new NotificationResponse();
                response.setId(String.valueOf(notification.getId()));
                response.setNameOfHouse(notification.getNameHouse());
                response.setDescription(notification.getDescription());
                notifications.add(response);
            }
        }
        return notifications;
    }

    @GetMapping("/getAll/on/moderation")
    public List<HomeResponseForGetAll> getListOnModeration(){
        return userService.getAllOnModeration();
    }
}