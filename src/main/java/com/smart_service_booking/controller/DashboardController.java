package com.smart_service_booking.controller;

import com.smart_service_booking.entity.Booking;
import com.smart_service_booking.entity.SupportTicket;
import com.smart_service_booking.enums.BookingStatus;
import com.smart_service_booking.enums.TicketStatus;
import com.smart_service_booking.repository.BookingRepository;
import com.smart_service_booking.repository.SupportTicketRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final BookingRepository bookingRepository;
    private final SupportTicketRepository supportTicketRepository;

    public DashboardController(BookingRepository bookingRepository,
                               SupportTicketRepository supportTicketRepository) {
        this.bookingRepository = bookingRepository;
        this.supportTicketRepository = supportTicketRepository;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboard() {
        Map<String, Object> data = new HashMap<>();

        List<Booking> allBookings = bookingRepository.findAll();
        List<SupportTicket> allTickets = supportTicketRepository.findAll();

        data.put("totalBookings", allBookings.size());
        data.put("pending", allBookings.stream().filter(b -> b.getStatus() == BookingStatus.PENDING).count());
        data.put("completed", allBookings.stream().filter(b -> b.getStatus() == BookingStatus.COMPLETED).count());
        data.put("openTickets", allTickets.stream().filter(t -> t.getStatus() != TicketStatus.CLOSED).count());
        data.put("bookings", allBookings);
        data.put("tickets", allTickets);

        return ResponseEntity.ok(data);
    }
}