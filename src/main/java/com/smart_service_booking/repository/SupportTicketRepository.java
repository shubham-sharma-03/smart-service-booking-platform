package com.smart_service_booking.repository;

import com.smart_service_booking.entity.SupportTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportTicketRepository extends JpaRepository<SupportTicket, Long> {

    // Fix: Add these methods
    long countByStatus(String status);

    List<SupportTicket> findByStatus(String status);

    List<SupportTicket> findByBookingId(Long bookingId);
}