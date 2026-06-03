package com.smart_service_booking.repository;


import com.smart_service_booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    int countByStatus(String pending);
}
