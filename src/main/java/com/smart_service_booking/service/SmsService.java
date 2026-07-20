package com.smart_service_booking.service;

import com.smart_service_booking.entity.Booking;
import com.smart_service_booking.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SmsService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendBookingConfirmation(Booking booking) {
        String message = String.format(
                "Hi %s, your %s booking is confirmed! Token: %s. Track at: %s/technician/%d",
                booking.getUserName(),
                booking.getServiceType(),
                booking.getToken(),
                AppConstants.APP_BASE_URL,
                booking.getId()
        );

        sendSms(booking.getPhoneNumber(), message);
    }

    public void sendTechnicianArrivalNotification(Booking booking, String eta) {
        String message = String.format(
                "Hi %s, %s is arriving in %s. Track live: %s/technician/%d",
                booking.getUserName(),
                booking.getProvider(),
                eta,
                AppConstants.APP_BASE_URL,
                booking.getId()
        );

        sendSms(booking.getPhoneNumber(), message);
    }

    private void sendSms(String phoneNumber, String message) {
        // Integrate with Twilio, Fast2SMS, or MSG91
        // Example Twilio integration:
        try {
            Map<String, String> payload = new HashMap<>();
            payload.put("to", phoneNumber);
            payload.put("body", message);

            // Uncomment when you have SMS provider configured
            // ResponseEntity<String> response = restTemplate.postForEntity(
            //     AppConstants.SMS_API_URL, payload, String.class
            // );

            System.out.println("SMS would be sent to " + phoneNumber + ": " + message);
        } catch (Exception e) {
            System.err.println("Failed to send SMS: " + e.getMessage());
        }
    }
}