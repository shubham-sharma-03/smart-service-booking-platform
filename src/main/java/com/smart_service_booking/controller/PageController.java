package com.smart_service_booking.controller;

import com.smart_service_booking.entity.Booking;
import com.smart_service_booking.entity.SupportTicket;
import com.smart_service_booking.service.BookingService;
import com.smart_service_booking.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("recentBookings", bookingService.getRecentBookings(5));
        model.addAttribute("totalBookings", bookingService.getTotalCount());
        model.addAttribute("pendingBookings", bookingService.getPendingCount());
        model.addAttribute("completedBookings", bookingService.getCompletedCount());
        model.addAttribute("booking", new Booking());
        model.addAttribute("supportTicket", new SupportTicket());
        model.addAttribute("dashboardStats", dashboardService.getDashboardStats());
        return "index";
    }

    @GetMapping("/technician/{id}")
    public String trackTechnician(@PathVariable Long id, Model model) {
        model.addAttribute("bookingId", id);
        model.addAttribute("booking", bookingService.getBookingResponse(id));
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}