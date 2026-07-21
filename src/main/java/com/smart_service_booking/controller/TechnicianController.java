package com.smart_service_booking.controller;

import com.smart_service_booking.entity.Technician;
import com.smart_service_booking.repository.TechnicianRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TechnicianController {

    private final TechnicianRepository technicianRepository;

    public TechnicianController(TechnicianRepository technicianRepository) {
        this.technicianRepository = technicianRepository;
    }

    @GetMapping("/technicians")
    public ResponseEntity<List<Technician>> getAllTechnicians() {
        return ResponseEntity.ok(technicianRepository.findAll());
    }

    @GetMapping("/technicians/count")
    public ResponseEntity<Long> getTechnicianCount() {
        return ResponseEntity.ok(technicianRepository.count());
    }
}