/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    private final Color GOLD_COLOR = new Color(198, 168, 77); // Color #C6A84D
    private final Color BLUE_COLOR = new Color(0, 86, 179); // Color azul corporativo

    public MainMenuPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUI();
    }

    private void createUI() {
        // Header
        JPanel header = new JPanel();
        header.setBackground(BLUE_COLOR);
        header.setPreferredSize(new Dimension(800, 100));
        
        JLabel title = new JLabel("MEDINET - MENÚ PRINCIPAL", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        
        add(header, BorderLayout.NORTH);

        // Botones principales
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(40, 150, 40, 150));
        buttonsPanel.setBackground(new Color(240, 240, 240));

        // Botones del menú
        JButton btnGestionGeneral = createMenuButton("GESTIÓN GENERAL");
        btnGestionGeneral.addActionListener(e -> {
            frame.setContentPane(new GestionGeneralPanel(frame));
            frame.revalidate();
        });

        JButton btnGestionHorarios = createMenuButton("GESTIÓN DE HORARIOS");
        btnGestionHorarios.addActionListener(e -> {
            frame.setContentPane(new GestionHorariosPanel(frame));
            frame.revalidate();
        });

        JButton btnGestionRutas = createMenuButton("GESTIÓN DE RUTAS");
        btnGestionRutas.addActionListener(e -> {
            frame.setContentPane(new GestionRutasPanel(frame));
            frame.revalidate();
        });

        JButton btnGestionEmpleados = createMenuButton("GESTIÓN DE EMPLEADOS");
        btnGestionEmpleados.addActionListener(e -> {
            frame.setContentPane(new GestionEmpleadosPanel(frame));
            frame.revalidate();
        });

        // Agregar botones al panel
        buttonsPanel.add(btnGestionGeneral);
        buttonsPanel.add(Box.createVerticalStrut(20));
        buttonsPanel.add(btnGestionHorarios);
        buttonsPanel.add(Box.createVerticalStrut(20));
        buttonsPanel.add(btnGestionRutas);
        buttonsPanel.add(Box.createVerticalStrut(20));
        buttonsPanel.add(btnGestionEmpleados);

        add(buttonsPanel, BorderLayout.CENTER);

        // Footer
        JPanel footer = new JPanel();
        footer.setBackground(new Color(240, 240, 240));
        
        JButton exitButton = new JButton("SALIR DEL SISTEMA");
        exitButton.setBackground(new Color(150, 40, 40));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.addActionListener(e -> System.exit(0));
        
        footer.add(exitButton);
        add(footer, BorderLayout.SOUTH);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(GOLD_COLOR);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setPreferredSize(new Dimension(350, 60));
        button.setMaximumSize(new Dimension(350, 60));
        
        // Efecto hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(210, 180, 90));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(GOLD_COLOR);
            }
        });
        
        return button;
    }
}