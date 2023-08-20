package com.example.airbnb.service;

import com.example.airbnb.dto.response.HomeResponseForGetAll;
import com.example.airbnb.dto.response.HomeResponseForGetOne;
import com.example.airbnb.dto.response.SimpleResponse;
import com.example.airbnb.models.House;
import com.example.airbnb.models.enums.Status;
import com.example.airbnb.repositories.HouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {
    private final HouseRepository houseRepository;

    public List<HomeResponseForGetAll> getAllApplications(){
        List<HomeResponseForGetAll> responses = new ArrayList<>();
        for (House house: houseRepository.getAllApplications()) {
            HomeResponseForGetAll response = new HomeResponseForGetAll();
            response.setId(String.valueOf(house.getId()));
            response.setRate(String.valueOf(house.getRating()));
            response.setTitle(house.getTitle());
            response.setPrice(String.valueOf(house.getPrice()));
            response.setAddress(house.getAddress());
            response.setMaxGuests(String.valueOf(house.getMaxGuests()));
            responses.add(response);
        }
        return responses;
    }

    public HomeResponseForGetOne getApplicationById(Long id){
        House house = houseRepository.getApplicationById(id);
        HomeResponseForGetOne response = new HomeResponseForGetOne();
        if (house == null) {
            return response;
        }else {
        response.setId(String.valueOf(house.getId()));
        response.setDescription(house.getDescription());
        response.setMaxGuests(String.valueOf(house.getMaxGuests()));
        response.setHomeType(String.valueOf(house.getHomeType()));
        response.setPhotos(house.getPhotos());
        response.setTitle(house.getTitle());
        response.setAddress(house.getAddress());
        if (house.getUser() != null){
        response.setUserId(String.valueOf(house.getUser().getId()));
        } else if (house.getUser() == null) {
            response.setUserId(null);
        }
        return response;
        }
    }

    public SimpleResponse selectStatusById(Long id,boolean status){
        House house = houseRepository.getById(id);
        if (status == true){
            house.setStatus(Status.ACCEPTED);
            log.info("successfully worked");
            houseRepository.save(house);
            return new SimpleResponse("Accepted :)");
        }else {
            house.setStatus(Status.REJECTED);
            log.info("successfully worked");
            houseRepository.save(house);
        return new SimpleResponse("Rejected :)");
        }
    }
}
