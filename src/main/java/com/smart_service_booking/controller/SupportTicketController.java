package com.smart_service_booking.controller;

import com.smart_service_booking.entity.SupportTicket;
import com.smart_service_booking.service.SupportTicketService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/support")
public class SupportTicketController {

    private final SupportTicketService service;

    public SupportTicketController(SupportTicketService service) {
        this.service = service;
    }

    @PostMapping
    public SupportTicket create(@RequestBody SupportTicket ticket) {
        return service.createTicket(ticket);
    }
}


