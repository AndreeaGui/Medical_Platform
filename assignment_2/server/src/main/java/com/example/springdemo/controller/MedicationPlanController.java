package com.example.springdemo.controller;

import com.example.springdemo.dto.MedicationPerPlanDTO;
import com.example.springdemo.dto.MedicationPlanDTO;
import com.example.springdemo.services.MedicationPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/medicationPlan")
@RequiredArgsConstructor
public class MedicationPlanController {

    private final MedicationPlanService medicationPlanService;

    @PreAuthorize("hasAuthority('DOCTOR')")
    @PostMapping(value="/addMedicationPlan")
    public MedicationPlanDTO addMedicationPlan(@RequestBody MedicationPlanDTO medicationPlanDTO){
        return medicationPlanService.addMedicationPlan(medicationPlanDTO);
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @PostMapping(value="/addMedicationPerPlan")
    public MedicationPerPlanDTO addMedicationPerPlan(@RequestBody MedicationPerPlanDTO medicationPerPlanDTO){
        return medicationPlanService.addMedicationPerPlan(medicationPerPlanDTO);
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @GetMapping(value="/getMedicationPlans")
    public List<MedicationPlanDTO> getAllMedicationPlans(){
        return medicationPlanService.getAllMedicaitonPlans();
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @GetMapping(value="/patientMedicationPlan")
    public List<MedicationPerPlanDTO> getPatientMedicationPlan(){
        return medicationPlanService.getMediacationPlanPerLoggedPatient();
    }
}
