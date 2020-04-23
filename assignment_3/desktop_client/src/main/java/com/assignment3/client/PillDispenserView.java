package com.assignment3.client;

import com.assignment3.client.grpc.Medication;
import com.assignment3.client.grpc.MedicationPlan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PillDispenserView extends JFrame {

    private List<JLabel> medicationNameList = new ArrayList<>();
    private List<JLabel> dosageList = new ArrayList<>();
    private List<JLabel> intervalList = new ArrayList<>();
    private List<JButton> buttonList = new ArrayList<>();
    private List<JPanel> panelList = new ArrayList<>();

    private JPanel planPanel;
    private int xCoordPanel;
    private int yCoordPanel;
    private int xInsidePanel;
    private int yInsidePanel;

    Controller controller;


    public PillDispenserView(MedicationPlan medicationPlan, Controller controller) {

        super();
        System.out.println(medicationPlan);
        this.controller = controller;

       // refreshMedications(medicationPlan);
//        List<Medication> medications = medicationPlan.getMedicationsList();

        this.setVisible(true);
        this.setSize(900, 650);
        setTitle("Pill Dispenser");
        getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel("Medications");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        titleLabel.setBounds(300, 20, 150, 30);
        getContentPane().add(titleLabel);


        planPanel = new JPanel();
        planPanel.setBackground(new Color(0, 0, 128));
        planPanel.setBounds(45, 95, 755, 500);
        getContentPane().add(planPanel);
        planPanel.setLayout(null);

        JLabel timer = new JLabel(LocalTime.now().toString());
        timer.setFont(new Font("Tahoma", Font.BOLD, 18));
        timer.setBounds(15, 0, 230, 125);
        getContentPane().add(timer);

        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.setText(LocalTime.now().toString());
            }
        }).start();

//        this.xCoordPanel = 10;
//        this.yCoordPanel = 11;
//        this.xInsidePanel = 10;
//        this.yInsidePanel = 11;
//
//        for (Medication iterator : medications) {
//            createMedicationPanel(iterator, controller);
//        }
//
//        this.revalidate();
//        this.repaint();

    }

    private void createMedicationPanel(Medication medication, Controller controller) {

        JPanel medicationPanel = new JPanel();
        medicationPanel.setBackground(new Color(135, 206, 235));
        medicationPanel.setBounds(xCoordPanel, yCoordPanel, 735, 40);
        planPanel.add(medicationPanel);
        medicationPanel.setLayout(null);

        //JLabel nameLabel = new JLabel("Medication name");
        JLabel nameLabel = new JLabel(medication.getName());
        nameLabel.setBounds(xInsidePanel, yInsidePanel, 180, 20);
        medicationPanel.add(nameLabel);

        //JLabel dosageLabel = new JLabel("Dosage");
        JLabel dosageLabel = new JLabel(medication.getDosage());
        dosageLabel.setBounds(xInsidePanel + 200, yInsidePanel, 180, 20);
        medicationPanel.add(dosageLabel);

        //JLabel intervalLabel = new JLabel("Interval of intake");
        JLabel intervalLabel = new JLabel(medication.getInterval());
        intervalLabel.setBounds(xInsidePanel + 400, yInsidePanel, 180, 20);
        medicationPanel.add(intervalLabel);

        JButton takenButton = new JButton("Taken");
        takenButton.setBounds(xInsidePanel + 600, yInsidePanel, 90, 20);
        medicationPanel.add(takenButton);
        //takenButton.addActionListener(e-> controller.medicationWasTaken(medication.getId()));
        takenButton.addActionListener(e -> controller.medicationWasTaken(medication.getId(),medicationPanel));

        yCoordPanel += 50;

    }


    public void refreshMedications(List<Medication> medications){

//        List<Medication> medications = medicationPlan.getMedicationsList();

//        planPanel = new JPanel();
//        planPanel.setBackground(new Color(0, 0, 128));
//        planPanel.setBounds(45, 95, 755, 500);
//        getContentPane().add(planPanel);
//        planPanel.setLayout(null);

        planPanel.removeAll();

        this.revalidate();
        this.repaint();

        this.xCoordPanel = 10;
        this.yCoordPanel = 11;
        this.xInsidePanel = 10;
        this.yInsidePanel = 11;

        for (Medication iterator : medications) {
            createMedicationPanel(iterator, controller);
        }

        this.revalidate();
        this.repaint();

    }
}
