package com.smart_service_booking.service;

import com.smart_service_booking.dto.SupportTicketRequest;
import com.smart_service_booking.entity.SupportTicket;
import com.smart_service_booking.exception.ResourceNotFoundException;
import com.smart_service_booking.repository.SupportTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class SupportTicketService {

    @Autowired
    private SupportTicketRepository supportTicketRepository;

    @Autowired
    private EmailService emailService;

    public List<SupportTicket> getAllTickets() {
        return supportTicketRepository.findAll();
    }

    public SupportTicket getTicketById(Long id) {
        return supportTicketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found: " + id));
    }

    public SupportTicket createTicket(SupportTicketRequest request) {
        SupportTicket ticket = new SupportTicket();
        ticket.setBookingId(request.getBookingId());
        ticket.setIssue(request.getIssue());
        ticket.setStatus("OPEN");

        SupportTicket saved = supportTicketRepository.save(ticket);

        // Notify admin via email
        emailService.sendTicketNotification(saved);

        return saved;
    }

    public void resolveTicket(Long id) {
        SupportTicket ticket = getTicketById(id);
        ticket.setStatus("RESOLVED");
        ticket.setResolvedAt(LocalDateTime.now());
        supportTicketRepository.save(ticket);
    }

    public void updateTicketStatus(Long id, String status) {
        SupportTicket ticket = getTicketById(id);
        ticket.setStatus(status);
        supportTicketRepository.save(ticket);
    }

    public long getOpenTicketCount() {
        return supportTicketRepository.countByStatus("OPEN");
    }

    public List<SupportTicket> getTicketsByBookingId(Long bookingId) {
        return supportTicketRepository.findByBookingId(bookingId);
    }
}