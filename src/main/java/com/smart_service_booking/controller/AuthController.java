package com.smart_service_booking.controller;

import com.smart_service_booking.dto.RegisterRequest;
import com.smart_service_booking.dto.LoginRequest;
import com.smart_service_booking.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        // TODO: Add actual registration logic
        return ResponseEntity.ok(Map.of("message", "Registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // TODO: Add actual login logic with JWT
        Map<String, Object> user = new HashMap<>();
        user.put("email", request.getEmail());
        user.put("name", "User"); // Get from DB later

        Map<String, Object> response = new HashMap<>();
        response.put("token", "dummy-jwt-token-for-testing");
        response.put("user", user);

        return ResponseEntity.ok(response);
    }
}