package com.assignment4.producer.repositories;

import com.assignment4.producer.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    Optional<Doctor> findByUsernameAndPassword(String username, String password);
}
