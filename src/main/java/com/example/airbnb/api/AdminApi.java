package com.example.airbnb.api;

import com.example.airbnb.dto.response.AllUserResponseForAdmin;
import com.example.airbnb.dto.response.HomeResponseForGetAll;
import com.example.airbnb.dto.response.HomeResponseForGetOne;
import com.example.airbnb.dto.response.SimpleResponse;
import com.example.airbnb.models.Notification;
import com.example.airbnb.models.enums.StatusRequest;
import com.example.airbnb.repositories.HouseRepository;
import com.example.airbnb.repositories.NotificationRepository;
import com.example.airbnb.repositories.UserRepository;
import com.example.airbnb.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
public class AdminApi {
    private final AdminService adminService;
    private final HouseRepository houseRepository;
    private final UserRepository repository;
    private final NotificationRepository notificationRepository;


    @GetMapping("/applications")
    public List<HomeResponseForGetAll> getAllApplications() {
        return adminService.getAllApplications();
    }

    @GetMapping("/get/application/by/id")
    public HomeResponseForGetOne getAllApplicationById(@RequestParam Long id) {
        return adminService.getApplicationById(id);
    }

    @PostMapping("/select/status")
    @Transactional
    public SimpleResponse selectStatus(@RequestParam Long id, @RequestParam StatusRequest status,
                                       @RequestParam(required = false) String description,
                                       @RequestParam(required = false) Long userId) {
        adminService.selectStatusById(id, status);
        Notification notification = new Notification();
        if (userId != null) {
            notification.setDescription(description);
            notification.setNameHouse(houseRepository.getById(id).getTitle());
            notification.setUser(repository.getById(userId));
            notificationRepository.save(notification);
        }
        return new SimpleResponse("Successfully");
    }

    @GetMapping("/getAllUser")
    public List<AllUserResponseForAdmin> getAllUsersForAdmin(@RequestParam(required = false)Long userId) {
        if (userId != null) {
            repository.deleteById(userId);
        }
        return adminService.getAllUsersForAdmin();
    }
}