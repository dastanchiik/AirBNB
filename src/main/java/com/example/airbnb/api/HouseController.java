package com.example.airbnb.api;

import com.example.airbnb.dto.response.HomeResponseForGetAll;
import com.example.airbnb.models.enums.Region;
import com.example.airbnb.service.HouseService;
import com.example.airbnb.dto.response.HouseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;

//@GetMapping("/search")
//public List<HouseResponse> searchHousesByTownOrTitleOrRegion(
//        @RequestParam String searchTerm) {
//
//    List<HouseResponse> searchResults = houseService.searchByTownOrTitleIgnoreCase(searchTerm);
//    return searchResults;
//}

    @GetMapping("/search/houses")
    public List<HomeResponseForGetAll> getHousesByUserId(
            @RequestParam Region region) {
        return houseService.searchHousesByRegion(region);
    }

}




