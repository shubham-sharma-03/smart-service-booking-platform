package com.smart_service_booking.controller;

import com.smart_service_booking.dto.BookingRequest;
import com.smart_service_booking.entity.Booking;
import com.smart_service_booking.enums.BookingStatus;
import com.smart_service_booking.repository.BookingRepository;
import com.smart_service_booking.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class BookingController {

    private final BookingRepository bookingRepository;
    private final BookingService bookingService;

    public BookingController(BookingRepository bookingRepository, BookingService bookingService) {
        this.bookingRepository = bookingRepository;
        this.bookingService = bookingService;
    }

    @GetMapping("/bookings/me")
    public ResponseEntity<List<Booking>> getMyBookings() {
        return ResponseEntity.ok(bookingRepository.findAll());
    }

    @GetMapping("/bookings/stats")
    public ResponseEntity<Map<String, Long>> getBookingStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("total", bookingRepository.count());
        stats.put("pending", bookingRepository.countByStatus(BookingStatus.PENDING));
        stats.put("completed", bookingRepository.countByStatus(BookingStatus.COMPLETED));
        stats.put("cancelled", bookingRepository.countByStatus(BookingStatus.CANCELLED));
        return ResponseEntity.ok(stats);
    }

    @PostMapping("/bookings")
    public ResponseEntity<Booking> createBooking(@RequestBody Map<String, Object> rawRequest) {
        BookingRequest request = new BookingRequest();
        request.setUserName((String) rawRequest.get("userName"));
        request.setServiceType((String) rawRequest.get("serviceType"));
        request.setPhoneNumber((String) rawRequest.get("customerPhone"));
        request.setAddress((String) rawRequest.get("address"));
        request.setNotes((String) rawRequest.get("notes"));

        // NEW: read the randomly-assigned technician sent from the frontend
        request.setTechnicianName((String) rawRequest.get("technicianName"));
        request.setTechnicianPhone((String) rawRequest.get("technicianPhone"));

        Booking saved = bookingService.createBooking(request);
        return ResponseEntity.ok(saved);
    }

    @PatchMapping("/bookings/{id}/cancel")
    public ResponseEntity<?> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok("Cancelled");
    }
}