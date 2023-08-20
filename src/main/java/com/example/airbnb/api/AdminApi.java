package com.example.airbnb.api;

import com.example.airbnb.dto.response.HomeResponseForGetAll;
import com.example.airbnb.dto.response.HomeResponseForGetOne;
import com.example.airbnb.dto.response.SimpleResponse;
import com.example.airbnb.models.User;
import com.example.airbnb.repositories.UserRepository;
import com.example.airbnb.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AdminApi {
    private final AdminService adminService;
    private final UserRepository repository;

    @GetMapping("/admin/applications")
    public List<HomeResponseForGetAll>getAllApplications(){
        return adminService.getAllApplications();
    }

    @GetMapping("/admin/application/by/id")
    public HomeResponseForGetOne getAllApplicationById(@RequestParam Long id){
        return adminService.getApplicationById(id);
    }

    @PostMapping
    public SimpleResponse selectStatus(@RequestParam Long id,@RequestParam boolean status){
        return adminService.selectStatusById(id, status);
    }


}
