package com.example.airbnb;

import com.example.airbnb.models.House;
import com.example.airbnb.models.User;
import com.example.airbnb.models.enums.Status;
import com.example.airbnb.repositories.HouseRepository;
import com.example.airbnb.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
@EnableScheduling
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
		user.setEmail("dosyafree@gmail.com");
		user.setUserName("Dastanchik");
		userRepository.save(user);

		User person = new User();
		person.setEmail("dosyafree32@gmail.com");
		person.setUserName("dastanchik");
		userRepository.save(person);

		User user1 = new User();
		user1.setEmail("dosyafree@gmail.com");
		user1.setUserName("dastanchik");
		userRepository.save(user1);
		User user2 = new User();
		user2.setEmail("dosyafree32@gmail.com");
		user2.setUserName("dastanchik");
		userRepository.save(user2);

		User user3 = new User();
		user3.setEmail("dosyafree32@gmail.com");
		user3.setUserName("dastanchik");
		userRepository.save(user3);

		User user4 = new User();
		user4.setEmail("dosyafre@gmail.com");
		user4.setUserName("dastanchik");
		userRepository.save(user4);

		User user5 = new User();
		user5.setEmail("dosyafres@gmail.com");
		user5.setUserName("dastanchik");
		userRepository.save(user5);

		User user7 = new User();
		user7.setEmail("dosyafres@gmail.com");
		user7.setUserName("dastanchik");
		userRepository.save(user7);

		User user6 = new User();
		user6.setEmail("dosyafre@gmail.com");
		user6.setUserName("dastanchik");
		userRepository.save(user6);

		House house = new House();
		house.setTitle("Bishkek");
		house.setStatus(Status.ACCEPTED);
		houseRepository.save(house);

		House home = new House();
		home.setTitle("Kochkor-Ata");
		home.setUser(user6);
		houseRepository.save(home);
		houseRepository.save(home);

	}
}
