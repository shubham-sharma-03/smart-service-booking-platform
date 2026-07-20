package com.smart_service_booking.dto;

import lombok.Data;

@Data
public class SupportTicketRequest {
    private Long bookingId;
    private String issue;
    private String status;
}