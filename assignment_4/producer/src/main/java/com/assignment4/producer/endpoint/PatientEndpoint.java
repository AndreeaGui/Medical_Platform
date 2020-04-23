package com.assignment4.producer.endpoint;

import com.assignment4.producer.repositories.ActivityRepository;
import com.assignment4.producer.service.ActivityService;
import io.spring.guides.gs_producing_web_service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class PatientEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private final ActivityService activityService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getActivityRequest")
    @ResponsePayload
    public GetActivityResponse getActivities(@RequestPayload GetActivityRequest request) {
        return activityService.findActivitiesPerPatient(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getLoginRequest")
    @ResponsePayload
    public GetLoginResponse getLogin(@RequestPayload GetLoginRequest request) {
        return activityService.loginXsd(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMedicationPlanRequest")
    @ResponsePayload
    public GetMedicationPlanResponse getMedicationPlans(@RequestPayload GetMedicationPlanRequest request) {
        GetMedicationPlanResponse response = new GetMedicationPlanResponse();
        return activityService.findMedicationPlans(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRecommendationRequest")
    @ResponsePayload
    public GetRecommendationResponse getRecommendation(@RequestPayload GetRecommendationRequest request) {
        GetRecommendationResponse response = new GetRecommendationResponse();
        return activityService.updateRecommndation(request);
    }
}