package com.example.airbnb.service;

import com.example.airbnb.dto.response.HomeResponseForGetAll;
import com.example.airbnb.dto.response.HomeResponseForGetOne;
import com.example.airbnb.models.House;
import com.example.airbnb.models.User;
import com.example.airbnb.models.enums.Status;
import com.example.airbnb.models.enums.StatusRequest;
import com.example.airbnb.repositories.HouseRepository;
import com.example.airbnb.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {
    private final HouseRepository houseRepository;
    private final UserRepository repository;

    public List<HomeResponseForGetAll> getAllApplications(){
        List<HomeResponseForGetAll> responses = new ArrayList<>();
        for (House house: houseRepository.getAllApplications()) {
            HomeResponseForGetAll response = new HomeResponseForGetAll();
            response.setId(String.valueOf(house.getId()));
//            if (house.getPhotos().get(0) != null){
//            response.setPhoto(house.getPhotos().get(0));
//            }else {
//                response.setPhoto(null);
//            }
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

    public void selectStatusById(Long id, StatusRequest status) {
        House house = houseRepository.getById(id);

        if (status==StatusRequest.ACCEPTED) {
            house.setStatus(Status.ACCEPTED);
            log.info("successfully worked");
            houseRepository.save(house);
        } else if (status == StatusRequest.REJECTED){
            house.setStatus(Status.REJECTED);
        log.info("successfully worked");
        houseRepository.save(house);
        } else if (status == StatusRequest.DELETE) {
            houseRepository.delete(house);
        }
    }
//    @Scheduled(fixedRate = 300000)
    @Transactional
    public void findAndRemoveSimilarUsers() {
        String email = "";
        for (int i = repository.findAll().size(); i >=0; i--) {
            Optional<User> userOptional = repository.findById((long) i);
            email = userOptional.map(User::getEmail).orElse("DefaultEmail"); // Или какой-то другой дефолтный адрес
            List<User> userList = repository.findLastUsersWithSimilarEmail(email);
            int size = userList.size();
            for (int j = 0; j < size; j++) {
                User user = userList.get(j);
                if (j == size - 1) {
                    continue;
                }
                repository.delete(user);
            }
        }
    }
}
