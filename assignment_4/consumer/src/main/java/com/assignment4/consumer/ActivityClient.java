package com.assignment4.consumer;

import com.assignment4.consumer.example.wsdl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class ActivityClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(ActivityClient.class);

    public GetLoginResponse getLogin(String username, String password) {

        GetLoginRequest request = new GetLoginRequest();
        request.setUsername(username);
        request.setPassword(password);

        log.info("Username: " + username);

        GetLoginResponse response = (GetLoginResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/patients", request,
                        new SoapActionCallback(
                                "http://spring.io/guides/gs-producing-web-service/GetLoginResponse"));

        return response;
    }

    public GetActivityResponse getActivities(String patientId) {

        GetActivityRequest request = new GetActivityRequest();
        request.setPatientId(patientId);
        log.info("Patient id for activities: " + patientId);

        GetActivityResponse response = (GetActivityResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/patients", request,
                        new SoapActionCallback(
                                "http://spring.io/guides/gs-producing-web-service/getActivityResponse"));

        return response;
    }

    public GetMedicationPlanResponse getMedications(String patientId) {

        GetMedicationPlanRequest request = new GetMedicationPlanRequest();
        request.setPatientId(patientId);
        log.info("Patient id for medications: " + patientId);

        GetMedicationPlanResponse response = (GetMedicationPlanResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/patients", request,
                        new SoapActionCallback(
                                "http://spring.io/guides/gs-producing-web-service/getMedicationPlanResponse"));

        return response;
    }

    public GetRecommendationResponse getRecommendation(ActivityXsd activityXsd) {

        GetRecommendationRequest request = new GetRecommendationRequest();
        request.setActivity(activityXsd);
        log.info("Patient id for medications: " + activityXsd);

        GetRecommendationResponse response = (GetRecommendationResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/patients", request,
                        new SoapActionCallback(
                                "http://spring.io/guides/gs-producing-web-service/getRecommendationResponse"));

        return response;
    }
}
