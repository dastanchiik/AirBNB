package com.example.airbnb;

import com.example.airbnb.models.Booking;
import com.example.airbnb.models.FeedBack;
import com.example.airbnb.models.House;
import com.example.airbnb.models.User;
import com.example.airbnb.models.enums.Role;
import com.example.airbnb.repositories.BookingRepository;
import com.example.airbnb.repositories.FeedbackRepository;
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
		houseRepository.save(house1);

		House home = new House();
		home.setTitle("Oomat Stroi");
		home.setUser(user12);
		houseRepository.save(home);

		House house = new House();
		house.setTitle("B");
		house.setUser(user);
		houseRepository.save(house);

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
