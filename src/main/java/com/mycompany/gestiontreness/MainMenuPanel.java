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

public class MainMenuPanel extends JPanel {
    public MainMenuPanel(JFrame frame) {
        setLayout(new BorderLayout());
        createHeader();
        createButtonsPanel(frame);
        createFooter();
    }

    private void createHeader() {
        JPanel header = new JPanel();
        header.setBackground(new Color(26, 38, 116));
        header.setPreferredSize(new Dimension(600, 80));
        
        JLabel title = new JLabel("MEDINET", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        header.add(title);
        
        add(header, BorderLayout.NORTH);
    }

    private void createButtonsPanel(JFrame frame) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(244, 244, 244));
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        String[] options = {"GESTIÓN GENERAL", "GESTIÓN DE HORARIOS", "GESTIÓN RUTAS"};
        for (String opt : options) {
            JButton button = createMenuButton(opt);
            button.addActionListener(e -> handleMenuSelection(frame, opt));
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(205, 163, 74));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        return button;
    }

    private void handleMenuSelection(JFrame frame, String option) {
        switch (option) {
            case "GESTIÓN GENERAL":
                frame.setContentPane(new GestionGeneralPanel(frame));
                break;
            case "GESTIÓN DE HORARIOS":
                frame.setContentPane(new GestionHorariosPanel(frame));
                break;
            case "GESTIÓN RUTAS":
                frame.setContentPane(new GestionRutasPanel(frame));
                break;
        }
        frame.revalidate();
    }

    private void createFooter() {
        JPanel footer = new JPanel();
        footer.setBackground(new Color(26, 38, 116));
        
        JButton salirBtn = new JButton("SALIR DEL SISTEMA");
        salirBtn.setBackground(new Color(150, 40, 40));
        salirBtn.setForeground(Color.WHITE);
        salirBtn.addActionListener(e -> System.exit(0));
        
        footer.add(salirBtn);
        add(footer, BorderLayout.SOUTH);
    }
}

