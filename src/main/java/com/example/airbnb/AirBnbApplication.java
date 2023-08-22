package com.example.airbnb;

import com.example.airbnb.models.House;
import com.example.airbnb.models.User;
import com.example.airbnb.models.enums.HomeType;
import com.example.airbnb.models.enums.Region;
import com.example.airbnb.models.enums.Role;
import com.example.airbnb.repositories.HouseRepository;
import com.example.airbnb.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class AirBnbApplication {
	public static void main(String[] args) {
		SpringApplication.run(AirBnbApplication.class, args);
	}

    public static void main(String[] args) {
        SpringApplication.run(AirBnbApplication.class, args);
    }

    private final HouseRepository houseRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        House house = new House();
        house.setHomeType(HomeType.APARTMENT);
        house.setAddress("fffffffffffffffffff");
        house.setTitle("aaaaaaaaaa");
        house.setTown("Bishkek");
        houseRepository.save(house);
        User user = new User();
        user.setEmail("string");
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode("string"));
        userRepository.save(user);
    }
}
