package com.assignment4.consumer.view;

import com.assignment4.consumer.ActivityController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private JTextField usernameField;
    private JTextField passwordField;

    ActivityController controller;
    public LoginView(ActivityController activityController) {

        this.controller = activityController;

        this.setBounds(100, 100, 400, 300);
        setTitle("Login");
        getContentPane().setLayout(null);


        usernameField = new JTextField();
        usernameField.setBounds(126, 78, 131, 20);
        getContentPane().add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JTextField();
        passwordField.setText("");
        passwordField.setBounds(126, 134, 131, 20);
        getContentPane().add(passwordField);
        passwordField.setColumns(10);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(168, 53, 61, 14);
        getContentPane().add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(168, 109, 60, 14);
        getContentPane().add(lblPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(149, 187, 89, 23);
        getContentPane().add(btnLogin);

        getContentPane().setVisible(true);
        this.setVisible(true);


        btnLogin.addActionListener(e-> controller.loginAction(usernameField.getText(), passwordField.getText()));
    }




}
