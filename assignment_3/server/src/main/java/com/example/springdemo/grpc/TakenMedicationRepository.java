package com.example.springdemo.grpc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TakenMedicationRepository extends JpaRepository<TakenMedication, UUID> {

}
