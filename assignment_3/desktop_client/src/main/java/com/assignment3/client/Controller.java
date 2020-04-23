package com.assignment3.client;

import com.assignment3.client.grpc.Medication;
import com.assignment3.client.grpc.MedicationPlan;
import com.assignment3.client.grpc.PatientMessage;
import com.assignment3.client.grpc.RequestGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.Timer;

public class Controller {

    private PillDispenserView view;
    private ManagedChannel channel;
    private RequestGrpc.RequestBlockingStub stub;
    private MedicationPlan medicationPlan;
    private List<Medication> medicationList;

//    public Controller(MedicationPlan medicationPlan){
    public Controller(){

        this.channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();

        this.stub = RequestGrpc.newBlockingStub(channel);

        this.medicationPlan = stub.sendMedications(PatientMessage.newBuilder()
                .setId("0163d1e1-79b5-4b3d-91d3-94a106bfc194")
                .build());

        medicationList = new ArrayList<>(medicationPlan.getMedicationsList());
        //medicationList = medicationPlan.getMedicationsList();

        //channel.shutdown();

        view = new PillDispenserView(medicationPlan, this);
        loadMedicationPlansPerDay();


    }

    public void medicationWasTaken(String medicationId, JPanel medicationPanel){
        System.out.println("Medication "+ medicationId+" was taken");
        medicationPanel.setVisible(false);
        stub.takeMedication(Medication.newBuilder()
        .setId(medicationId)
        .build());


        ListIterator<Medication> iter = medicationList.listIterator();
        while(iter.hasNext()){
            if(iter.next().getId().equals(medicationId)){
                iter.remove();
            }
        }

        //delete the medication from the daily storage
//        int iter = -1;
//        for(int i=0; i<medicationList.size(); i++){
//            if(medicationId.equals(medicationList.get(i).getId())){
//                iter = i;
//            }
//        }
//
//        if(iter!=-1){
//            medicationList.remove(iter);
//        }

        //channel.shutdown();
    }

    public void medicationWasNotTaken(String medicationId){

        stub.delayMedication(Medication.newBuilder()
                .setId(medicationId)
                .build());

        ListIterator<Medication> iter = medicationList.listIterator();
        while(iter.hasNext()){
            if(iter.next().getId().equals(medicationId)){
                iter.remove();
            }
        }

        //channel.shutdown();
    }

    private void loadMedicationPlansPerDay(){

        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                System.out.println("Task performed on " + LocalDate.now());
                medicationPlan = stub.sendMedications(PatientMessage.newBuilder()
                        .setId("0163d1e1-79b5-4b3d-91d3-94a106bfc194")
                        .build());

                //view.refreshMedications(medicationPlan);
                loadMedicationsPerHour();
            }
        };
        Timer timer = new Timer("Timer");
        long delay = 100L;
        //long period = 5000L;
        long period = 1000L * 60L * 60L * 24L; //the daily period
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }

    private void loadMedicationsPerHour(){

        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                System.out.println("Hour task performed on " + LocalDate.now());

                //parse the time interval
                //List<Medication> medications = medicationPlan.getMedicationsList();
                List<Medication> medicationForTheCurrentHour = new ArrayList<>();
                List<Medication> medicationNotTaken = new ArrayList<>();
                for(Medication iterator: medicationList){
                    String interval = iterator.getInterval();
                    List<String> splitedInterval = Arrays.asList(interval.split("-"));
                    LocalTime startTime = LocalTime.of(Integer.parseInt(splitedInterval.get(0)), 40);
                    LocalTime endTime = LocalTime.of(Integer.parseInt(splitedInterval.get(0)), 41);
                    if(startTime.isBefore(LocalTime.now()) && endTime.isAfter(LocalTime.now())){
                        medicationForTheCurrentHour.add(iterator);
                    }
                    if(endTime.isBefore(LocalTime.now())){
                        medicationNotTaken.add(iterator);
                    }
                }
                //show the medications needed for this hour
                view.refreshMedications(medicationForTheCurrentHour);

                //eliminate the medications that expired and were not taken
                for(Medication notTakenIterator: medicationNotTaken){
                    medicationWasNotTaken(notTakenIterator.getId());
                }


            }
        };
        Timer timer = new Timer("Timer");
        long delay = 100L;
        //long period = 5000L;
        //long period = 1000L * 60L * 60L; //once per hour
        long period = 1000L * 6; //once per minute
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }


}
