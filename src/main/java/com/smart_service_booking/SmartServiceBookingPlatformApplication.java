package com.smart_service_booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class SmartServiceBookingPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartServiceBookingPlatformApplication.class, args);
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║     🚀 Smart Service Booking Platform Started!             ║");
        System.out.println("║     📍 http://localhost:8080                             ║");
        System.out.println("║     📚 API Docs: http://localhost:8080/swagger-ui.html   ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}