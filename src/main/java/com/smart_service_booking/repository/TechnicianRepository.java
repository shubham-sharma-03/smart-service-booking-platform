package com.smart_service_booking.repository;

import com.smart_service_booking.entity.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Long> {

    List<Technician> findByAvailableTrue();

    List<Technician> findBySpecializationAndAvailableTrue(String specialization);

    Optional<Technician> findByPhoneNumber(String phoneNumber);
}