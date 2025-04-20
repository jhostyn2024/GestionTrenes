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

public class GestionHorariosPanel extends JPanel {
    private final JFrame frame;
    private final Color GOLD_COLOR = new Color(198, 168, 77); // Color #C6A84D

    public GestionHorariosPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUI();
    }

    private void createUI() {
        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(0, 86, 179));
        header.setPreferredSize(new Dimension(800, 80));
        
        JLabel title = new JLabel("GESTIÓN DE HORARIOS", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        header.add(title);
        
        add(header, BorderLayout.NORTH);

        // Main content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 150, 40, 150));
        contentPanel.setBackground(new Color(240, 240, 240));

        // Botón Agregar Horario
        JButton btnAgregar = createMenuButton("AGREGAR HORARIO");
        btnAgregar.addActionListener(e -> {
            frame.setContentPane(new AgregarHorarioPanel(frame));
            frame.revalidate();
        });
        
        // Botón Ver Disponibilidad
        JButton btnDisponibilidad = createMenuButton("VER DISPONIBILIDAD");
        btnDisponibilidad.addActionListener(e -> {
            frame.setContentPane(new DisponibilidadPanel(frame));
            frame.revalidate();
        });
        
        // Botón Modificar/Eliminar
        JButton btnModificarEliminar = createMenuButton("MODIFICAR/ELIMINAR");
        btnModificarEliminar.addActionListener(e -> {
            frame.setContentPane(new ModificarEliminarHorarioPanel(frame));
            frame.revalidate();
        });

        // Añadir botones al panel
        contentPanel.add(btnAgregar);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(btnDisponibilidad);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(btnModificarEliminar);

        add(contentPanel, BorderLayout.CENTER);

        // Footer
        JPanel footer = new JPanel();
        footer.setBackground(new Color(240, 240, 240));
        
        JButton backButton = new JButton("VOLVER AL MENÚ PRINCIPAL");
        backButton.setBackground(GOLD_COLOR);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.addActionListener(e -> {
            frame.setContentPane(new MainMenuPanel(frame));
            frame.revalidate();
        });
        
        footer.add(backButton);
        add(footer, BorderLayout.SOUTH);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(GOLD_COLOR);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(300, 50));
        button.setMaximumSize(new Dimension(300, 50));
        button.setFocusPainted(false);
        
        // Efecto hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(210, 180, 90)); // Color más claro al pasar el mouse
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(GOLD_COLOR);
            }
        });
        
        return button;
    }
}