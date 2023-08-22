package com.example.airbnb.api;

import com.example.airbnb.dto.response.*;
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

    @GetMapping("/getUser/ById")
    public OneUserResponseForAdmin getUserById(@RequestParam Long id){
        return adminService.getStudentByIdForAdmin(id);
    }

    @GetMapping("/getUser/bookings")
    public List<BookingResponse> getAllBooking(@RequestParam Long id){
        return adminService.getAllUserBookings(id);
    }

    @GetMapping("/getHouses/by/userId")
    public List<HomeResponseForGetAll> getHousesByUserId(@RequestParam Long id) {
        return adminService.getHousesByUserId(id);
    }

    @GetMapping("/get/house/by/id")
    public List<Object> getHouseById(@RequestParam Long id, @RequestParam(required = false)boolean confirmation) {
        if (confirmation){
        adminService.updateBlockedHouse(id);
        }
        return adminService.getHouseById(id);
    }

    }