package com.example.springdemo.controller;

import com.example.springdemo.dto.MedicationDTO;
import com.example.springdemo.dto.PatientDTO;
import com.example.springdemo.dto.UserDTO;
import com.example.springdemo.services.DoctorService;
import com.example.springdemo.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    private final SecurityUtil securityUtil;

    @Autowired
    public DoctorController(DoctorService doctorService, SecurityUtil securityUtil) {
        this.doctorService = doctorService;
        this.securityUtil = securityUtil;
    }


    @PreAuthorize("hasAuthority('DOCTOR')")
    @GetMapping(value="/caregivers")
    public List<UserDTO> findAllCaregivers(){

        System.out.println(securityUtil.getSecurityLoggedUserUsername());
        return doctorService.findAllCaregivers();
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @PostMapping(value="/caregivers/new")
    public UserDTO addCaregiver(@RequestBody UserDTO userDTO){
        return doctorService.addCaregiver(userDTO);
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @PostMapping(value="/caregivers/{id}")
    public UserDTO editCaregiver(@RequestBody UserDTO userDTO, @PathVariable("id") UUID id){
        return doctorService.editCaregiver(id, userDTO);
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @DeleteMapping(value = "/caregivers/{id}")
    public void deleteCaregiver(@PathVariable("id") UUID id ){
        doctorService.deleteCaregiver(id);
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @GetMapping(value="/patients")
    public List<PatientDTO> findAllPatients(){

        SecurityUtil securityUtil = new SecurityUtil();
        System.out.println(securityUtil.getSecurityLoggedUserUsername());
        System.out.println(securityUtil.getSecurityLoggedUserAuthorities());
        return doctorService.findAllPatients();
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @PostMapping(value="/patients/new")
    public PatientDTO addPatient(@RequestBody PatientDTO patientDTO){
        return doctorService.addPatient(patientDTO);
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @PostMapping(value="/patients/{id}")
    public PatientDTO editPatient(@PathVariable("id") UUID id, @RequestBody PatientDTO patientDTO){
        return doctorService.editPatient(id, patientDTO);
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @DeleteMapping(value = "/patients/{id}")
    public void deletePatient(@PathVariable("id") UUID id ){
        doctorService.deletePatient(id);
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @GetMapping(value="/medications")
    public List<MedicationDTO> findAllMedications(){
        return doctorService.findAllMedications();
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @PostMapping(value="/medications/new")
    public MedicationDTO addMedication(@RequestBody MedicationDTO medicationDTO){
        return doctorService.addMedication(medicationDTO);
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @PostMapping(value="/medications/{id}")
    public MedicationDTO editMedication(@PathVariable("id") UUID id, @RequestBody MedicationDTO medicationDTO){
        return doctorService.editMedication(id, medicationDTO);
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @DeleteMapping(value="/medications/{id}")
    public void deleteMedication(@PathVariable("id") UUID id){
        doctorService.deleteMedication(id);
    }

    @GetMapping()
    public String getUser(){
        return securityUtil.getSecurityLoggedUserUsername();
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @GetMapping(value="/doctors")
    public List<UserDTO> findAllDoctors(){
        return doctorService.findAllDoctors();
    }

}
