package com.example.springdemo.grpc;

import com.example.springdemo.entities.MedicationPerPlan;
import com.example.springdemo.entities.Patient;
import com.example.springdemo.repositories.MedicationPerPlanRepository;
import com.example.springdemo.repositories.MedicationPlanRepository;
import com.example.springdemo.repositories.MedicationRepository;
import com.example.springdemo.repositories.PatientRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.lognet.springboot.grpc.GRpcService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@GRpcService
public class RequestService extends RequestGrpc.RequestImplBase{

    private final MedicationPerPlanRepository medicationPerPlanRepository;
    private final PatientRepository patientRepository;
    private final MedicationPlanRepository medicationPlanRepository;
    private final MedicationRepository medicationRepository;
    private final TakenMedicationRepository takenMedicationRepository;

//    @Autowired
//    public RequestService(MedicationPerPlanRepository medicationPerPlanRepository) {
//        this.medicationPerPlanRepository = medicationPerPlanRepository;
//    }

    @Override
    public void sendMedications(PatientMessage request, StreamObserver<MedicationPlan> responseObserver) {
        System.out.println("Inside send medications");

        String patientId = request.getId();
        System.out.println("Patient id: "+ patientId);
        MedicationPlan.Builder response = MedicationPlan.newBuilder();
        Medication.Builder medicationResponse = Medication.newBuilder();
        //List<Medication> medications = new ArrayList<>();

        List<Patient> patients = patientRepository.findAll();
        //take the first patient as hardcoded and extract all their medication plans
//        List<com.example.springdemo.entities.MedicationPlan> medicationPlansPerPatient
//                = medicationPlanRepository.findAllByPatient(patients.get(0));
        List<com.example.springdemo.entities.MedicationPlan> medicationPlansPerPatient
                = medicationPlanRepository.findAllByPatientAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                        patients.get(0), LocalDate.now(), LocalDate.now());
        System.out.println("MEdication plans for patient "+ patients.get(0).getId());

        List<MedicationPerPlan> plans = medicationPerPlanRepository.findAll();

        //extract all the medications for each medication plan
        List<MedicationPerPlan> medicationsPerPlan = new ArrayList<>();
        for(com.example.springdemo.entities.MedicationPlan iterator: medicationPlansPerPatient){
            medicationsPerPlan.addAll(medicationPerPlanRepository.findAllByPlan(iterator));
        }
        System.out.println(medicationsPerPlan);
        for(MedicationPerPlan iter: medicationsPerPlan){

            medicationResponse.setName(iter.getMedication().getName());
            System.out.println(medicationResponse.getName());
            medicationResponse.setDosage(Double.toString(iter.getDosage()));
            System.out.println(medicationResponse.getDosage());
            medicationResponse.setInterval(iter.getIntakeIntervals());
            System.out.println(medicationResponse.getInterval());
            medicationResponse.setId(iter.getMedication().getId().toString());
            System.out.println(medicationResponse.getId());

            response.addMedications(medicationResponse.build());
        }

        System.out.println(response.getMedications(0));
        response.setPatient(patients.get(0).getId().toString());
        response.setMedicationPlanId("oare ce plan scriu aici?");
        responseObserver.onNext(response.build());

        responseObserver.onCompleted();

    }

    @Override
    public void takeMedication(Medication request, StreamObserver<EmptyMessage> responseObserver) {

        EmptyMessage.Builder response = EmptyMessage.newBuilder();

        System.out.println(request.getId());

        Optional<com.example.springdemo.entities.Medication> foundMedication =
                medicationRepository.findById(UUID.fromString(request.getId()));
        if(foundMedication.isPresent()){
            System.out.println("Taken medication: "+foundMedication.get().getId());
            List<Patient> patients = patientRepository.findAll();

            TakenMedication takenMedication = new TakenMedication(foundMedication.get(), patients.get(0), true,
                    ZonedDateTime.now());
            takenMedicationRepository.save(takenMedication);
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();

    }

    @Override
    public void delayMedication(Medication request, StreamObserver<EmptyMessage> responseObserver) {

        EmptyMessage.Builder response = EmptyMessage.newBuilder();

        System.out.println(request.getId());

        Optional<com.example.springdemo.entities.Medication> foundMedication =
                medicationRepository.findById(UUID.fromString(request.getId()));
        if(foundMedication.isPresent()){
            System.out.println("Delayed medication: "+foundMedication.get().getId());
            List<Patient> patients = patientRepository.findAll();

            TakenMedication takenMedication = new TakenMedication(foundMedication.get(), patients.get(0), false,
                    ZonedDateTime.now());
            takenMedicationRepository.save(takenMedication);
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}
