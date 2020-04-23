package com.example.springdemo.services;

import com.example.springdemo.dto.MedicationDTO;
import com.example.springdemo.dto.PatientDTO;
import com.example.springdemo.dto.UserDTO;
import com.example.springdemo.dto.builders.MedicationMapper;
import com.example.springdemo.dto.builders.PatientMapper;
import com.example.springdemo.dto.builders.UserMapper;
import com.example.springdemo.entities.Caregiver;
import com.example.springdemo.entities.Doctor;
import com.example.springdemo.entities.Medication;
import com.example.springdemo.entities.Patient;
import com.example.springdemo.exception.ExceptionMessage;
import com.example.springdemo.exception.MedicationExceeption;
import com.example.springdemo.exception.UserException;
import com.example.springdemo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.springdemo.exception.ExceptionMessage.*;

@Service
public class DoctorService {

    private final CaregiverRepository caregiverRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final MedicationRepository medicationRepository;

    @Autowired
    public DoctorService(CaregiverRepository caregiverRepository, PatientRepository patientRepository,
                         DoctorRepository doctorRepository, UserRepository userRepository, MedicationRepository medicationRepository) {
        this.caregiverRepository = caregiverRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.medicationRepository = medicationRepository;
    }

    /*
    CRUD on caregiver
     */
    public UserDTO addCaregiver(UserDTO userDTO) {
        Caregiver user = caregiverRepository.save(UserMapper.generateCaregiverFromUserDTO(userDTO));
        return UserMapper.generateUserDTOFromUser(user);
    }

    public UserDTO editCaregiver(UUID id, UserDTO userDTO) {
        Caregiver caregiver = caregiverRepository.findById(id)
                .orElseThrow(() -> new UserException(ExceptionMessage.CAREGIVER_NOT_FOUND));
        if (userDTO.getUsername() != null)
            caregiver.setUsername(userDTO.getUsername());
        if (userDTO.getPassword() != null)
            caregiver.setPassword(userDTO.getPassword());
        if (userDTO.getName() != null)
            caregiver.setName(userDTO.getName());
        if (userDTO.getBirthDate() != null)
            caregiver.setBirthDate(userDTO.getBirthDate());
        if (userDTO.getGender() != null)
            caregiver.setGender(userDTO.getGender());
        return UserMapper.generateUserDTOFromUser(caregiverRepository.save(caregiver));
    }

    public void deleteCaregiver(UUID id) {
        Caregiver caregiver = caregiverRepository.findById(id)
                .orElseThrow(() -> new UserException(ExceptionMessage.CAREGIVER_NOT_FOUND));
        caregiverRepository.delete(caregiver);
    }

    /*public Page<UserDTO> getAllCaregivers(Pageable pageable) {
        return caregiverRepository.findAll(pageable).map(UserMapper::generateUserDTOFromUser);
    }*/

    public List<UserDTO> findAllCaregivers() {
        List<Caregiver> caregivers = caregiverRepository.findAll();
        List<UserDTO> dtos = new ArrayList<>();
        for (Caregiver caregiver : caregivers) {
            dtos.add(UserMapper.generateUserDTOFromUser(caregiver));
        }
        return dtos;
    }

    public PatientDTO addPatient(PatientDTO patient) {
        Doctor doctor = doctorRepository.findById(patient.getDoctorId())
                .orElseThrow(() -> new UserException(DOCTOR_NOT_FOUND));
        Caregiver caregiver = caregiverRepository.findById(patient.getCaregiverId())
                .orElseThrow(() -> new UserException(DOCTOR_NOT_FOUND));
        Patient newPatient = patientRepository.save(PatientMapper.generatePatientFromPatientDTO(patient, doctor, caregiver));
        return PatientMapper.generatePatientDTOFromPatient(newPatient);
    }

    public void deletePatient(UUID id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new UserException(PATIENT_NOT_FOUND));
        patientRepository.delete(patient);
    }

    public List<PatientDTO> findAllPatientsByDoctor(UUID doctorId) {
        List<Patient> patients = patientRepository.findAllByDoctor(doctorId);
        List<PatientDTO> dtos = new ArrayList<>();
        for (Patient patient : patients) {
            dtos.add(PatientMapper.generatePatientDTOFromPatient(patient));
        }
        return dtos;
    }

    public List<PatientDTO> findAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        List<PatientDTO> dtos = new ArrayList<>();
        for (Patient patient : patients) {
            dtos.add(PatientMapper.generatePatientDTOFromPatient(patient));
        }
        return dtos;
    }

    public PatientDTO editPatient(UUID id, PatientDTO userDTO) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new UserException(PATIENT_NOT_FOUND));
        if (userDTO.getUsername() != null)
            patient.setUsername(userDTO.getUsername());
        if (userDTO.getPassword() != null)
            patient.setPassword(userDTO.getPassword());
        if (userDTO.getName() != null)
            patient.setName(userDTO.getName());
        if (userDTO.getBirthDate() != null)
            patient.setBirthDate(userDTO.getBirthDate());
        if (userDTO.getGender() != null)
            patient.setGender(userDTO.getGender());
        if (userDTO.getMedicalRecord() != null)
            patient.setMedicalRecord(userDTO.getMedicalRecord());
        Caregiver caregiver = caregiverRepository.findById(userDTO.getCaregiverId())
                .orElseThrow(() -> new UserException(CAREGIVER_NOT_FOUND));
        if (caregiver.getId() != patient.getCaregiver().getId())
            patient.setCaregiver(caregiver);
        return PatientMapper.generatePatientDTOFromPatient(patientRepository.save(patient));
    }

    //CRUD on medication
    public List<MedicationDTO> findAllMedications() {
        List<Medication> medications = medicationRepository.findAll();
        List<MedicationDTO> dtos = new ArrayList<>();
        for (Medication medication : medications) {
            dtos.add(MedicationMapper.generateMEdicationDTOFromMedication(medication));
        }
        return dtos;
    }

    public MedicationDTO addMedication(MedicationDTO medicationDTO) {
        Medication medication = medicationRepository.save(MedicationMapper.generateMedicationFromMedicationDTO(medicationDTO));
        return MedicationMapper.generateMEdicationDTOFromMedication(medication);
    }

    public MedicationDTO editMedication(UUID id, MedicationDTO medicationDTO) {
        Medication medication = medicationRepository.findById(id)
                .orElseThrow(() -> new MedicationExceeption(MEDICATION_NOT_FOUND));
        if (medicationDTO.getName() != null)
            medication.setName(medicationDTO.getName());
        if (medicationDTO.getSideEffect() != null)
            medication.setSideEffect(medicationDTO.getSideEffect());
        return MedicationMapper.generateMEdicationDTOFromMedication(medicationRepository.save(medication));
    }

    public void deleteMedication(UUID id) {
        Medication medication = medicationRepository.findById(id)
                .orElseThrow(() -> new MedicationExceeption(MEDICATION_NOT_FOUND));
        medicationRepository.delete(medication);
    }

    public List<UserDTO> findAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<UserDTO> dtos = new ArrayList<>();
        for (Doctor doctor : doctors) {
            dtos.add(UserMapper.generateUserDTOFromUser(doctor));
        }
        return dtos;
    }
}
