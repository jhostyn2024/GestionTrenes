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

public class MainMenuPanel extends JPanel {
    private JFrame frame;

    public MainMenuPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUI();
    }

    private void createUI() {
        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(26, 38, 116));
        header.setPreferredSize(new Dimension(800, 100));
        
        JLabel title = new JLabel("MEDINET - MENÚ PRINCIPAL", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        header.add(title);
        
        add(header, BorderLayout.NORTH);

        // Botones principales
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 1, 20, 20));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(40, 150, 40, 150));
        buttonsPanel.setBackground(new Color(240, 240, 240));

        String[] options = {
            "GESTIÓN GENERAL", 
            "GESTIÓN DE HORARIOS", 
            "GESTIÓN DE RUTAS"
        };

        for (String text : options) {
            JButton button = createMenuButton(text);
            button.addActionListener(e -> handleMenuSelection(text));
            buttonsPanel.add(button);
        }

        add(buttonsPanel, BorderLayout.CENTER);

        // Footer
        JPanel footer = new JPanel();
        footer.setBackground(new Color(26, 38, 116));
        
        JButton exitButton = new JButton("SALIR DEL SISTEMA");
        exitButton.setBackground(new Color(150, 40, 40));
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(e -> System.exit(0));
        
        footer.add(exitButton);
        add(footer, BorderLayout.SOUTH);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(205, 163, 74));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(300, 80));
        
        // Efecto hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(225, 183, 94));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(205, 163, 74));
            }
        });
        
        return button;
    }

    private void handleMenuSelection(String option) {
        switch (option) {
            case "GESTIÓN GENERAL":
                frame.setContentPane(new GestionGeneralPanel(frame));
                break;
            case "GESTIÓN DE HORARIOS":
                frame.setContentPane(new GestionHorariosPanel(frame));
                break;
            case "GESTIÓN DE RUTAS":
                frame.setContentPane(new GestionRutasPanel(frame));
                break;
        }
        frame.revalidate();
    }
}

