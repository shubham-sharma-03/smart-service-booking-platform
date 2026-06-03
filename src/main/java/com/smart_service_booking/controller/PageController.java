package com.smart_service_booking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/")
    public String bookingPage() {
        return "booking";
    }

    // Technician details page
    @GetMapping("/technician/{id}")
    public String technicianPage(@PathVariable Long id, Model model) {
        model.addAttribute("bookingId", id);
        return "technician";
    }
}
