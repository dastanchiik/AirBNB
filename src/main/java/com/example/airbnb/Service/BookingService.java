package com.example.airbnb.Service;

import com.example.airbnb.dto.response.BookingResponse;
import com.example.airbnb.dto.response.BookingResponseFindByID;
import com.example.airbnb.models.Booking;
import com.example.airbnb.repositories.BookingRepository;
import com.example.airbnb.repositories.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final HouseRepository houseRepository;

    public Booking saveBooking(Booking booking) {
        try {
            return bookingRepository.save(booking);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }


    public void deleteById(Long id) {
        try {
            bookingRepository.deleteById(id);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public void update(Long id, Booking booking) {
        try {
            Booking booking1 = bookingRepository.findById(id).orElseThrow();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

    }


    public BookingResponseFindByID findById(Long id) {
        Booking booking1 = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException(String.valueOf(id)));
        BookingResponseFindByID bookingResponseFindByID = new BookingResponseFindByID();
        bookingResponseFindByID.setName(houseRepository.findById(id).get().getTitle());
        bookingResponseFindByID.setAddresses(houseRepository.findById(id).get().getAddress());
        bookingResponseFindByID.setDescription(houseRepository.findById(id).get().getDescription());
        bookingResponseFindByID.setMaxGuest(houseRepository.findById(id).get().getMaxGuests());
        bookingResponseFindByID.setId(String.valueOf(booking1.getId()));
        bookingResponseFindByID.setUserId(String.valueOf(booking1.getUser().getId()));
        bookingResponseFindByID.setHomeType(String.valueOf(houseRepository.findById(id).get().getHomeType()));
        return bookingResponseFindByID;

    }


//    public List<BookingResponse> findAll() {
//        List<BookingResponse> list = new ArrayList<>();
//        for (Booking booking : bookingRepository.findAll()) {
//            BookingResponse bookingResponse = new BookingResponse();
//            bookingResponse.setTotalMoney(booking.getTotalMoney());
//            bookingResponse.setCarNumber(booking.getCardNumber());
//            bookingResponse.setStatus(booking.getStatus());
//            bookingResponse.setCheckin(booking.getCheckin());
//            bookingResponse.setCheckout(booking.getCheckout());
//
//        }
//        return list;
//    }
}