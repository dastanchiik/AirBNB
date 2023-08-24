package com.example.airbnb.service;

import com.example.airbnb.dto.response.*;
import com.example.airbnb.models.Booking;
import com.example.airbnb.models.FeedBack;
import com.example.airbnb.models.House;
import com.example.airbnb.models.User;
import com.example.airbnb.models.enums.Role;
import com.example.airbnb.models.enums.Status;
import com.example.airbnb.models.enums.StatusRequest;
import com.example.airbnb.repositories.BookingRepository;
import com.example.airbnb.repositories.FeedbackRepository;
import com.example.airbnb.repositories.HouseRepository;
import com.example.airbnb.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {
    private final HouseRepository houseRepository;
    private final UserRepository repository;
    private final BookingRepository bookingRepository;
    private final FeedbackRepository feedbackRepository;

    public List<HomeResponseForGetAll> getAllApplications() {
        List<HomeResponseForGetAll> responses = new ArrayList<>();
        for (House house : houseRepository.getAllApplications()) {
            HomeResponseForGetAll response = new HomeResponseForGetAll();
            response.setId(String.valueOf(house.getId()));
            if (!house.getPhotos().isEmpty() && house.getPhotos().get(0) != null) {
                response.setPhoto(house.getPhotos().get(0));
            } else {
                response.setPhoto(null);
            }
            response.setRate(String.valueOf(house.getRating()));
            response.setTitle(house.getTitle());
            response.setPrice(String.valueOf(house.getPrice()));
            response.setAddress(house.getAddress());
            response.setMaxGuests(String.valueOf(house.getMaxGuests()));
            responses.add(response);
        }
        return responses;
    }

    public HomeResponseForGetOne getApplicationById(Long id) {
        House house = houseRepository.getApplicationById(id);
        HomeResponseForGetOne response = new HomeResponseForGetOne();
        if (house == null) {
            return response;
        } else {
            response.setId(String.valueOf(house.getId()));
            response.setDescription(house.getDescription());
            response.setMaxGuests(String.valueOf(house.getMaxGuests()));
            response.setHomeType(String.valueOf(house.getHomeType()));
            response.setPhotos(house.getPhotos());
            response.setTitle(house.getTitle());
            response.setAddress(house.getAddress());
            if (house.getUser() != null) {
                response.setUserId(String.valueOf(house.getUser().getId()));
            } else if (house.getUser() == null) {
                response.setUserId(null);
            }
            return response;
        }
    }

    public void selectStatusById(Long id, StatusRequest status) {
        House house = houseRepository.getById(id);

        if (status == StatusRequest.ACCEPTED) {
            house.setStatus(Status.ACCEPTED);
            log.info("successfully worked");
            houseRepository.save(house);
        } else if (status == StatusRequest.REJECTED) {
            house.setStatus(Status.REJECTED);
            log.info("successfully worked");
            houseRepository.save(house);
        } else if (status == StatusRequest.DELETE) {
            houseRepository.delete(house);
        }
    }

    @Scheduled(fixedRate = 300000)
    @Transactional
    public void findAndRemoveSimilarUsers() {
        String email = "";
        for (int i = repository.findAll().size(); i >= 0; i--) {
            Optional<User> userOptional = repository.findById((long) i);
            email = userOptional.map(User::getEmail).orElse("DefaultEmail"); // Или какой-то другой дефолтный адрес
            List<User> userList = repository.findLastUsersWithSimilarEmail(email);
            int size = userList.size();
            for (int j = 0; j < size; j++) {
                User user = userList.get(j);
                if (j == size - 1) {
                    continue;
                }
                repository.delete(user);
            }
        }
    }

    public List<AllUserResponseForAdmin> getAllUsersForAdmin() {
        List<AllUserResponseForAdmin> responses = new ArrayList<>();
        for (User user : repository.findAll()) {
            if (user.getRole() == Role.USER) {
                AllUserResponseForAdmin response = new AllUserResponseForAdmin();
                response.setId(String.valueOf(user.getId()));
                response.setName(user.getName());
                response.setEmail(user.getEmail());
                int countHouses = 0;
                int countBookings = 0;
                for (House house : houseRepository.findAll()) {
                    if (house.getUser().getId().equals(user.getId())) {
                        countHouses++;
                    }
                }
                for (Booking booking : bookingRepository.findAll()) {
                    if (booking.getUser().getId().equals(user.getId())) {
                        countBookings++;
                    }
                }
                response.setCountBooking(String.valueOf(countBookings));
                response.setCountAnnouncement(String.valueOf(countHouses));
                responses.add(response);
            }
        }
        return responses;
    }

    public OneUserResponseForAdmin getStudentByIdForAdmin(Long id) {
        User user = repository.findById(id).orElseThrow();
        OneUserResponseForAdmin response = new OneUserResponseForAdmin();
        response.setId(String.valueOf(user.getId()));
        response.setPhoto(user.getPhoto());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return response;
    }

    public List<Object> getAllUserBookings(Long id) {
        List<Object>users = new ArrayList<>();
        users.add(getStudentByIdForAdmin(id));
        List<BookingResponse> responses = new ArrayList<>();
        User user = repository.findById(id).orElseThrow();
        for (Booking booking : bookingRepository.findAll()) {
            if (booking.getUser() != null) {
                if (booking.getUser().getId().equals(user.getId())) {
                    BookingResponse response = new BookingResponse();
                    response.setId(String.valueOf(booking.getHouse().getId()));
                    response.setAddress(booking.getHouse().getAddress());
                    response.setTotalMoney(booking.getTotalMoney());
                    if (!booking.getHouse().getPhotos().isEmpty() && booking.getHouse().getPhotos().get(0) != null) {
                        response.setPhoto(booking.getHouse().getPhotos().get(0));
                    } else {
                        response.setPhoto(null);
                    }
                    response.setName(booking.getHouse().getTitle());
                    response.setMaxGuests(String.valueOf(booking.getHouse().getMaxGuests()));
                    response.setRating(String.valueOf(booking.getHouse().getRating()));
                    responses.add(response);
                }
            }
        }
        users.addAll(responses);
        return users;
    }

    public List<Object> getHousesByUserId(Long id,Boolean blocking) {
        List<Object> user = new ArrayList<>();
        user.add(getStudentByIdForAdmin(id));
        List<HomeResponseForGetAll> responses = new ArrayList<>();
        for (House house : houseRepository.findAll()) {
            if (blocking){
                house.setBlocked(true);
                houseRepository.save(house);
            }
            if (Objects.equals(house.getUser().getId(), id) && !house.isBlocked()) {
                HomeResponseForGetAll response = new HomeResponseForGetAll();
                response.setId(String.valueOf(house.getId()));
                if (!house.getPhotos().isEmpty() && house.getPhotos().get(0) != null) {
                    response.setPhoto(house.getPhotos().get(0));
                } else {
                    response.setPhoto(null);
                }
                response.setRate(String.valueOf(house.getRating()));
                response.setTitle(house.getTitle());
                response.setPrice(String.valueOf(house.getPrice()));
                response.setAddress(house.getAddress());
                response.setMaxGuests(String.valueOf(house.getMaxGuests()));
                responses.add(response);
            }
        }
        user.addAll(responses);
        return user;
    }

    public List<Object> getHouseById(Long id) {
        House house = houseRepository.findById(id).get();
        List<Object> responses = new ArrayList<>();
        HomeResponseForGetOne response = new HomeResponseForGetOne();
        if (house == null) {
            return responses;
        } else {
            response.setId(String.valueOf(house.getId()));
            response.setDescription(house.getDescription());
            response.setRating(String.valueOf(house.getRating()));
            response.setMaxGuests(String.valueOf(house.getMaxGuests()));
            response.setHomeType(String.valueOf(house.getHomeType()));
            response.setPhotos(house.getPhotos());
            response.setTitle(house.getTitle());
            response.setAddress(house.getAddress());
            if (house.getUser() != null) {
                response.setUserId(String.valueOf(house.getUser().getId()));
            } else if (house.getUser() == null) {
                response.setUserId(null);
            }
            responses.add(response);
            for (FeedBack feedBack : feedbackRepository.findAll()) {
                FeedbackResponseFindByID feedBackResponse = new FeedbackResponseFindByID();
                feedBackResponse.setName(feedBack.getOwner().getName());
                feedBackResponse.setPhoto(feedBack.getOwner().getPhoto());
                feedBackResponse.setId(String.valueOf(feedBack.getId()));
                feedBackResponse.setLikeCount(feedBack.getLikeCount());
                feedBackResponse.setDislikeCount(feedBack.getDislikeCount());
                feedBackResponse.setUserId(feedBack.getOwner().getId());
                feedBackResponse.setRating(feedBack.getStar());
                feedBackResponse.setDescription(feedBack.getFeedback());
                responses.add(feedBackResponse);
            }
            return responses;
        }
    }

    public void updateBlockedHouse(Long id) {
        House house = houseRepository.findById(id).orElseThrow();
        if (house.isBlocked()) {
            house.setBlocked(false);
        } else {
            house.setBlocked(true);
        }
    }
}
