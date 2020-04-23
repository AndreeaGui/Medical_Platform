package com.example.springdemo.controller;

import com.example.springdemo.dto.PatientDTO;
import com.example.springdemo.services.CaregiverService;
import com.example.springdemo.services.DoctorService;
import com.example.springdemo.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/caregiver")
@RequiredArgsConstructor
public class CaregiverController {

    private final CaregiverService caregiverService;

    @PreAuthorize("hasAuthority('CAREGIVER')")
    @GetMapping(value="/patients")
    public List<PatientDTO> findAllPatientsPerCaregiver(){

        return caregiverService.findPatientsByLoggedCaregiver();
    }
}
