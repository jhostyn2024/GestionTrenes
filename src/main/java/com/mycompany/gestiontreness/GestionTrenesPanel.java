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

public class GestionTrenesPanel extends JPanel {
    private JFrame frame;

    public GestionTrenesPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        createHeader();
        createButtonsPanel();
    }

    private void createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(30, 58, 138));
        header.setPreferredSize(new Dimension(800, 80));
        
        JLabel title = new JLabel("GESTIÓN DE TRENES", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        
        JButton backButton = new JButton("Volver");
        backButton.setBackground(new Color(205, 163, 74));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new GestionGeneralPanel(frame));
            frame.revalidate();
        });
        
        header.add(backButton, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);
    }

    private void createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(4, 1, 20, 20));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(40, 150, 40, 150));
        buttonsPanel.setBackground(new Color(244, 244, 244));

        String[] options = {
            "AGREGAR NUEVO TREN", 
            "VER LISTA DE TRENES", 
            "MODIFICAR TREN EXISTENTE", 
            "ELIMINAR TREN"
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
        button.setPreferredSize(new Dimension(300, 60));
        return button;
    }

    private void handleButtonAction(ActionEvent e) {
        String action = ((JButton)e.getSource()).getText();
        
        switch (action) {
            case "AGREGAR NUEVO TREN":
                frame.setContentPane(new AgregarTrenPanel(frame));
                break;
            case "VER LISTA DE TRENES":
                frame.setContentPane(new ListaTrenesPanel(frame));
                break;
            case "MODIFICAR TREN EXISTENTE":
                frame.setContentPane(new ModificarEliminarTrenPanel(frame));
                break;
            case "ELIMINAR TREN":
                frame.setContentPane(new ModificarEliminarTrenPanel(frame));
                break;
        }
        frame.revalidate();
    }
}

