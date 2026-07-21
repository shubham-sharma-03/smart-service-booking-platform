package com.smart_service_booking.controller;

import com.smart_service_booking.entity.SupportTicket;
import com.smart_service_booking.repository.SupportTicketRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SupportTicketController {

    private final SupportTicketRepository supportTicketRepository;

    public SupportTicketController(SupportTicketRepository supportTicketRepository) {
        this.supportTicketRepository = supportTicketRepository;
    }

    @GetMapping("/support-tickets/me")
    public ResponseEntity<List<SupportTicket>> getMyTickets() {
        return ResponseEntity.ok(supportTicketRepository.findAll());
    }

    @PostMapping("/support-tickets")
    public ResponseEntity<SupportTicket> createTicket(@RequestBody SupportTicket ticket) {
        return ResponseEntity.ok(supportTicketRepository.save(ticket));
    }
}