/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

/**
 *
 * @author jhost
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GestionGeneralPanel extends JPanel {
    private JFrame frame;

    public GestionGeneralPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        createHeader();
        createButtonsPanel();
    }

    private void createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(30, 58, 138));
        header.setPreferredSize(new Dimension(800, 80));
        
        JLabel title = new JLabel("GESTIÓN GENERAL", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        
        JButton backButton = new JButton("Cerrar Sesión");
        backButton.setBackground(new Color(205, 163, 74));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new LoginPanel(frame));
            frame.revalidate();
        });
        
        header.add(backButton, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);
    }

    private void createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 1, 20, 20));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(40, 150, 40, 150));
        buttonsPanel.setBackground(new Color(244, 244, 244));

        String[] options = {
            "GESTIÓN DE TRENES", 
            "GESTIÓN DE VAGONES", 
            "GESTIÓN DE RUTAS"
        };

        for (String text : options) {
            JButton button = createMenuButton(text);
            button.addActionListener(this::handleButtonAction);
            buttonsPanel.add(button);
        }

        add(buttonsPanel, BorderLayout.CENTER);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(205, 163, 74));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(300, 80));
        return button;
    }

    private void handleButtonAction(ActionEvent e) {
        String action = ((JButton)e.getSource()).getText();
        
        switch (action) {
            case "GESTIÓN DE TRENES":
                frame.setContentPane(new GestionTrenesPanel(frame));
                break;
            case "GESTIÓN DE VAGONES":
                frame.setContentPane(new GestionVagonesPanel(frame));
                break;
            case "GESTIÓN DE RUTAS":
                frame.setContentPane(new GestionRutasPanel(frame));
                break;
        }
        frame.revalidate();
    }
}

