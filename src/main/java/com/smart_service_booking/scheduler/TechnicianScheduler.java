package com.smart_service_booking.scheduler;

import com.smart_service_booking.entity.Technician;
import com.smart_service_booking.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Component
public class TechnicianScheduler {

    @Autowired
    private TechnicianRepository technicianRepository;

    private final Random random = new Random();

    // Update technician locations every 30 seconds (simulation)
    @Scheduled(fixedRate = 30000)
    public void updateTechnicianLocations() {
        List<Technician> technicians = technicianRepository.findByAvailableTrue();

        for (Technician tech : technicians) {
            // Simulate slight movement (New Delhi area ~28.6, 77.2)
            double baseLat = 28.6139;
            double baseLng = 77.2090;

            tech.setCurrentLat(baseLat + (random.nextDouble() - 0.5) * 0.01);
            tech.setCurrentLng(baseLng + (random.nextDouble() - 0.5) * 0.01);
            tech.setLastLocationUpdate(LocalDateTime.now());

            technicianRepository.save(tech);
        }

        System.out.println("Updated " + technicians.size() + " technician locations at " + LocalDateTime.now());
    }

    // Reset daily stats at midnight
    @Scheduled(cron = "0 0 0 * * ?")
    public void resetDailyStats() {
        System.out.println("Running midnight cleanup at " + LocalDateTime.now());
    }

    // Send SMS reminders for pending bookings every hour
    @Scheduled(fixedRate = 3600000)
    public void sendBookingReminders() {
        System.out.println("Checking for booking reminders at " + LocalDateTime.now());
    }
}