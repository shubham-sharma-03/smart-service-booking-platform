package com.smart_service_booking.util;

public class AppConstants {

    public static final String APP_NAME = "Smart Service Booking";
    public static final String APP_VERSION = "1.0.0";
    public static final String APP_BASE_URL = "http://localhost:8080";

    // SMS Configuration
    public static final String SMS_API_URL = "https://api.twilio.com/2010-04-01/Accounts/{AccountSid}/Messages.json";
    public static final String SMS_FROM_NUMBER = "+1234567890";

    // JWT
    public static final long JWT_EXPIRATION = 86400000; // 24 hours
    public static final String JWT_SECRET = "your-256-bit-secret-key-here-make-it-very-long-and-secure";

    // Pagination
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int MAX_PAGE_SIZE = 100;

    private AppConstants() {}
}