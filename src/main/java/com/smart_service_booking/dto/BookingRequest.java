package com.smart_service_booking.dto;

import lombok.Data;

@Data
public class BookingRequest {
    private String userName;
    private String serviceType;
    private String provider;
    private String phoneNumber;
    private String address;
    private String notes;
    private Long technicianId;

    // NEW
    private String technicianName;
    private String technicianPhone;
}