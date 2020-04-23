package com.example.springdemo.services;

import com.example.springdemo.dto.PatientDTO;
import com.example.springdemo.dto.builders.PatientMapper;
import com.example.springdemo.entities.Caregiver;
import com.example.springdemo.entities.Patient;
import com.example.springdemo.entities.User;
import com.example.springdemo.entities.enumeration.Role;
import com.example.springdemo.exception.ExceptionMessage;
import com.example.springdemo.exception.UserException;
import com.example.springdemo.repositories.CaregiverRepository;
import com.example.springdemo.repositories.PatientRepository;
import com.example.springdemo.repositories.UserRepository;
import com.example.springdemo.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.springdemo.exception.ExceptionMessage.CAREGIVER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CaregiverService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;

    public List<PatientDTO> findPatientsByLoggedCaregiver(){
        String username = securityUtil.getSecurityLoggedUserUsername();
        System.out.println(username);
        User user = userRepository.findByUsername(username).orElseThrow(()->new UserException(CAREGIVER_NOT_FOUND));
        List<Patient> patients = patientRepository.findByCaregiver((Caregiver)user);
        List<PatientDTO> dtos = new ArrayList<>();
        for(Patient patient: patients){
            dtos.add(PatientMapper.generatePatientDTOFromPatient(patient));
        }
        return dtos;
    }

}
