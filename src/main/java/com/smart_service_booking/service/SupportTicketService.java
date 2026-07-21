package com.smart_service_booking.service;

import com.smart_service_booking.entity.SupportTicket;
import com.smart_service_booking.enums.TicketStatus;  // or whatever your enum is
import com.smart_service_booking.repository.SupportTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupportTicketService {

    @Autowired
    private SupportTicketRepository supportTicketRepository;

    public List<SupportTicket> getAllTickets() {
        return supportTicketRepository.findAll();
    }

    public SupportTicket getTicketById(Long id) {
        return supportTicketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found: " + id));
    }

    public SupportTicket createTicket(SupportTicket ticket) {
        return supportTicketRepository.save(ticket);
    }

    // Fix: Use proper enum or String based on your entity
    public long getOpenTicketCount() {
        // If your entity uses String status:
        return supportTicketRepository.countByStatus("OPEN");

        // If your entity uses enum TicketStatus:
        // return supportTicketRepository.countByStatus(TicketStatus.OPEN);
    }

    public List<SupportTicket> getTicketsByStatus(String status) {
        return supportTicketRepository.findByStatus(status);
    }

    public List<SupportTicket> getTicketsByBookingId(Long bookingId) {
        return supportTicketRepository.findByBookingId(bookingId);
    }
}