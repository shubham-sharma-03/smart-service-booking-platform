package com.smart_service_booking.entity;

import com.smart_service_booking.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "support_tickets")
@Data
public class SupportTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingId;

    private String subject;

    @Column(length = 1000)
    private String message;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // <-- THIS WAS MISSING

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) status = TicketStatus.OPEN;
    }
}