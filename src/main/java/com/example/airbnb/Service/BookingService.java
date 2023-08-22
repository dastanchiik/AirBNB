package com.example.airbnb.Service;

import com.example.airbnb.dto.response.BookingResponse;
import com.example.airbnb.dto.response.BookingResponseFindByID;
import com.example.airbnb.models.Booking;
import com.example.airbnb.repositories.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;

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


    public Booking findById(Long id) {
            Booking booking1 = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException(String.valueOf(id)));
        BookingResponseFindByID bookingResponseFindByID = new BookingResponseFindByID();
        bookingResponseFindByID.setName(booking1.get);


    }

    public List<BookingResponse> findAll() {
        List<BookingResponse> list = new ArrayList<>();
        for (Booking booking : bookingRepository.findAll()) {
            BookingResponse bookingResponse = new BookingResponse();
            bookingResponse.setDays(booking.getDays());
            bookingResponse.setTotalMoney(booking.getTotalMoney());
            bookingResponse.setCarNumber(booking.getCardNumber());
            bookingResponse.setStatus(booking.getStatus());
            bookingResponse.setCheckin(booking.getCheckin());
            bookingResponse.setCheckout(booking.getCheckout());

        }
        return list;
    }
}