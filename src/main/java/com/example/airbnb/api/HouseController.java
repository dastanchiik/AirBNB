package com.example.airbnb.api;

import com.example.airbnb.Service.HouseService;
import com.example.airbnb.models.House;
import com.example.airbnb.models.enums.Region;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;
    @GetMapping("/search")
    public ResponseEntity<List<House>> searchHouses(
            @RequestParam("title") String title,
            @RequestParam("town") String town,
            @RequestParam("region") Region region,
            @RequestParam("homeType") String homeType) {

        List<House> houses = houseService.searchHouses(title, town, region, homeType);
        return new ResponseEntity<>(houses, HttpStatus.OK);
    }

}
