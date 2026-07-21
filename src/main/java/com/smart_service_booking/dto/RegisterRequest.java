package com.smart_service_booking.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;        // matches frontend: name
    private String email;       // matches frontend: email
    private String phone;       // matches frontend: phone
    private String password;    // matches frontend: password
}