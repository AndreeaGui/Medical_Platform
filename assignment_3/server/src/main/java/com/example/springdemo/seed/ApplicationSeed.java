package com.example.springdemo.seed;

import com.example.springdemo.entities.*;
import com.example.springdemo.entities.enumeration.Gender;
import com.example.springdemo.entities.enumeration.Role;
import com.example.springdemo.rabbitMQ.Activity;
import com.example.springdemo.rabbitMQ.ActivityRepository;
import com.example.springdemo.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApplicationSeed implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CaregiverRepository caregiverRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final MedicationRepository medicationRepository;
    private final MedicationPlanRepository medicationPlanRepository;
    private final MedicationPerPlanRepository medicationPerPlanRepository;
    private static final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(10);

    private final ActivityRepository activityRepository;


    @Override
    public void run(String... args) throws Exception {
        User user = userRepository.save(User.builder()
                .username("a@g.com")
                .password(bcrypt.encode("000"))
                .name("Andreea")
                .birthDate(LocalDate.now())
                .role(Role.CAREGIVER)
                .gender(Gender.FEMALE)
                .build()
        );
        System.out.println(user.getName() + " " + user.getId() + " " + user.getClass());

        Caregiver caregiver = caregiverRepository.save(Caregiver.caregiverFromUserBuilder()
                .user(user)
                .build()
        );
        System.out.println(caregiver.getName() + " " + caregiver.getId() + " " + caregiver.getPassword()+ " "+ caregiver.getClass());

        user.setName("Caregiver Unu");
        user.setUsername("caregiver1");
        user.setPassword(bcrypt.encode("000"));
        Caregiver caregiver1 = caregiverRepository.save(Caregiver.caregiverFromUserBuilder()
                .user(user)
                .build());
        System.out.println(caregiver1.getName() + " " + caregiver1.getId() + " " + caregiver1.getPassword()+ " "+ caregiver1.getClass());


        user.setName("Caregiver Doi");
        user.setUsername("caregiver2");
        user.setPassword(bcrypt.encode("000"));
        Caregiver caregiver2 = caregiverRepository.save(Caregiver.caregiverFromUserBuilder()
                .user(user)
                .build());
        System.out.println(caregiver2.getName() + " " + caregiver2.getId()+ " " + caregiver2.getPassword()+ " " + caregiver2.getClass());

        System.out.println("\n\n\n");
        //caregiverRepository.deleteById(caregiver.getId());
        List<Caregiver> caregivers = caregiverRepository.findAll();
        for (Caregiver iterator : caregivers) {
            System.out.println(iterator.getName() + " " + iterator.getId() + " " + iterator.getUsername() + " " + iterator.getPassword());
        }
        System.out.println("\n\n\n");

        user.setName("Anastasia");
        user.setUsername("anastasia");
        Doctor doctor = Doctor.doctorFromUserBuilder()
                .user(user).build();
        doctor.setUsername("gui");
        doctor.setPassword(bcrypt.encode("000"));
        doctor = doctorRepository.save(doctor);
        System.out.println(doctor.getId());

        Doctor doctor2 = Doctor.doctorFromUserBuilder()
                .user(user).build();
        doctor2.setUsername("doctor2");
        doctor2.setPassword(bcrypt.encode("000"));
        doctor2.setName("Perfect doctor");
        doctor2 = doctorRepository.save(doctor2);
        System.out.println(doctor2.getId());




        Patient patient = Patient.patientBuilder()
                .id(UUID.randomUUID())
                .username("patient@a.com")
                .password(bcrypt.encode("000"))
                .name("Pacientul Alfa")
                .gender(Gender.FEMALE)
                .birthDate(LocalDate.now())
                .medicalRecord("Parkinson")
                .caregiver(caregiver1)
                .doctor(doctor)
                .build();
        patient = patientRepository.save(patient);
        System.out.println(patient.getUsername()+" " + patient.getCaregiver().getName());
        System.out.println("Patient1 "+ patient.getId());

        Patient patient2 = Patient.patientBuilder()
                .id(UUID.randomUUID())
                .username("patient2")
                .password(bcrypt.encode("000"))
                .name("Pacientul Doi")
                .gender(Gender.FEMALE)
                .birthDate(LocalDate.now())
                .medicalRecord("Schizofrenia")
                .caregiver(caregiver2)
                .doctor(doctor)
                .build();
        patient2 = patientRepository.save(patient2);
        System.out.println(patient.getUsername()+" " + patient.getCaregiver().getName());
        System.out.println("Patient2 "+ patient2.getId());



        //--------------------------------MEDICATIONS--------------------------------------------------
        Medication medication1 = Medication.builder().name("Crestor").sideEffect("headache, depression, indigestion").build();
        medication1 = medicationRepository.save(medication1);

        Medication medication2 = Medication.builder().name("Nexium").sideEffect("allergy, irregular heartbeat").build();
        medication2 = medicationRepository.save(medication2);

        Medication medication3 = Medication.builder().name("Belara").sideEffect("headache, depression, indigestion").build();
        medication3 = medicationRepository.save(medication3);

        Medication medication4 = Medication.builder().name("Tonotil").sideEffect("allergy, irregular heartbeat").build();
        medication4 = medicationRepository.save(medication4);


        //----------------------------MEDICATION PLANS -----------------------------------------------------------
        //patient 1
        //valid
        MedicationPlan medicationPlan1= new MedicationPlan(patient, LocalDate.now(), LocalDate.of(2019, Month.DECEMBER, 15));
        medicationPlan1 = medicationPlanRepository.save(medicationPlan1);
        //future
        MedicationPlan medicationPlan3= new MedicationPlan(patient, LocalDate.of(2019, Month.DECEMBER, 10), LocalDate.of(2020, Month.JANUARY, 10));
        medicationPlan3 = medicationPlanRepository.save(medicationPlan3);

        //patient 2
        //in time
        MedicationPlan medicationPlan2= new MedicationPlan(patient2, LocalDate.of(2019, Month.NOVEMBER, 1), LocalDate.of(2020, Month.FEBRUARY, 1));
        medicationPlan2 = medicationPlanRepository.save(medicationPlan2);
        //past time
        MedicationPlan medicationPlan4= new MedicationPlan(patient2, LocalDate.of(2019, Month.MAY, 1), LocalDate.of(2019, Month.JULY, 1));
        medicationPlan4 = medicationPlanRepository.save(medicationPlan4);
        //future time
        MedicationPlan medicationPlan5= new MedicationPlan(patient2, LocalDate.of(2020, Month.JANUARY, 1), LocalDate.of(2020, Month.FEBRUARY, 1));
        medicationPlan5 = medicationPlanRepository.save(medicationPlan5);



        //---------------------------------------MEDICATION PER PLAN--------------------------------------------------
        //patient 1, med plan 1
        MedicationPerPlan medicationPerPlan1 = new MedicationPerPlan(medication1, medicationPlan1, 0.5, "2-3");
        medicationPerPlan1 = medicationPerPlanRepository.save(medicationPerPlan1);
        MedicationPerPlan medicationPerPlan3 = new MedicationPerPlan(medication3, medicationPlan1, 1.5, "2-3");
        medicationPerPlan3 = medicationPerPlanRepository.save(medicationPerPlan3);
        MedicationPerPlan medicationPerPlan4 = new MedicationPerPlan(medication4, medicationPlan1, 200, "14-15");
        medicationPerPlan4 = medicationPerPlanRepository.save(medicationPerPlan4);
        MedicationPerPlan medicationPerPlan5 = new MedicationPerPlan(medication2, medicationPlan3, 10, "6-7");
        medicationPerPlan5 = medicationPerPlanRepository.save(medicationPerPlan5);

        //patient 2
        MedicationPerPlan medicationPerPlan2 = new MedicationPerPlan(medication2, medicationPlan2, 2, "2-3");
        medicationPerPlan2 = medicationPerPlanRepository.save(medicationPerPlan2);
        MedicationPerPlan medicationPerPlan6 = new MedicationPerPlan(medication1, medicationPlan2, 3, "2-3");
        medicationPerPlan6 = medicationPerPlanRepository.save(medicationPerPlan6);
        MedicationPerPlan medicationPerPlan7 = new MedicationPerPlan(medication3, medicationPlan2, 0.8, "14-15");
        medicationPerPlan7 = medicationPerPlanRepository.save(medicationPerPlan7);
        MedicationPerPlan medicationPerPlan8 = new MedicationPerPlan(medication4, medicationPlan4, 1000, "13-14");
        medicationPerPlan8 = medicationPerPlanRepository.save(medicationPerPlan8);
        MedicationPerPlan medicationPerPlan9 = new MedicationPerPlan(medication4, medicationPlan5, 3000, "13-14");
        medicationPerPlan9 = medicationPerPlanRepository.save(medicationPerPlan9);



        Activity a = new Activity(54545L, 776765L, "mor", UUID.randomUUID());
        activityRepository.save(a);
    }
}
