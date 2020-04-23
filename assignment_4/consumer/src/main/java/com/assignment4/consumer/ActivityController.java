package com.assignment4.consumer;


import com.assignment4.consumer.view.DayPieChart;
import org.jfree.chart.*;
import com.assignment4.consumer.example.wsdl.*;
import com.assignment4.consumer.view.ActivityView;
import com.assignment4.consumer.view.LoginView;
import lombok.Getter;
import lombok.Setter;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RefineryUtilities;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Getter
@Setter
public class ActivityController {

    private LoginView loginView;
    private ActivityView activityView;
    private ActivityClient activityClient;
    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ActivityConfiguration.class);
    private List<PatientXsd> patientXsdList;
    private List<ActivityXsd> activityXsdList;
    private List<MedicationPlanXsd> medicationPlanXsdList;
    private Map<LocalDate, Map<String, Long>> activitiesPerDays = new HashMap<>();
    private List<ActivityXsd> abnormalActivities;

    public ActivityController() {

        this.activityClient = context.getBean(ActivityClient.class);
        loginView = new LoginView(this);

    }

    public void loginAction(String username, String password) {

        GetLoginResponse response = activityClient.getLogin(username, password);

        System.out.println("Lista de pacienti: ");
        System.err.println(response.getPatients());
        patientXsdList = response.getPatients();
        if (response.isValidate()) {
            loginView.setVisible(false);
            activityView = new ActivityView(this);
            activityView.setVisible(true);
        }

    }

    public void choosePatientAction(String id) {

        GetActivityResponse activityResponse = activityClient.getActivities(id);
        this.activityXsdList = activityResponse.getActivities();
        System.out.println("---------------------------------ID: " + id);
        GetMedicationPlanResponse medicationResponse = activityClient.getMedications("4aec508f-ab9c-4e7e-8dec-7984ecfbd58d");
        //GetMedicationPlanResponse medicationResponse = activityClient.getMedications(id);
        this.medicationPlanXsdList = medicationResponse.getMedicationPlans();

//        for(ActivityXsd i: activityXsdList){
//            System.out.println(i.getId()+ " "+ i.getName()+ " "+ i.getStart()+ " "+i.getEnd());
//        }
//
//        System.out.println("????????????????????????????????????????????????????");
//        for(MedicationPlanXsd j:medicationPlanXsdList){
//            System.out.println(j.getId()+" "+j.getMedicationName()+" "+j.getInterval()+" "+j.isTaken());
//        }
        //for the daily charts
        splitTheActivitiesOnDays();

        //for the medication list
        activityView.createMedicationGroupPanels();

        //find abnormal activities
        findAbnormalActivities();
        activityView.createAbnormalActivityGroupPanels();


    }

    private void splitTheActivitiesOnDays() {

        //convert the string into long and after in LocalDate
//        for(ActivityXsd i: activityXsdList){
//            LocalDate startDate = Instant.ofEpochMilli(Long.parseLong(i.getStart())).atZone(ZoneId.systemDefault()).toLocalDate();
//            System.out.println(startDate);
//
//        }

        //store the days as entries in a map
        activityXsdList.forEach(e -> activitiesPerDays.put(Instant.ofEpochMilli(Long.parseLong(e.getStart())).atZone(ZoneId.systemDefault()).toLocalDate(),
                new HashMap<String, Long>()));
        //store each activity per day
        activityXsdList.forEach(e -> {
            activitiesPerDays.get(Instant.ofEpochMilli(Long.parseLong(e.getStart())).atZone(ZoneId.systemDefault()).toLocalDate())
                    .computeIfPresent(e.getName(), (k, v) -> v = v + (Long.parseLong(e.getEnd()) - Long.parseLong(e.getStart())));
            activitiesPerDays.get(Instant.ofEpochMilli(Long.parseLong(e.getStart())).atZone(ZoneId.systemDefault()).toLocalDate())
                    .putIfAbsent(e.getName(), (Long.parseLong(e.getEnd()) - Long.parseLong(e.getStart())));
        });

        System.out.println("DE aici -------------------------------------------------------------");
        for (LocalDate iter : activitiesPerDays.keySet()) {
            System.out.println(iter.toString());
        }

        activityView.createDayGroupButtons(new ArrayList<>(activitiesPerDays.keySet()));
    }

    public void chooseDayAction(LocalDate day) {
        System.out.println("Am ales ziua  " + day.toString());

        DefaultPieDataset dataset = new DefaultPieDataset();
        Map<String, Long> myDayData = activitiesPerDays.get(day);
        for (String activity : myDayData.keySet()) {
            dataset.setValue(activity, myDayData.get(activity));
        }

        DayPieChart demo = new DayPieChart(day, myDayData);
        demo.setSize(560, 367);
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    public void findAbnormalActivities() {
        this.abnormalActivities = new ArrayList<>();
        for (ActivityXsd activity : activityXsdList) {
            Long start = Long.parseLong(activity.getStart());
            Long end = Long.parseLong(activity.getEnd());
            String name = activity.getName();
            if (name.contains("Sleeping") && (end - start > 12 * 3600000L)) {
                abnormalActivities.add(activity);
            }
            if ((name.contains("Toileting") || name.contains("Showering")) && (end - start > 3600000L)) {
                abnormalActivities.add(activity);
            }
            if (name.contains("Leaving") && (end - start > 12 * 3600000L)) {
                abnormalActivities.add(activity);
            }

        }
    }

    public void checkAbnormal(JCheckBox checkBox, JTextField textField, JButton button) {
        textField.setEnabled(checkBox.isSelected());
        button.setEnabled(checkBox.isSelected());
    }

    public void sendRecommendation(JTextField textField, ActivityXsd xsd) {
        xsd.setRecommendation(textField.getText());
        textField.setEnabled(false);
        GetRecommendationResponse response = activityClient.getRecommendation(xsd);
        System.out.println("Recommendation: " + xsd.getRecommendation());
        System.out.println("Recommendation stored: " + response.getActivity().getRecommendation());
    }


}
