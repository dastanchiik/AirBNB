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
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class AirBnbApplication {
	private final UserRepository userRepository;
	private final HouseRepository houseRepository;
	private final PasswordEncoder encoder;
	public static void main(String[] args) {
		SpringApplication.run(AirBnbApplication.class, args);
	}

	@PostConstruct
	public void init() {
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
		house.setRegion(Region.TALAS);
		house.setHomeType(HomeType.HOUSE);
		house.setUser(user1);
		house.setAddress("Kochkor-Ata");
		houseRepository.save(house);

		House house1 = new House();
		house1.setTitle("title1212");
		house1.setRegion(Region.CHUI);
		house1.setHomeType(HomeType.HOUSE);
		house1.setBlocked(false);
		house1.setUser(user1);
		house1.setAddress("Jalal-Abad");
		houseRepository.save(house1);

		House house2 = new House();
		house2.setTitle("J");
		house2.setBlocked(false);
		house2.setRegion(Region.TALAS);
		house2.setHomeType(HomeType.HOUSE);
		house2.setUser(user1);
		house2.setAddress("Bishkek");
		houseRepository.save(house2);

		House house3 = new House();
		house3.setTitle("F");
		house3.setBlocked(false);
		house3.setUser(user1);
		house3.setRegion(Region.OSH);
		house3.setHomeType(HomeType.HOUSE);
		house3.setAddress("Bishkek");
		houseRepository.save(house3);

		House house4 = new House();
		house4.setTitle("E");
		house4.setBlocked(false);
		house4.setHomeType(HomeType.HOUSE);
		house4.setUser(user1);
		house4.setRegion(Region.BATKEN);
		house4.setAddress("D");
		houseRepository.save(house4);

		House house5 = new House();
		house5.setTitle("C");
		house5.setBlocked(false);
		house5.setRegion(Region.JALAL_ABAD);
		house5.setHomeType(HomeType.HOUSE);
		house5.setUser(user1);
		house5.setAddress("Bishkek");
		houseRepository.save(house5);

		House house6 = new House();
		house6.setTitle("B");
		house6.setBlocked(false);
		house6.setRegion(Region.NARYN);
		house6.setUser(user1);
		house6.setHomeType(HomeType.APARTMENT);
		house6.setAddress("Bishkek");
		houseRepository.save(house6);

		House house7 = new House();
		house7.setTitle("A");
		house7.setHomeType(HomeType.HOUSE);
		house7.setBlocked(false);
		house7.setRegion(Region.YSSYK_KOL);
		house7.setUser(user1);
		house7.setAddress("Bishkek");
		houseRepository.save(house7);
	}
}