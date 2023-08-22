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

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class AirBnbApplication {
	private final HouseRepository houseRepository;
	private final UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(AirBnbApplication.class, args);
	}

	@PostConstruct
	public void  init (){
		User user = new User();
		user.setName("Dastan");
		user.setEmail("dosyafree32@gmail.com");
		user.setRole(Role.USER);
		userRepository.save(user);

//		User user1 = new User();
//		user1.setName("Daniel");
//		user1.setEmail("daniel@gmail.com");
//		userRepository.save(user1);

		House house1 = new House();
		house1.setTitle("Baytik");
		house1.setUser(user);
		houseRepository.save(house1);

		House home = new House();
		home.setTitle("OOmat Stroi");
		home.setUser(user);
		houseRepository.save(home);

		House house = new House();
		house.setTitle("B");
		house.setUser(user);
		houseRepository.save(house);
	}
}
