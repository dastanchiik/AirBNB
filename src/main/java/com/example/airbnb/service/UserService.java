package com.example.airbnb.service;

import com.example.airbnb.dto.UserRequest;
import com.example.airbnb.dto.response.HomeResponseForGetAll;
import com.example.airbnb.dto.response.HouseResponse;
import com.example.airbnb.dto.response.HouseResponseForMain;
import com.example.airbnb.dto.response.UserResponse;
import com.example.airbnb.models.House;
import com.example.airbnb.models.enums.*;
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

    public List<HomeResponseForGetAll> getSortedMainPageHouses(Region region, Kind kind, HomeType homeType, PriceType priceType) {
        List<House> houses = new ArrayList<>();
        List<HomeResponseForGetAll> responses = new ArrayList<>();


        if (kind == Kind.POPULAR) {
            houses.addAll(houseRepository.sortedMainHousesPopular(homeType, region));
        } else if (kind == Kind.THE_LATEST && priceType == PriceType.LOW_TO_HIGH ) {
            houses.addAll(houseRepository.sortedMainHousesLatestAndLow(homeType, region));
        } else if (kind == Kind.POPULAR && priceType == PriceType.HIGH_TO_LOW) {
            houses.addAll(houseRepository.sortedMainHousesPopularAndHigh(homeType, region));
        }
        else if (kind == Kind.POPULAR && priceType == PriceType.LOW_TO_HIGH) {
            houses.addAll(houseRepository.sortedMainHousesPopularAndLow(homeType, region));
        } else if (kind == Kind.THE_LATEST && priceType == PriceType.HIGH_TO_LOW) {
            houses.addAll(houseRepository.sortedMainHousesLatestAndHigh(homeType,region));
        } else if (kind == Kind.THE_LATEST && priceType == PriceType.ALL) {
            houses.addAll(houseRepository.sortedMainHousesLatest(homeType, region));
        } else if (priceType == PriceType.HIGH_TO_LOW && kind == Kind.ALL) {
            houses.addAll(houseRepository.sortedMainHousesHigh(homeType, region));
        } else if (priceType == PriceType.LOW_TO_HIGH && kind == Kind.ALL) {
            houses.addAll(houseRepository.sortedMainHousesLow(homeType, region));
        } else if (kind == Kind.ALL && priceType == PriceType.ALL && region == Region.ALL && homeType == HomeType.ALL) {
            houses.addAll(houseRepository.findAll());
        } else if (kind == Kind.ALL && priceType == PriceType.ALL) {
            for (House house:houseRepository.findAll()) {
                if (house.getRegion() == region || house.getHomeType() == homeType){
                    houses.add(house);
                }
            }
        }

        for (House house : houses) {
            HomeResponseForGetAll response = new HomeResponseForGetAll();
            response.setId(String.valueOf(house.getId()));
            response.setRate(String.valueOf(house.getRating()));
            response.setPrice(String.valueOf(house.getPrice()));
            if (house.getPhotos() != null && !house.getPhotos().isEmpty()) {
                response.setPhoto(house.getPhotos().get(0));
            } else {
                response.setPhoto(null);
            }
            response.setAddress(house.getAddress());
            response.setTitle(house.getTitle());
            response.setMaxGuests(String.valueOf(house.getMaxGuests()));
            responses.add(response);
        }
        return responses;
    }
}
