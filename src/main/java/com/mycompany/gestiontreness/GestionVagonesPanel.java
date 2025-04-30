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

public class GestionVagonesPanel extends JPanel {
    private JFrame frame;

    public GestionVagonesPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(244, 244, 244)); // Fondo gris claro
        createUI();
    }

    private void createUI() {
        // 1. Crear Header con botón de volver
        createHeader();

        // 2. Crear Panel de Botones Principales
        createMainButtonsPanel();
    }

    private void createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(26, 38, 116)); // Azul oscuro corporativo
        header.setPreferredSize(new Dimension(800, 80));

        // Título centrado
        JLabel title = new JLabel("GESTIÓN DE VAGONES", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);

        // Botón Volver
        JButton backButton = new JButton("Volver");
        backButton.setBackground(new Color(205, 163, 74)); // Dorado
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.addActionListener(e -> {
            frame.setContentPane(new GestionGeneralPanel(frame));
            frame.revalidate();
        });

        header.add(backButton, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);
    }

    private void createMainButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 1, 20, 20));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(40, 150, 40, 150));
        buttonsPanel.setBackground(new Color(244, 244, 244));

        // Botón Agregar Vagón
        JButton agregarBtn = createMenuButton("AGREGAR VAGÓN");
        agregarBtn.addActionListener(e -> {
            frame.setContentPane(new AgregarVagonPanel(frame));
            frame.revalidate();
        });

        // Botón Eliminar o Modificar
        JButton modificarBtn = createMenuButton("ELIMINAR O MODIFICAR");
        modificarBtn.addActionListener(e -> {
            if (GestorVagones.getInstance().getVagones().isEmpty()) {
                JOptionPane.showMessageDialog(frame, 
                    "No hay vagones registrados para modificar", 
                    "Información", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                frame.setContentPane(new ModificarVagonPanel(frame));
                frame.revalidate();
            }
        });

        // Botón Disponibilidad
        JButton disponibilidadBtn = createMenuButton("DISPONIBILIDAD");
        disponibilidadBtn.addActionListener(e -> {
            if (GestorVagones.getInstance().getVagones().isEmpty()) {
                JOptionPane.showMessageDialog(frame, 
                    "No hay vagones registrados para mostrar", 
                    "Información", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                frame.setContentPane(new DisponibilidadVagonesPanel(frame));
                frame.revalidate();
            }
        });

        buttonsPanel.add(agregarBtn);
        buttonsPanel.add(modificarBtn);
        buttonsPanel.add(disponibilidadBtn);

        add(buttonsPanel, BorderLayout.CENTER);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(205, 163, 74)); // Dorado
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        // Efecto hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(225, 183, 94)); // Dorado más claro
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(205, 163, 74)); // Dorado original
            }
        });
        
        return button;
    }
}