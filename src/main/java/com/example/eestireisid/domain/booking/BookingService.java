package com.example.eestireisid.domain.booking;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Resource
    private BookingRepository bookingRepository;

    public void saveBooking(Booking booking) {
        bookingRepository.save(booking);
    }
}
