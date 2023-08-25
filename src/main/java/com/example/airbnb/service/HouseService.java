package com.example.airbnb.service;

import com.example.airbnb.dto.response.HouseResponse;
import com.example.airbnb.models.House;
import com.example.airbnb.repositories.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository searchHousesRepository;


public List<HouseResponse> searchByTownOrTitleIgnoreCase(String searchTerm) {
    List<HouseResponse> list1 = new ArrayList<>();
    String cleanSearchTerm = cleanInput(searchTerm);
    for (House house : searchHousesRepository.findAll()) {
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

    private String cleanInput(String input) {
        return input.trim().replace("_,-", "").replaceAll("\\s+", "");
    }









}


