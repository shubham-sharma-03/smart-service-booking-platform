package com.smart_service_booking.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;       // matches frontend: email (not username)
    private String password;    // matches frontend: password
}