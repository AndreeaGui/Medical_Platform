package com.example.springdemo.services;

import com.example.springdemo.dto.MedicationPerPlanDTO;
import com.example.springdemo.dto.MedicationPlanDTO;
import com.example.springdemo.dto.builders.MedicationPlanMapper;
import com.example.springdemo.entities.*;
import com.example.springdemo.exception.MedicationPlanException;
import com.example.springdemo.exception.UserException;
import com.example.springdemo.repositories.*;
import com.example.springdemo.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.springdemo.exception.ExceptionMessage.*;

@Service
@RequiredArgsConstructor
public class MedicationPlanService {

    private final PatientRepository patientRepository;
    private final MedicationPlanRepository medicationPlanRepository;
    private final MedicationRepository medicationRepository;
    private final MedicationPerPlanRepository medicationPerPlanRepository;
    private final SecurityUtil securityUtil;
    private final UserRepository userRepository;

    public MedicationPlanDTO addMedicationPlan(MedicationPlanDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new MedicationPlanException(PATIENT_NOT_FOUND));
        MedicationPlan medicationPlan = medicationPlanRepository
                .save(MedicationPlanMapper.generateMedicationPlanFromDTO(dto, patient));
        return MedicationPlanMapper.generateMedicationPlanDTOFromEntity(medicationPlan);
    }

    public MedicationPerPlanDTO addMedicationPerPlan(MedicationPerPlanDTO dto) {
        MedicationPlan medicationPlan = medicationPlanRepository.findById(dto.getMedicationPlanId())
                .orElseThrow(() -> new MedicationPlanException(MEDICATION_PLAN_NOT_FOUND));
        Medication medication = medicationRepository.findById(dto.getMedicationId())
                .orElseThrow(() -> new MedicationPlanException(MEDICATION_NOT_FOUND));
        MedicationPerPlan medicationPerPlan = medicationPerPlanRepository
                .save(MedicationPlanMapper.generateMedicationPerPlanFromDTO(dto, medicationPlan, medication));
        return MedicationPlanMapper.generateMedicationPerPlanDTOFromEntity(medicationPerPlan);
    }

    public List<MedicationPlanDTO> getAllMedicaitonPlans() {
        List<MedicationPlan> entities = medicationPlanRepository.findAll();
        List<MedicationPlanDTO> dtos = new ArrayList<>();
        for (MedicationPlan iterator : entities) {
            dtos.add(MedicationPlanMapper.generateMedicationPlanDTOFromEntity(iterator));
        }
        return dtos;
    }

    public List<MedicationPlanDTO> getAllMedicationPlansPerPatient(UUID patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new MedicationPlanException(PATIENT_NOT_FOUND));
        List<MedicationPlan> entities = medicationPlanRepository.findAllByPatient(patient);
        List<MedicationPlanDTO> dtos = new ArrayList<>();
        for (MedicationPlan iterator : entities) {
            dtos.add(MedicationPlanMapper.generateMedicationPlanDTOFromEntity(iterator));
        }
        return dtos;
    }

    public List<MedicationPerPlanDTO> getMediacationPlanPerLoggedPatient() {
        String username = securityUtil.getSecurityLoggedUserUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserException(PATIENT_NOT_FOUND));
        Patient patient = patientRepository.findById(user.getId())
                .orElseThrow(() -> new UserException(PATIENT_NOT_FOUND));
        List<MedicationPlan> medicationPlans = medicationPlanRepository.findAllByPatient(patient);
        List<MedicationPerPlan> medicationPerPlans = new ArrayList<>();
        for (MedicationPlan iterator : medicationPlans) {
            List<MedicationPerPlan> iteratorList = medicationPerPlanRepository.findAllByPlan(iterator);
            for (MedicationPerPlan mpp : iteratorList) {
                medicationPerPlans.add(mpp);
            }
        }
        List<MedicationPerPlanDTO> dtos = new ArrayList<>();
        for (MedicationPerPlan iter : medicationPerPlans) {
            dtos.add(MedicationPlanMapper.generateMedicationPerPlanDTOFromEntity(iter));
        }
        return dtos;
    }

    /*public List<MedicationDTO> getAllMedications(){
        List<Medication> entities = medicationRepository.findAll();
        List<MedicationDTO> dtos = new ArrayList<>();
        for(Medication iterator: entities){
            dtos.add(MedicationMapper.generateMEdicationDTOFromMedication(iterator));
        }
        return dtos;
    }*/


}
