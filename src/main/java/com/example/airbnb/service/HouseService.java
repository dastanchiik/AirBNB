package com.example.airbnb.service;

import com.example.airbnb.dto.response.HouseResponse;
import com.example.airbnb.dto.response.HomeResponseForGetAll;
import com.example.airbnb.dto.response.HouseResponseFindByID;
import com.example.airbnb.models.House;
import com.example.airbnb.models.enums.Region;
import com.example.airbnb.repositories.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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


    public HouseResponseFindByID findById(Long id) {
        House house = houseRepository.findById(id).orElseThrow(() -> new RuntimeException(String.valueOf(id)));
        HouseResponseFindByID houseResponseFindByID = new HouseResponseFindByID();
        houseResponseFindByID.setName(house.getTitle());
        houseResponseFindByID.setAddresses(house.getAddress());
        houseResponseFindByID.setDescription(house.getDescription());
        houseResponseFindByID.setMaxGuest(house.getMaxGuests());
        houseResponseFindByID.setId(house.getId());
        houseResponseFindByID.setUserId(house.getUser().getId());
        houseResponseFindByID.setHomeType(String.valueOf(house.getHomeType()));
        return houseResponseFindByID;
    }

    public House save(House house) {
        try {
            return houseRepository.save(house);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }


//    public List<HouseResponseForGetAll> findAll() {
//        List<HouseResponseForGetAll> list = new ArrayList<>();
//        for (House house : houseRepository.findAll()) {
//            HouseResponseForGetAll houseResponseForGetAll = new HouseResponseForGetAll();
//            houseResponseForGetAll.setTitle(house.getTitle());
//            houseResponseForGetAll.setRegion(house.getRegion());
//            houseResponseForGetAll.setPrice(String.valueOf(house.getPrice()));
//            houseResponseForGetAll.setMaxGuest(house.getMaxGuests());
//            houseResponseForGetAll.setRating(house.getRating());
//            houseResponseForGetAll.setImages(house.getPhotos());
//
//        }
//
//        return list;
//    }
public List<HouseResponse> searchByTownOrTitleIgnoreCase(String searchTerm) {
    List<HouseResponse> list1 = new ArrayList<>();
    String cleanSearchTerm = cleanInput(searchTerm);
    for (House house : houseRepository.findAll()) {
        if (house.getRegion().name().equalsIgnoreCase(cleanSearchTerm) ||
                house.getTown().toLowerCase().contains(cleanSearchTerm) ||
                house.getTitle().toLowerCase().contains(cleanSearchTerm)) {
            HouseResponse houseResponse = new HouseResponse();
            houseResponse.setId(house.getId());
            houseResponse.setRegion(house.getRegion().name());
            houseResponse.setTown(house.getTown());
            houseResponse.setTitle(house.getTitle());
            list1.add(houseResponse);
        }
    }
    return list1;
}

    }
    private String cleanInput(String input) {
        return input.trim().replace("_,-", "").replaceAll("\\s+", "");

    public List<HomeResponseForGetAll> searchHousesByRegion(Region region) {

        List<HomeResponseForGetAll> responses = new ArrayList<>();
        for (House house : houseRepository.findAll()) {
            if (house.isBlocked()) {
                responses.add(null);
            }
            if (house.getRegion() == region) {
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
        }

//        responses.sort(Comparator.comparing(h -> h.getRegion().name()));

        return responses;
    }

}