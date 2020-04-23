package com.assignment4.producer.repositories;


import com.assignment4.producer.entities.Caregiver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CaregiverRepository extends JpaRepository<Caregiver, UUID> {

    //Page<Caregiver> findAll(Pageable pageable);

    List<Caregiver> findAll();
}
