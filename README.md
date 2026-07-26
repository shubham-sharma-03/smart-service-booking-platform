🚀 Smart Service Booking Platform

A full-stack web application that allows users to book home services such as electricians, plumbers, carpenters, and more. The platform provides secure authentication, real-time booking management, technician assignment, live location tracking using Google Maps, and notification support.

---

## 📌 Features

- 👤 User Registration & Login
- 🔐 Secure Authentication
- 📅 Service Booking System
- 🎫 Token-Based Queue Management
- 📊 Booking Status Tracking
  - Pending
  - Confirmed
  - Completed
  - Cancelled
- 👨‍🔧 Technician Assignment
- 📍 Live Technician Location Tracking (Google Maps API)
- 📩 Email Notifications
- 📱 Responsive User Interface
- 🛡️ Input Validation & Exception Handling
- ⚡ RESTful API Architecture

---

## 🛠️ Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring MVC
- Spring Data JPA
- Spring Security
- Hibernate
- REST APIs

### Frontend
- HTML5
- CSS3
- JavaScript
- Thymeleaf

### Database
- PostgreSQL

### Build Tool
- Maven

### Tools & IDE
- IntelliJ IDEA
- Postman
- Git
- GitHub

---

## 🏗️ Architecture

```
Client
   │
   ▼
Spring Boot Application
   │
   ├── Controller
   ├── Service
   ├── Repository
   ├── Entity
   ├── DTO
   ├── Security
   └── Configuration
            │
            ▼
      PostgreSQL Database
```

---

## 📂 Project Structure

```
src/main/java/com/smart_service_booking
│
├── audit
│     └── AuditModel.java
│
├── config
│     ├── SecurityConfig.java
│     ├── SwaggerConfig.java
│     └── WebConfig.java
│
├── controller
│     ├── BookingController.java
│     ├── SupportTicketController.java
│     ├── AdminController.java
│     └── PageController.java
│
├── dto
│     ├── ApiResponse.java
│     ├── BookingRequest.java
│     ├── BookingResponse.java
│     ├── SupportTicketRequest.java
│     ├── LoginRequest.java
│     ├── RegisterRequest.java
│     └── ErrorResponse.java
│
├── entity
│     ├── Booking.java
│     ├── SupportTicket.java
│     ├── User.java
│     └── Technician.java
│
├── enums
│     ├── BookingStatus.java
│     └── UserRole.java
│
├── exception
│     ├── GlobalExceptionHandler.java
│     ├── ResourceNotFoundException.java
│     └── BookingException.java
│
├── mapper
│     └── BookingMapper.java
│
├── repository
│     ├── BookingRepository.java
│     ├── SupportTicketRepository.java
│     ├── UserRepository.java
│     └── TechnicianRepository.java
│
├── scheduler
│     └── TechnicianScheduler.java
│
├── service
│     ├── BookingService.java
│     ├── SmsService.java
│     ├── EmailService.java
│     ├── SupportTicketService.java
│     └── DashboardService.java
│
├── util
│     ├── AppConstants.java
│     ├── JwtUtil.java
│     └── ValidationUtil.java
│
└── SmartServiceBookingPlatformApplication.java
```

## ⚙️ Installation

### Clone Repository

```bash
git clone https://github.com/shubham-sharma-03/smart-service-booking-platform.git
```

### Move into Project

```bash
cd Smart-Service-Booking-Platform
```

### Configure PostgreSQL

Update the database credentials inside:

```
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/smart_booking
spring.datasource.username=postgres
spring.datasource.password=your_password
```

### Run Application

```bash
mvn spring-boot:run
```

Application will start on

```
http://localhost:8080
```

---

## 📖 REST API Modules

- Authentication
- User Management
- Service Management
- Booking Management
- Technician Management
- Location Tracking
- Notification Service

---

## 🚀 Future Enhancements

- Payment Gateway Integration
- SMS Notifications
- Rating & Review System
- Admin Dashboard
- AI-Based Technician Recommendation
- Real-Time Chat
- Docker Deployment
- CI/CD Pipeline
- Cloud Deployment (AWS)

---

## 📷 Application Screenshots

### 🏠 Home Page

<img width="1920" height="1080" alt="Screenshot 2026-07-26 121612" src="https://github.com/user-attachments/assets/67e71a76-5188-4c6c-8357-97849ce40d3b" />

---

### 🔐 Login Page

<img width="1920" height="1080" alt="Screenshot 2026-07-26 122144" src="https://github.com/user-attachments/assets/58e3e531-e92c-47dc-81f8-384ede153b83" />
---

### 📊 Dashboard

<img width="1920" height="1080" alt="Screenshot 2026-07-26 122100" src="https://github.com/user-attachments/assets/3af8be03-5a9d-4b65-9df4-0987ce947328" />
---

### 📅 Booking Form

<img width="1920" height="1080" alt="Screenshot 2026-07-26 121952" src="https://github.com/user-attachments/assets/3ba04cd6-a27f-483d-8662-3d2c42a36f0c" />
---

### 📜 Booking History

<img width="1920" height="1080" alt="Screenshot 2026-07-26 122114" src="https://github.com/user-attachments/assets/db2b7532-8503-4d04-9b7a-b25b19d2393b" />
---

### 📍 Technician Tracking

<img width="1920" height="1080" alt="Screenshot 2026-07-26 122039" src="https://github.com/user-attachments/assets/c41f37b3-001a-45b9-8c9d-da4111d23cf4" />
---

## 🤝 Contributing

Contributions are welcome.

1. Fork the repository
2. Create your feature branch

```bash
git checkout -b feature-name
```

3. Commit your changes

```bash
git commit -m "Added new feature"
```

4. Push to GitHub

```bash
git push origin feature-name
```

5. Open a Pull Request

---

## 👨‍💻 Author

**Shubham Sharma**

- GitHub: https://github.com/shubham-sharma-03
- LinkedIn: https://www.linkedin.com/in/shubham-sharma2004/

---

---
## 🌐 Live Demo

https://smart-service-booking-platform-ee5n.onrender.com
---


## ⭐ Support

If you found this project helpful, please consider giving it a ⭐ on GitHub.

---

## 📄 License

This project is licensed under the MIT License.
