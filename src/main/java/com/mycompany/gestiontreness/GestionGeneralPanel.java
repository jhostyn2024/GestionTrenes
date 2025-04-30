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
        createUI();
    }

    private void createUI() {
        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(30, 58, 138));
        header.setPreferredSize(new Dimension(800, 80));
        
        JLabel title = new JLabel("GESTIÓN GENERAL", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        
        JButton backButton = new JButton("Volver");
        backButton.setBackground(new Color(205, 163, 74));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new MainMenuPanel(frame));
            frame.revalidate();
        });
        
        header.add(backButton, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        // Botones principales
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 1, 20, 20));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(40, 150, 40, 150));
        buttonsPanel.setBackground(new Color(244, 244, 244));

        String[] options = {
            "GESTIÓN DE TRENES", 
            "GESTIÓN DE VAGONES"
        };

        for (String text : options) {
            JButton button = createMenuButton(text);
            button.addActionListener(e -> handleButtonAction(text));
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

    private void handleButtonAction(String action) {
        switch (action) {
            case "GESTIÓN DE TRENES":
                frame.setContentPane(new GestionTrenesPanel(frame));
                break;
            case "GESTIÓN DE VAGONES":
                frame.setContentPane(new GestionVagonesPanel(frame));
                break;
        }
        frame.revalidate();
    }
}
