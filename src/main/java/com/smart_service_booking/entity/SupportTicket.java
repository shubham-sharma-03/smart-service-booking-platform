package com.smart_service_booking.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "support_tickets")
public class SupportTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingId;

    private String issue;

    private String status; // OPEN or RESOLVED

    // constructors
    public SupportTicket() {}

    public SupportTicket(Long bookingId, String issue, String status) {
        this.bookingId = bookingId;
        this.issue = issue;
        this.status = status;
    }

    // getters & setters
    public Long getId() {
        return id;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
