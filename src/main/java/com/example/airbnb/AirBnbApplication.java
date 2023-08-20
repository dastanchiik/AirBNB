package com.example.airbnb;

import com.example.airbnb.models.House;
import com.example.airbnb.models.User;
import com.example.airbnb.models.enums.Status;
import com.example.airbnb.repositories.HouseRepository;
import com.example.airbnb.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
public class AirBnbApplication {
	private final HouseRepository houseRepository;
	private final UserRepository userRepository;
	public static void main(String[] args) {
		try {
		SpringApplication.run(AirBnbApplication.class, args);
		}catch (Exception e) {
			System.out.println("Successfully classes -> relationship does not exist");
		}
	}

	@PostConstruct
	public void init(){
		User user = new User();
		user.setUserName("Dastanchik");
		userRepository.save(user);

		House house = new House();
		house.setTitle("Bishkek");
		house.setStatus(Status.ACCEPTED);
		houseRepository.save(house);

		House home = new House();
		home.setTitle("Kochkor-Ata");
		home.setUser(user);
		houseRepository.save(home);
	}
}
