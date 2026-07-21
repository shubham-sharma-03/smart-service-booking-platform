package com.smart_service_booking.service;

import com.smart_service_booking.entity.Booking;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    public void sendBookingConfirmation(Booking booking) {
        // TODO: Implement actual SMS sending
        System.out.println("SMS sent to: " + booking.getPhoneNumber());
    }
}