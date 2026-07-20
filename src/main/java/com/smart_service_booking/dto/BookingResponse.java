package com.smart_service_booking.dto;

import com.smart_service_booking.enums.BookingStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BookingResponse {
    private Long id;
    private String userName;
    private String serviceType;
    private String provider;
    private BookingStatus status;
    private String token;
    private LocalDateTime bookingDate;
    private LocalDateTime completedDate;
    private String phoneNumber;
    private String address;
    private String technicianName;
    private Double technicianLat;
    private Double technicianLng;
}