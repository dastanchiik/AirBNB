package com.example.airbnb.api;

import com.example.airbnb.dto.response.*;
import com.example.airbnb.models.Notification;
import com.example.airbnb.models.enums.*;
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
@PreAuthorize("hasAnyAuthority('ADMIN_USER','ADMIN')")
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
                                       @RequestParam(required = false) String description
//                                       @RequestParam(required = false) Long userId
    ) {
        adminService.selectStatusById(id, status);
        Notification notification = new Notification();
        if (status == StatusRequest.REJECTED) {
            notification.setDescription(description);
            notification.setNameHouse(houseRepository.getById(id).getTitle());
            notification.setUser(repository.getById(houseRepository.getById(id).getUser().getId()));
            notificationRepository.save(notification);
        }
        return new SimpleResponse("Successfully");
    }

    @GetMapping("/getAllUser")
    public List<AllUserResponseForAdmin> getAllUsersForAdmin(@RequestParam(required = false)Long deleteByUserId) {
        if (deleteByUserId != null) {
            repository.deleteById(deleteByUserId);
        }
        return adminService.getAllUsersForAdmin();
    }

    @GetMapping("/bookings/by/userId")
    public List<Object> getAllBooking(@RequestParam Long id){
        return adminService.getAllUserBookings(id);
    }

    @GetMapping("/getHouses/by/userId")
    public List<Object> getHousesByUserId(@RequestParam Long id,@RequestParam(defaultValue = "false",required = false)Boolean blockAll) {
        return adminService.getHousesByUserId(id,blockAll);
    }

    @GetMapping("/get/house/by/id")
    public List<Object> getHouseById(@RequestParam Long id,
                                     @RequestParam(required = false)boolean confirmation,
                                     @RequestParam(required = false)boolean delete) {
        if (confirmation){
        adminService.updateBlockedHouse(id);
        }
        if (delete){
            houseRepository.deleteById(id);
        }
        return adminService.getHouseById(id);
    }

    @PutMapping("/updateRole")
    public Role updateRole(){
        return adminService.updateRole();
    }


//    @GetMapping("/getSorted")
//    public List<HomeResponseForGetAll> sortedHouses(@RequestParam(defaultValue = "ALL") BookedType bookedType,
//                                                    @RequestParam(defaultValue = "ALL") Kind kind,
//                                                    @RequestParam(defaultValue = "ALL") HomeType homeType,
//                                                    @RequestParam(defaultValue = "ALL") PriceType priceType){
//        return adminService.getSortedHouses(bookedType,kind,homeType,priceType);
//    }
    }