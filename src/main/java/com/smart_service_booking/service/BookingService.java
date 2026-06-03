package com.smart_service_booking.service;

import com.smart_service_booking.entity.Booking;
import com.smart_service_booking.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final SmsService smsService;

    public BookingService(BookingRepository bookingRepository,
                          SmsService smsService) {
        this.bookingRepository = bookingRepository;
        this.smsService = smsService;
    }

    // CREATE BOOKING
    public Booking createBooking(Booking booking) {

        int token = bookingRepository.countByStatus("PENDING") + 1;
        booking.setTokenNumber(token);
        booking.setStatus("PENDING");

        // dummy technician location
        booking.setTechnicianLat(28.6139);
        booking.setTechnicianLng(77.2090);

        return bookingRepository.save(booking);
    }

    // GET ALL
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // GET BY ID
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    // UPDATE STATUS + SMS
    public Booking updateStatus(Long id, String status) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(status);

        if ("CONFIRMED".equals(status)) {
            smsService.sendSms(
                    "9999999999",
                    "👷 Technician assigned for booking ID " + booking.getId()
            );
        }

        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
