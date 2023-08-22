package com.example.airbnb.Service;

import com.example.airbnb.dto.response.HouseResponseForGetAll;
import com.example.airbnb.dto.response.UserResponse;
import com.example.airbnb.models.House;
import com.example.airbnb.models.User;
import com.example.airbnb.repositories.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository houseRepository;


    public void deleteById(Long id) {
        houseRepository.deleteById(id);

    }

    public void update(Long id, House house) {
        try {
            House house1 = houseRepository.findById(id).orElseThrow();
            house1.setTitle(house1.getTitle());
            house1.setDescription(house.getDescription());
            house1.setPrice(house.getPrice());
            house1.setMaxGuests(house.getMaxGuests());
            house1.setRegion(house.getRegion());
            house1.setAddress(house.getAddress());
            house1.setFeedBacks(house.getFeedBacks());
            house1.setPhotos(house.getPhotos());
            house1.setHomeType(house.getHomeType());
            house1.setStatus(house.getStatus());
            house1.setRating(house.getRating());
            houseRepository.save(house1);

        } catch (Exception exception) {
            throw new RuntimeException(String.valueOf(id));
        }
    }


    public House findById(Long id) {
        return houseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.valueOf(id)));
    }


    public House save(House house) {
        try {
            return houseRepository.save(house);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }


    public List<HouseResponseForGetAll> findAll() {
        List<HouseResponseForGetAll> list = new ArrayList<>();
        for (House house : houseRepository.findAll()) {
            HouseResponseForGetAll houseResponseForGetAll = new HouseResponseForGetAll();
            houseResponseForGetAll.setTitle(house.getTitle());
            houseResponseForGetAll.setRegion(house.getRegion());
            houseResponseForGetAll.setPrice(house.getPrice());
            houseResponseForGetAll.setMaxGuest(house.getMaxGuests());
            houseResponseForGetAll.setRating(house.getRating());
            houseResponseForGetAll.setImages(house.getPhotos());

        }

        return list;
    }

    }