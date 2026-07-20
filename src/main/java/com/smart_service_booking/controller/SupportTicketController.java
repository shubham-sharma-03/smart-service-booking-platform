package com.smart_service_booking.controller;

import com.smart_service_booking.dto.ApiResponse;
import com.smart_service_booking.dto.SupportTicketRequest;
import com.smart_service_booking.entity.SupportTicket;
import com.smart_service_booking.service.SupportTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/support")
@CrossOrigin(origins = "*")
public class SupportTicketController {

    @Autowired
    private SupportTicketService supportTicketService;

    @GetMapping
    public ResponseEntity<List<SupportTicket>> getAllTickets() {
        return ResponseEntity.ok(supportTicketService.getAllTickets());
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createTicket(@RequestBody SupportTicketRequest request) {
        SupportTicket ticket = supportTicketService.createTicket(request);
        return ResponseEntity.ok(ApiResponse.success("Ticket created successfully", ticket));
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<ApiResponse> resolveTicket(@PathVariable Long id) {
        supportTicketService.resolveTicket(id);
        return ResponseEntity.ok(ApiResponse.success("Ticket resolved"));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse> updateStatus(@PathVariable Long id, @RequestParam String status) {
        supportTicketService.updateTicketStatus(id, status);
        return ResponseEntity.ok(ApiResponse.success("Status updated"));
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<List<SupportTicket>> getByBooking(@PathVariable Long bookingId) {
        return ResponseEntity.ok(supportTicketService.getTicketsByBookingId(bookingId));
    }
}