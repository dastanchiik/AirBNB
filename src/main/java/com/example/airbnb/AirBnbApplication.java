package com.example.airbnb;

import com.example.airbnb.models.House;
import com.example.airbnb.models.User;
import com.example.airbnb.models.enums.Role;
import com.example.airbnb.repositories.HouseRepository;
import com.example.airbnb.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class AirBnbApplication {
	private final HouseRepository houseRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder encoder;
	public static void main(String[] args) {
		SpringApplication.run(AirBnbApplication.class, args);
	}

	@PostConstruct
	public void init(){
		User user = new User();
		user.setEmail("d@gmail.com");
		user.setPassword(encoder.encode(",dastan.111217D"));
		user.setRole(Role.USER);
		userRepository.save(user);

		User user1 = new User();
		user1.setEmail("dastan@gmail.com");
		user1.setPassword(encoder.encode(",dastan.111217D"));
		user1.setRole(Role.USER);
		userRepository.save(user1);

		House house = new House();
		house.setTitle("title");
		house.setBlocked(false);
		house.setUser(user1);
		house.setAddress("Kochkor-Ata");
		houseRepository.save(house);

		House house1 = new House();
		house1.setTitle("title1212");
		house1.setBlocked(true);
		house1.setUser(user1);
		house1.setAddress("Jalal-Abad");
		houseRepository.save(house1);

		House house2 = new House();
		house2.setTitle("Baytik");
		house2.setBlocked(false);
		house2.setUser(user);
		house2.setAddress("Bishkek");
		houseRepository.save(house2);
	}
}
