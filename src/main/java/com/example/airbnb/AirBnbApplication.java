package com.example.airbnb;

import com.example.airbnb.models.House;
import com.example.airbnb.models.enums.Region;
import com.example.airbnb.repositories.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import javax.annotation.PostConstruct;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class AirBnbApplication {
	public static void main(String[] args) {
		SpringApplication.run(AirBnbApplication.class, args);
	}


    private final HouseRepository searchHousesRepository;


    @PostConstruct
    public void init() {

        House house = new House();
        house.setRegion(Region.CHUI);
        house.setTown("Asd");
        house.setTitle("ffffff");
        searchHousesRepository.save(house);

        House house1 = new House();
        house1.setRegion(Region.BATKEN);
        house1.setTown("Karakol");
        house1.setTitle("ddddd");
        searchHousesRepository.save(house1);

        House house2 = new House();
        house2.setRegion(Region.JALAL_ABAD);
        house2.setTown("Kochkorata");
        house2.setTitle("aaaaaa");
        searchHousesRepository.save(house2);
    }
}
