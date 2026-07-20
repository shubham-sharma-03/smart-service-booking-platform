package com.smart_service_booking.entity;

import com.smart_service_booking.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String serviceType;

    @Column(nullable = false)
    private String provider;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status = BookingStatus.PENDING;

    @Column(unique = true)
    private String token;

    @Column(nullable = false)
    private LocalDateTime bookingDate = LocalDateTime.now();

    private LocalDateTime completedDate;

    private String phoneNumber;

    private String address;

    @Column(length = 500)
    private String notes;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technician;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void generateToken() {
        if (this.token == null) {
            this.token = "TKN" + System.currentTimeMillis();
        }
    }
}