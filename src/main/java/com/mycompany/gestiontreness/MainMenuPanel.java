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
import java.awt.event.*;

public class MainMenuPanel extends JPanel {
    public MainMenuPanel(JFrame frame) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("MEDINET", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);

        JPanel header = new JPanel();
        header.setBackground(new Color(26, 38, 116));
        header.setPreferredSize(new Dimension(600, 80));
        header.setLayout(new BorderLayout());
        header.add(title, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(244, 244, 244));
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        String[] options = {"GESTIÓN GENERAL", "GESTIÓN DE HORARIOS", "GESTIÓN RUTAS"};
        for (String opt : options) {
            JButton button = new JButton(opt);
            button.setBackground(new Color(205, 163, 74));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setFont(new Font("Arial", Font.BOLD, 16));

            button.addActionListener(e -> {
                switch (opt) {
                    case "GESTIÓN GENERAL" -> frame.setContentPane(new GestionGeneralPanel(frame));
                    case "GESTIÓN DE HORARIOS" -> frame.setContentPane(new GestionHorariosPanel(frame));
                    case "GESTIÓN RUTAS" -> frame.setContentPane(new GestionRutasPanel(frame));
                }
                frame.revalidate();
            });

            buttonPanel.add(button);
        }

        add(header, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }
}


