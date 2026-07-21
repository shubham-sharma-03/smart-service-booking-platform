package com.smart_service_booking.service;

import com.smart_service_booking.entity.Booking;
import com.smart_service_booking.entity.SupportTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired(required = false)
    private JavaMailSender mailSender;

    public void sendTicketNotification(SupportTicket ticket) {
        if (mailSender == null) {
            System.out.println("Email not configured. Ticket: " + ticket.getSubject());
            return;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("admin@smartbooking.com");
        message.setSubject("New Support Ticket #" + ticket.getId());
        message.setText("Subject: " + ticket.getSubject() +
                "\nMessage: " + ticket.getMessage() +
                "\nBooking ID: " + ticket.getBookingId());
        mailSender.send(message);
    }

    public void sendBookingConfirmation(Booking booking) {
        if (mailSender == null) return;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("customer@email.com");
        message.setSubject("Booking Confirmed - " + booking.getToken());
        message.setText("Your " + booking.getServiceType() + " service has been booked.");
        mailSender.send(message);
    }
}