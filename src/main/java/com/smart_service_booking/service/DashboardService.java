package com.smart_service_booking.service;

import com.smart_service_booking.enums.BookingStatus;
import com.smart_service_booking.repository.BookingRepository;
import com.smart_service_booking.repository.SupportTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private SupportTicketRepository supportTicketRepository;

    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();

        stats.put("totalBookings", bookingRepository.count());
        stats.put("pendingBookings", bookingRepository.countByStatus(BookingStatus.PENDING));
        stats.put("completedBookings", bookingRepository.countByStatus(BookingStatus.COMPLETED));
        stats.put("cancelledBookings", bookingRepository.countByStatus(BookingStatus.CANCELLED));
        stats.put("openTickets", supportTicketRepository.countByStatus("OPEN"));
        stats.put("serviceDistribution", bookingRepository.countByServiceType());
        stats.put("weeklyStats", bookingRepository.getWeeklyStats());

        return stats;
    }
}