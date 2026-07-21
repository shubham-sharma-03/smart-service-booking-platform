package com.smart_service_booking.repository;

import com.smart_service_booking.entity.Booking;
import com.smart_service_booking.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    long countByStatus(BookingStatus status);

    List<Booking> findByStatus(BookingStatus status);

    @Query("SELECT b FROM Booking b ORDER BY b.bookingDate DESC")
    List<Booking> findRecentBookings();
}