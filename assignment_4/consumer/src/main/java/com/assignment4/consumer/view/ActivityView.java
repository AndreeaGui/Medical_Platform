package com.assignment4.consumer.view;

import com.assignment4.consumer.ActivityController;
import com.assignment4.consumer.example.wsdl.ActivityXsd;
import com.assignment4.consumer.example.wsdl.MedicationPlanXsd;
import com.assignment4.consumer.example.wsdl.PatientXsd;
import javafx.scene.control.ScrollBar;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ActivityView extends JFrame {

    JTabbedPane tabbedPane;
    ActivityController controller;
    JPanel patientsPanel;
    JPanel panel;
    JPanel medicaitonPanel;
    JPanel activityPanel;

    public ActivityView(ActivityController controller) {

        this.controller = controller;

        getContentPane().setLayout(null);
        this.setBounds(50, 20, 1000, 650);

        patientsPanel = new JPanel();
        patientsPanel.setBackground(new Color(32, 178, 170));
        patientsPanel.setBounds(10, 11, 150, 550);
        getContentPane().add(patientsPanel);
        patientsPanel.setLayout(null);

//        JButton patientNameButton = new JButton("Name 1");
//        patientNameButton.setBounds(10, 11, 78, 23);
//        patientsPanel.add(patientNameButton);


        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(250, 11, 600, 550);
        getContentPane().add(tabbedPane);

        //Tab 1
        JPanel daysPanel = new JPanel();
        daysPanel.setBackground(new Color(175, 238, 238));
        tabbedPane.addTab("Days", null, daysPanel, null);
        daysPanel.setLayout(null);

        //small panel in days panel
        panel = new JPanel();
        panel.setBounds(10, 11, 130, 480);
        daysPanel.add(panel);

        //Tab 2
        medicaitonPanel = new JPanel();
        medicaitonPanel.setBackground(new Color(0, 128, 128));
        tabbedPane.addTab("Medication", null, medicaitonPanel, null);
        tabbedPane.setBackgroundAt(1, new Color(32, 178, 170));
        medicaitonPanel.setLayout(null);

        //Tab 3
        JPanel container =  new JPanel();
        container.setLayout(null);
        activityPanel = new JPanel();
//        JScrollPane scrollPane = new JScrollPane(activityPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        scrollPane.setPreferredSize(new Dimension(600, 500));

//        JScrollPane scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(3, 3, 595, 495);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        container.add(scrollPane);
        scrollPane.setViewportView(activityPanel);
//        JScrollPane scroll = new JScrollPane();
//        scroll.setPreferredSize(new Dimension(600,550));
//        scroll.setViewportView(activityPanel);
//        scroll.add(activityPanel);

        activityPanel.setBackground(new Color(224, 255, 255));
//        tabbedPane.addTab("Activity", null, activityPanel, null);
        tabbedPane.addTab("Activity", null, container, null);
        tabbedPane.setBackgroundAt(2, new Color(224, 255, 255));
        activityPanel.setLayout(null);


        this.createPatientGroupButtons(controller.getPatientXsdList());

    }

    private void createPatientGroupButtons(List<PatientXsd> patientXsdList) {

        patientsPanel.removeAll();

        int x = 5;
        int y = 10;
        for (PatientXsd xsd : patientXsdList) {
            JButton patientNameButton = new JButton(xsd.getName());
            patientNameButton.setBounds(x, y, 120, 20);
            patientsPanel.add(patientNameButton);
            y += 30;
            patientNameButton.addActionListener(e -> controller.choosePatientAction(xsd.getId()));
        }

        this.revalidate();
        this.repaint();
    }

    public void createDayGroupButtons(List<LocalDate> dates) {

        panel.removeAll();

        int x = 5;
        int y = 10;
        for(LocalDate iterator: dates)
        {
            JButton btnDayX = new JButton(iterator.toString());
            btnDayX.setBounds(x, y, 100, 15);
            panel.add(btnDayX);
            btnDayX.addActionListener(e->controller.chooseDayAction(iterator));
        }

        this.revalidate();
        this.repaint();
    }

    private void createMedicaitonPanel(MedicationPlanXsd xsd, int xPanel, int yPanel){

        JPanel takenPanel = new JPanel();
        takenPanel.setLayout(null);
        takenPanel.setBackground(new Color(135, 206, 235));
        takenPanel.setBounds(xPanel, yPanel, 500, 40);
        medicaitonPanel.add(takenPanel);

        JLabel nameLabel = new JLabel(xsd.getMedicationName());
        nameLabel.setBounds(10, 11, 89, 18);
        takenPanel.add(nameLabel);

        JLabel timeLabel = new JLabel(xsd.getInterval());
        timeLabel.setBounds(109, 11, 250, 18);
        takenPanel.add(timeLabel);

        JCheckBox takenCheckBox = new JCheckBox();
        takenCheckBox.setEnabled(false);
        takenCheckBox.setSelected(xsd.isTaken());
        takenCheckBox.setBounds(450, 11, 30, 18);
        takenPanel.add(takenCheckBox);

    }

    public void createMedicationGroupPanels(){

        medicaitonPanel.removeAll();

        int xPanel = 10;
        int yPanel = 10;

        for(MedicationPlanXsd iter: controller.getMedicationPlanXsdList()){
            createMedicaitonPanel(iter, xPanel, yPanel);
            yPanel += 50;
        }

        this.revalidate();
        this.repaint();

    }

    private void createAbnormalActivityPanel(ActivityXsd xsd, int xPanel, int yPanel){

        JPanel abnormalPanel = new JPanel();
        abnormalPanel.setLayout(null);
        abnormalPanel.setBackground(new Color(135, 206, 250));
        abnormalPanel.setBounds(xPanel, yPanel, 570, 40);
        activityPanel.add(abnormalPanel);

        JLabel nameLabel = new JLabel(xsd.getName());
        nameLabel.setBounds(10, 11, 90, 18);
        abnormalPanel.add(nameLabel);

        JLabel dayLabel = new JLabel(Instant.ofEpochMilli(Long.parseLong(xsd.getStart())).atZone(ZoneId.systemDefault()).toLocalDate().toString());
        dayLabel.setBounds(110, 11, 100, 18);
        abnormalPanel.add(dayLabel);

        Long hours = TimeUnit.MILLISECONDS.toHours(Long.parseLong(xsd.getEnd()) - Long.parseLong(xsd.getStart()));
        JLabel timeLabel = new JLabel(hours.toString());
        timeLabel.setBounds(230, 11, 30, 18);
        abnormalPanel.add(timeLabel);

        JCheckBox takenCheckBox = new JCheckBox();
        takenCheckBox.setEnabled(true);
//        takenCheckBox.setSelected(xsd.isTaken());
        takenCheckBox.setBounds(270, 11, 30, 18);
        abnormalPanel.add(takenCheckBox);

        JTextField recomField = new JTextField();
        recomField.setBounds(310 , 11, 150, 18);
        abnormalPanel.add(recomField);

        JButton recomButton = new JButton("Done");
        recomButton.setBounds(460, 11, 90, 18);
        abnormalPanel.add(recomButton);
        recomButton.setEnabled(false);


        takenCheckBox.addActionListener(e->controller.checkAbnormal(takenCheckBox, recomField, recomButton));
        recomButton.addActionListener(e-> controller.sendRecommendation(recomField, xsd));


    }

    public void createAbnormalActivityGroupPanels(){

        activityPanel.removeAll();

        int xPanel = 10;
        int yPanel = 10;

        for(ActivityXsd iter: controller.getAbnormalActivities()){
            createAbnormalActivityPanel(iter, xPanel, yPanel);
            yPanel += 50;
        }

        this.revalidate();
        this.repaint();
    }


}
