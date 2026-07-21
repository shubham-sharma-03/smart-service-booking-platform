package com.smart_service_booking.service;

import com.smart_service_booking.enums.BookingStatus;
import com.smart_service_booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private BookingRepository bookingRepository;

    public Map<String, Object> getDashboardStats() {
        Map<String, Object> dashboard = new HashMap<>();

        long totalBookings = bookingRepository.count();
        long pending = bookingRepository.countByStatus(BookingStatus.PENDING);
        long confirmed = bookingRepository.countByStatus(BookingStatus.CONFIRMED);
        long completed = bookingRepository.countByStatus(BookingStatus.COMPLETED);
        long cancelled = bookingRepository.countByStatus(BookingStatus.CANCELLED);

        dashboard.put("totalBookings", totalBookings);
        dashboard.put("pendingBookings", pending);
        dashboard.put("confirmedBookings", confirmed);
        dashboard.put("completedBookings", completed);
        dashboard.put("cancelledBookings", cancelled);


        return dashboard;
    }
}