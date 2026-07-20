package com.smart_service_booking.mapper;

import com.smart_service_booking.dto.BookingResponse;
import com.smart_service_booking.entity.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingResponse toResponse(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .userName(booking.getUserName())
                .serviceType(booking.getServiceType())
                .provider(booking.getProvider())
                .status(booking.getStatus())
                .token(booking.getToken())
                .bookingDate(booking.getBookingDate())
                .completedDate(booking.getCompletedDate())
                .phoneNumber(booking.getPhoneNumber())
                .address(booking.getAddress())
                .technicianName(booking.getTechnician() != null ? booking.getTechnician().getName() : null)
                .technicianLat(booking.getTechnician() != null ? booking.getTechnician().getCurrentLat() : null)
                .technicianLng(booking.getTechnician() != null ? booking.getTechnician().getCurrentLng() : null)
                .build();
    }
}