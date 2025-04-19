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

public class GestionGeneralPanel extends JPanel {
    public GestionGeneralPanel(JFrame frame) {
        setLayout(new BorderLayout());
        createHeader(frame);
        createButtonsPanel(frame);
    }

    private void createHeader(JFrame frame) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(30, 58, 138));
        
        JLabel title = new JLabel("GESTIÓN GENERAL", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        
        JButton backButton = new JButton("Volver");
        backButton.setBackground(new Color(205, 163, 74));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> frame.setContentPane(new MainMenuPanel(frame)));
        
        header.add(backButton, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);
    }

    private void createButtonsPanel(JFrame frame) {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBackground(new Color(244, 244, 244));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        String[] options = {"GESTIÓN DE TRENES", "GESTIÓN DE VAGONES", "REPORTES"};
        for (String text : options) {
            JButton button = createMenuButton(text);
            button.addActionListener(e -> handleButtonAction(frame, text));
            buttonsPanel.add(button);
            buttonsPanel.add(Box.createVerticalStrut(20));
        }

        add(buttonsPanel, BorderLayout.CENTER);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(205, 163, 74));
        button.setForeground(Color.WHITE);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(300, 50));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        return button;
    }

    private void handleButtonAction(JFrame frame, String action) {
        switch (action) {
            case "GESTIÓN DE TRENES":
                frame.setContentPane(new GestionTrenesPanel(frame));
                break;
            case "GESTIÓN DE VAGONES":
                frame.setContentPane(new GestionVagonesPanel(frame));
                break;
            case "REPORTES":
                JOptionPane.showMessageDialog(frame, "Módulo de reportes en desarrollo", "Información", JOptionPane.INFORMATION_MESSAGE);
                break;
        }
        frame.revalidate();
    }
}

