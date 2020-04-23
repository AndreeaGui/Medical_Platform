package com.assignment4.producer.service;

import com.assignment4.producer.entities.Activity;
import com.assignment4.producer.entities.Patient;
import com.assignment4.producer.entities.TakenMedication;
import com.assignment4.producer.entities.User;
import com.assignment4.producer.repositories.*;
import io.spring.guides.gs_producing_web_service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final TakenMedicationRepository takenMedicationRepository;


    public void findAbnormalActivity() {
        List<Activity> activityList = activityRepository.findAll();

        activityList.stream().filter(this::problemsWereFound).forEach(x -> System.out.println(x));
    }

    private boolean problemsWereFound(Activity activity) {
        Long start = activity.getStarTime();
        Long end = activity.getEndTime();
        String name = activity.getActivity();
        if (name.contains("Sleeping") && (end - start > 12 * 3600000L)) {
            return true;
        }
        if ((name.contains("Toileting") || name.contains("Showering")) && (end - start > 3600000L)) {
            return true;
        }
        if (name.contains("Leaving") && (end - start > 12 * 3600000L)) {
            return true;
        }
        return false;
    }

    public GetActivityResponse findActivitiesPerPatient(GetActivityRequest request) {
        GetActivityResponse response = new GetActivityResponse();

        UUID patientId = UUID.fromString(request.getPatientId());
//        Patient patient = patientRepository.findById(patientId).get();
        List<Activity> activities = activityRepository.findAll();
//        List<ActivityXsd> activityXsdList = new ArrayList<>();
        for (Activity iterator : activities) {
            ActivityXsd xsd = new ActivityXsd();
            xsd.setId(iterator.getId().toString());
            xsd.setName(iterator.getActivity());
            xsd.setStart(iterator.getStarTime().toString());
            xsd.setEnd(iterator.getEndTime().toString());
            xsd.setRecommendation(iterator.getRecommendation());

            response.getActivities().add(xsd);
        }

        return response;
    }

    public GetLoginResponse loginXsd(GetLoginRequest request) {
        GetLoginResponse response = new GetLoginResponse();
        String username = request.getUsername();
//        String password = bcrypt.encode(request.getPassword());
        String password = request.getPassword();
        System.out.println(username);
        System.out.println(password);

        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            response.setValidate(true);
            System.out.println("User " + user.get().toString());
//            List<Patient> patients = patientRepository.findAllByDoctor(user.get().getId());
            List<Patient> patients = patientRepository.findAll();
            for (Patient iterator : patients) {
                PatientXsd xsd = new PatientXsd();
                xsd.setId(iterator.getId().toString());
                xsd.setName(iterator.getName());

                response.getPatients().add(xsd);
            }
        } else
            response.setValidate(false);

        return response;
    }

    public GetMedicationPlanResponse findMedicationPlans(GetMedicationPlanRequest request) {
        GetMedicationPlanResponse response = new GetMedicationPlanResponse();

        Optional<Patient> patient = patientRepository.findById(UUID.fromString(request.getPatientId()));
        if (patient.isPresent()) {
            List<TakenMedication> takenMedications = takenMedicationRepository.findByPatient(patient.get());
            for (TakenMedication iterator : takenMedications) {
                MedicationPlanXsd xsd = new MedicationPlanXsd();
                xsd.setId(iterator.getId().toString());
                xsd.setInterval(iterator.getIntakeTime().toString());
                xsd.setTaken(iterator.isTaken());
                xsd.setMedicationId(iterator.getMedication().getId().toString());
                xsd.setMedicationName(iterator.getMedication().getName());

                response.getMedicationPlans().add(xsd);
            }
        }
        return response;
    }

    public GetRecommendationResponse updateRecommndation(GetRecommendationRequest request){
        GetRecommendationResponse response = new GetRecommendationResponse();
        Activity a = new Activity();
        Optional<Activity> foundActivity = activityRepository.findById(UUID.fromString(request.getActivity().getId()));
        if(foundActivity.isPresent()){
            a = foundActivity.get();
            a.setRecommendation(request.getActivity().getRecommendation());
            activityRepository.save(a);

        }
        ActivityXsd xsd = new ActivityXsd();
        xsd.setRecommendation(a.getRecommendation());
        xsd.setId(a.getId().toString());
        xsd.setName(a.getActivity());
        xsd.setStart(a.getStarTime().toString());
        xsd.setEnd(a.getEndTime().toString());

        response.setActivity(xsd);
        return response;
    }

}
