package com.smart_service_booking.repository;

import com.smart_service_booking.entity.Booking;
import com.smart_service_booking.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByStatus(BookingStatus status);

    List<Booking> findByUserNameContainingIgnoreCase(String userName);

    List<Booking> findByServiceTypeContainingIgnoreCase(String serviceType);

    @Query("SELECT b FROM Booking b ORDER BY b.bookingDate DESC")
    List<Booking> findRecentBookings();

    long countByStatus(BookingStatus status);

    @Query("SELECT b.serviceType, COUNT(b) FROM Booking b GROUP BY b.serviceType")
    List<Object[]> countByServiceType();

    @Query("SELECT DATE(b.bookingDate), COUNT(b) FROM Booking b WHERE b.bookingDate >= CURRENT_DATE - 7 GROUP BY DATE(b.bookingDate)")
    List<Object[]> getWeeklyStats();
}