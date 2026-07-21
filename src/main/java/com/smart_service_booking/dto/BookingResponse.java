package com.smart_service_booking.dto;

import com.smart_service_booking.enums.BookingStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingResponse {
    private Long id;
    private String userName;
    private String serviceType;
    private String provider;
    private String phoneNumber;
    private String address;
    private String notes;
    private BookingStatus status;
    private LocalDateTime bookingDate;
    private LocalDateTime completedDate;
    private String technicianName;
}