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
import java.awt.event.ActionListener;

public class AdminGUI {
    private Controlador controlador;

    public AdminGUI(Controlador controlador) {
        this.controlador = controlador; // Recibe el controlador

        // Crear el marco de la GUI de administración
        JFrame frame = new JFrame("Administración");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear un panel para el contenido
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Layout vertical

        // Título
        JLabel titleLabel = new JLabel("ADMINISTRACIÓN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre el título y los botones

        // Botones de gestión
        JButton btnGestionEmpleados = new JButton("GESTIONAR EMPLEADOS");
        JButton btnGestionTrenes = new JButton("GESTIONAR TRENES");
        JButton btnGestionRutas = new JButton("GESTIONAR RUTAS");
        JButton btnLogout = new JButton("Cerrar Sesión");

        // Agregar acción a los botones
        btnGestionEmpleados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes abrir la ventana de gestión de empleados
                JOptionPane.showMessageDialog(frame, "Abrir gestión de empleados...");
            }
        });

        btnGestionTrenes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes abrir la ventana de gestión de trenes
                JOptionPane.showMessageDialog(frame, "Abrir gestión de trenes...");
            }
        });

        btnGestionRutas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes abrir la ventana de gestión de rutas
                JOptionPane.showMessageDialog(frame, "Abrir gestión de rutas...");
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginGUI(controlador); // Volver a la ventana de login
                frame.dispose(); // Cerrar la ventana actual
            }
        });

        // Agregar botones al panel
        panel.add(btnGestionEmpleados);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre botones
        panel.add(btnGestionTrenes);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre botones
        panel.add(btnGestionRutas);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre botones
        panel.add(btnLogout); // Agregar botón de cerrar sesión

        // Agregar el panel al marco
        frame.add(panel);
        frame.setVisible(true); // Hacer visible la ventana
    }
}