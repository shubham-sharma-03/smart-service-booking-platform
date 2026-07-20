package com.smart_service_booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "technicians")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Technician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String specialization; // Electrician, Plumber, etc.

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    private String email;

    private Double rating = 0.0;

    private Integer totalJobs = 0;

    private Integer completedJobs = 0;

    private boolean available = true;

    private Double currentLat;

    private Double currentLng;

    private LocalDateTime lastLocationUpdate;

    @Column(nullable = false)
    private LocalDateTime joinedAt = LocalDateTime.now();
}