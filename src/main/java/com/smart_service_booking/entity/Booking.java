package com.smart_service_booking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String serviceType;
    private String providerName;
    private String status;
    private Integer tokenNumber;
    private String phoneNumber;

    private Double technicianLat;
    private Double technicianLng;

    // ===== GETTERS =====
    public Long getId() { return id; }
    public String getUserName() { return userName; }
    public String getServiceType() { return serviceType; }
    public String getProviderName() { return providerName; }
    public String getStatus() { return status; }
    public Integer getTokenNumber() { return tokenNumber; }
    public String getPhoneNumber() { return phoneNumber; }
    public Double getTechnicianLat() { return technicianLat; }
    public Double getTechnicianLng() { return technicianLng; }

    // ===== SETTERS =====
    public void setUserName(String userName) { this.userName = userName; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }
    public void setProviderName(String providerName) { this.providerName = providerName; }
    public void setStatus(String status) { this.status = status; }
    public void setTokenNumber(Integer tokenNumber) { this.tokenNumber = tokenNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setTechnicianLat(Double technicianLat) { this.technicianLat = technicianLat; }
    public void setTechnicianLng(Double technicianLng) { this.technicianLng = technicianLng; }
}
