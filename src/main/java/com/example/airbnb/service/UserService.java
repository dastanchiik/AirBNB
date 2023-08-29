package com.example.airbnb.service;

import com.example.airbnb.dto.UserRequest;
import com.example.airbnb.dto.response.HomeResponseForGetAll;
import com.example.airbnb.dto.response.HouseResponse;
import com.example.airbnb.dto.response.HouseResponseForMain;
import com.example.airbnb.dto.response.UserResponse;
import com.example.airbnb.models.House;
import com.example.airbnb.repositories.HouseRepository;
import com.example.airbnb.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.airbnb.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final HouseRepository houseRepository;
    private final UserRepository userRepository;


    public void deleteById(Long id) {
        userRepository.deleteById(id);

    }

    public void update(Long id, User user) {
        try {
            User user1 = userRepository.findById(id).orElseThrow();
            user1.setEmail(user.getEmail());
            user1.setPassword(user.getPassword());
            user1.setUserName(user.getUserName());
            userRepository.save(user);
        } catch (Exception exception) {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    public User findById(Long id) {
        userRepository.findById(id);
        return null;
    }

    public User save(UserRequest user) {
        User user1 = new ModelMapper().map(user, User.class);
        return userRepository.save(user1);
    }


    public List<UserResponse> findAll() {
        List<UserResponse> list = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setEmail(user.getEmail());
            userResponse.setUserName(user.getUserName());
        }

        return list;
    }

    public List<HomeResponseForGetAll> getAllOnModeration() {
        List<HomeResponseForGetAll> responses = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName()).get();
        for (House house : houseRepository.getAllOnModerationByUserId(user.getId())) {
            HomeResponseForGetAll response = new HomeResponseForGetAll();
            response.setId(String.valueOf(house.getId()));
            if (!house.getPhotos().isEmpty() && house.getPhotos().get(0) != null) {
                response.setPhoto(house.getPhotos().get(0));
            } else {
                response.setPhoto(null);
            }
            response.setRate(String.valueOf(house.getRating()));
            response.setTitle(house.getTitle());
            response.setPrice(String.valueOf(house.getPrice()));
            response.setAddress(house.getAddress());
            response.setMaxGuests(String.valueOf(house.getMaxGuests()));
            responses.add(response);
        }
        return responses;
    }

    public List<HouseResponseForMain> getPopularApartments() {
        List<House> houses = houseRepository.getPopularApartments();
        return houses.stream()
                .limit(7) // Ограничиваем поток до 7 элементов
                .map(house -> {
                    HouseResponseForMain response = new HouseResponseForMain();
                    response.setId(String.valueOf(house.getId()));
                    response.setDescription(house.getDescription());
                    response.setAddress(house.getAddress());
                    response.setTitle(house.getTitle());
                    return response;
                })
                .collect(Collectors.toList());
    }

    /**public List<HouseResponseForMain> getPopularApartments(){
        List<HouseResponseForMain> responses = new ArrayList<>();
        List<House>houses = houseRepository.getPopularApartments();
        for (int i = 0; i <7 ; i++) {
            House house;
            if (houses.get(i) != null){
                house = houses.get(i);
            }else if(houses.get(i) == null){
                house = houses.get(0);
            }else {
                house = null;
            }
            HouseResponseForMain response = new HouseResponseForMain();
            response.setId(String.valueOf(house.getId()));
            response.setDescription(house.getDescription());
            response.setAddress(house.getAddress());
            response.setTitle(house.getTitle());
            responses.add(response);
        }
        return responses;
    }*/

    public List<HouseResponseForMain> getPopularHouses() {
        List<House> houses = houseRepository.getPopularHouses();
        return houses.stream()
                .limit(7) // Ограничиваем поток до 7 элементов
                .map(house -> {
                    HouseResponseForMain response = new HouseResponseForMain();
                    response.setId(String.valueOf(house.getId()));
                    response.setDescription(house.getDescription());
                    response.setAddress(house.getAddress());
                    response.setTitle(house.getTitle());
                    return response;
                })
                .collect(Collectors.toList());
    }

    /*public List<HouseResponseForMain> getPopularHouses(){
        List<HouseResponseForMain> responses = new ArrayList<>();
        List<House>houses = houseRepository.getPopularHouses();
        for (int i = 0; i <7 ; i++) {
            House house = new House();
            if (houses.get(i) != null){
            house = houses.get(i);
            }else if(houses.get(i) == null){
                house = houses.get(0);
            }else {
                house = null;
            }
            HouseResponseForMain response = new HouseResponseForMain();
            response.setId(String.valueOf(house.getId()));
            response.setDescription(house.getDescription());
            response.setAddress(house.getAddress());
            response.setTitle(house.getTitle());
            responses.add(response);
        }
        return responses;
    }*/

    public List<HouseResponseForMain> getLastedAnnouncement() {
        List<House> houses = houseRepository.getLasted();
        return houses.stream()
                .limit(7) // Ограничиваем поток до 7 элементов
                .map(house -> {
                    HouseResponseForMain response = new HouseResponseForMain();
                    response.setId(String.valueOf(house.getId()));
                    response.setDescription(house.getDescription());
                    response.setAddress(house.getAddress());
                    response.setTitle(house.getTitle());
                    return response;
                })
                .collect(Collectors.toList());
    }

    // public List<HouseResponseForMain> getLastedAnnouncement(){
   //     List<HouseResponseForMain> responses = new ArrayList<>();
   //     List<House>houses = houseRepository.getLasted();
   //     for (int i = 0; i <7 ; i++) {
   //
   //         House house = houses.get(i);
   //         HouseResponseForMain response = new HouseResponseForMain();
   //         response.setId(String.valueOf(house.getId()));
   //         response.setDescription(house.getDescription());
   //         response.setAddress(house.getAddress());
   //         response.setTitle(house.getTitle());
   //         responses.add(response);
   //     }
   //     return responses;
   // }
}
