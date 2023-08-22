package com.example.airbnb.Service;

import com.example.airbnb.models.House;
import com.example.airbnb.models.enums.Region;
import com.example.airbnb.repositories.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository houseRepository;
    public List<House> searchHouses(String title, String town, Region region, String homeType) {
        return houseRepository.search(title, town, region, homeType);
    }

}
