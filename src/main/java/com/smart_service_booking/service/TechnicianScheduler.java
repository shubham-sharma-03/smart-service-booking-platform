package com.smart_service_booking.service;

import com.smart_service_booking.entity.Booking;
import com.smart_service_booking.entity.Technician;
import com.smart_service_booking.enums.BookingStatus;
import com.smart_service_booking.repository.BookingRepository;
import com.smart_service_booking.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicianScheduler {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TechnicianRepository technicianRepository;

    // Run every 5 minutes
    @Scheduled(fixedRate = 300000)
    public void assignTechnicians() {
        List<Booking> pendingBookings = bookingRepository.findByStatus(BookingStatus.PENDING);

        for (Booking booking : pendingBookings) {
            List<Technician> availableTechs = technicianRepository.findByAvailableTrue();

            if (!availableTechs.isEmpty()) {
                Technician tech = availableTechs.get(0);
                booking.setTechnician(tech);
                booking.setStatus(BookingStatus.CONFIRMED);
                bookingRepository.save(booking);
            }
        }
    }
}