package com.smart_service_booking.mapper;

import com.smart_service_booking.dto.BookingResponse;
import com.smart_service_booking.entity.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingResponse toResponse(Booking booking) {
        BookingResponse response = new BookingResponse();
        response.setId(booking.getId());
        response.setUserName(booking.getUserName());
        response.setServiceType(booking.getServiceType());
        response.setProvider(booking.getProvider());
        response.setPhoneNumber(booking.getPhoneNumber());
        response.setAddress(booking.getAddress());
        response.setNotes(booking.getNotes());
        response.setStatus(booking.getStatus());
        response.setBookingDate(booking.getBookingDate());
        response.setCompletedDate(booking.getCompletedDate());

        if (booking.getTechnician() != null) {
            response.setTechnicianName(booking.getTechnician().getName());
        }

        return response;
    }
}