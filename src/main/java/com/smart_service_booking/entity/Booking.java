package com.smart_service_booking.entity;

import com.smart_service_booking.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String serviceType;
    private String provider;
    private String phoneNumber;
    private String address;
    private String notes;

    // NEW: snapshot fields for the randomly-assigned technician shown on the frontend
    private String assignedTechName;
    private String assignedTechPhone;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private LocalDateTime bookingDate;
    private LocalDateTime completedDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technician;

    private String token;

    @PrePersist
    protected void onCreate() {
        bookingDate = LocalDateTime.now();
        if (status == null) status = BookingStatus.PENDING;
        if (token == null) token = java.util.UUID.randomUUID().toString();
    }
}