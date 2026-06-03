package com.smart_service_booking.service;

import com.smart_service_booking.entity.SupportTicket;
import com.smart_service_booking.repository.SupportTicketRepository;
import org.springframework.stereotype.Service;

@Service
public class SupportTicketService {

    private final SupportTicketRepository repo;

    public SupportTicketService(SupportTicketRepository repo) {
        this.repo = repo;
    }

    public SupportTicket createTicket(SupportTicket ticket) {
        ticket.setStatus("OPEN");
        return repo.save(ticket);
    }

}
