package com.example.airbnb;

import com.example.airbnb.models.Booking;
import com.example.airbnb.models.FeedBack;
import com.example.airbnb.models.House;
import com.example.airbnb.models.User;
import com.example.airbnb.models.enums.HomeType;
import com.example.airbnb.models.enums.Role;
import com.example.airbnb.models.enums.Status;
import com.example.airbnb.repositories.BookingRepository;
import com.example.airbnb.repositories.FeedbackRepository;
import com.example.airbnb.repositories.HouseRepository;
import com.example.airbnb.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class AirBnbApplication {
	private final HouseRepository houseRepository;
	private final UserRepository userRepository;
	private final BookingRepository bookingRepository;
	private final FeedbackRepository feedbackRepository;
	public static void main(String[] args) {
		SpringApplication.run(AirBnbApplication.class, args);
	}

	@PostConstruct
	public void  init (){
		User user = new User();
		user.setName("Dastan");
		user.setEmail("d@gmail.com");
		user.setRole(Role.USER);
		userRepository.save(user);

		User user12 = new User();
		user12.setName("Dastan");
		user12.setEmail("d1@gmail.com");
		user12.setRole(Role.USER);
		userRepository.save(user12);

		House house1 = new House();
		house1.setTitle("Baytik");
		house1.setUser(user);
		house1.setStatus(Status.ACCEPTED);
		house1.setHomeType(HomeType.HOUSE);
		house1.setPrice(123456789L);
		house1.setRating(5.0);
		houseRepository.save(house1);

		House home = new House();
		home.setTitle("Oomat Stroi");
		home.setUser(user12);
		home.setStatus(Status.ACCEPTED);
		home.setHomeType(HomeType.HOUSE);
		home.setPrice(12345678L);
		home.setRating(4.9);
		houseRepository.save(home);

		House house = new House();
		house.setTitle("B");
		house.setUser(user);
		house.setStatus(Status.ACCEPTED);
		house.setHomeType(HomeType.HOUSE);
		house.setPrice(1234567L);
		house.setRating(4.8);
		houseRepository.save(house);

		House house3 = new House();
		house3.setTitle("A");
		house3.setUser(user);
		house3.setStatus(Status.ACCEPTED);
		house3.setHomeType(HomeType.APARTMENT);
		house3.setPrice(123456L);
		house3.setRating(4.7);
		houseRepository.save(house3);

		House house10 = new House();
		house10.setTitle("D");
		house10.setUser(user);
		house10.setStatus(Status.REJECTED);
		house10.setHomeType(HomeType.APARTMENT);
		house10.setPrice(12345L);
		house10.setRating(4.6);
		houseRepository.save(house10);

		House all = new House();
		all.setTitle("all");
		all.setUser(user);
		all.setStatus(Status.ACCEPTED);
		all.setHomeType(HomeType.HOUSE);
		all.setPrice(12343L);
		all.setRating(4.5);
		houseRepository.save(all);

		FeedBack feedBack = new FeedBack();
		feedBack.setOwner(user);
		feedBack.setStar(5);
		feedBack.setFeedback("it's best apartment");
		feedBack.setHouse(house1);
		feedbackRepository.save(feedBack);

		FeedBack feedBack1 = new FeedBack();
		feedBack1.setOwner(user12);
		feedBack1.setStar(5);
		feedBack1.setFeedback("it's best apartment");
		feedBack1.setHouse(house1);
		feedbackRepository.save(feedBack1);

		Booking booking = new Booking();
		booking.setHouse(house1);
		booking.setUser(user);
		bookingRepository.save(booking);

		Booking booking1 = new Booking();
		booking1.setHouse(home);
		booking1.setUser(user);
		bookingRepository.save(booking1);


	}
}
