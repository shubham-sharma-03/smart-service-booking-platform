package com.smart_service_booking.service;

import com.smart_service_booking.dto.BookingRequest;
import com.smart_service_booking.dto.BookingResponse;
import com.smart_service_booking.entity.Booking;
import com.smart_service_booking.entity.Technician;
import com.smart_service_booking.enums.BookingStatus;
import com.smart_service_booking.exception.ResourceNotFoundException;
import com.smart_service_booking.mapper.BookingMapper;
import com.smart_service_booking.repository.BookingRepository;
import com.smart_service_booking.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TechnicianRepository technicianRepository;

    @Autowired
    private SmsService smsService;

    @Autowired
    private BookingMapper bookingMapper;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getRecentBookings(int limit) {
        return bookingRepository.findRecentBookings().stream()
                .limit(limit)
                .collect(Collectors.toList());
    }

    public Booking createBooking(BookingRequest request) {
        Booking booking = new Booking();
        booking.setUserName(request.getUserName());
        booking.setServiceType(request.getServiceType());
        booking.setProvider(request.getProvider());
        booking.setPhoneNumber(request.getPhoneNumber());
        booking.setAddress(request.getAddress());
        booking.setNotes(request.getNotes());
        booking.setStatus(BookingStatus.PENDING);

        if (request.getTechnicianId() != null) {
            Technician tech = technicianRepository.findById(request.getTechnicianId())
                    .orElseThrow(() -> new ResourceNotFoundException("Technician not found"));
            booking.setTechnician(tech);
        }

        Booking saved = bookingRepository.save(booking);

        // Send SMS notification
        if (saved.getPhoneNumber() != null) {
            smsService.sendBookingConfirmation(saved);
        }

        return saved;
    }

    public void confirmBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found: " + id));
        booking.setStatus(BookingStatus.CONFIRMED);
        bookingRepository.save(booking);
    }

    public void completeBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found: " + id));
        booking.setStatus(BookingStatus.COMPLETED);
        booking.setCompletedDate(LocalDateTime.now());
        bookingRepository.save(booking);
    }

    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found: " + id));
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public BookingResponse getBookingResponse(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found: " + id));
        return bookingMapper.toResponse(booking);
    }

    public long getTotalCount() {
        return bookingRepository.count();
    }

    public long getPendingCount() {
        return bookingRepository.countByStatus(BookingStatus.PENDING);
    }

    public long getCompletedCount() {
        return bookingRepository.countByStatus(BookingStatus.COMPLETED);
    }

    public List<Booking> getBookingsByStatus(BookingStatus status) {
        return bookingRepository.findByStatus(status);
    }
}