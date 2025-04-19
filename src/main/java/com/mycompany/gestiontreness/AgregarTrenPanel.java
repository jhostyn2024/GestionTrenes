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

public class AgregarTrenPanel extends JPanel {
    private JFrame frame;
    private JTextField marcaField, idField, capacidadField, horaSalidaField, horaLlegadaField, vagonesField, rutaField;

    public AgregarTrenPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        createHeader();
        createFormPanel();
    }

    private void createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(0, 86, 179));
        header.setPreferredSize(new Dimension(800, 80));
        
        JLabel title = new JLabel("AGREGAR NUEVO TREN", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        
        JButton backButton = new JButton("Cancelar");
        backButton.setBackground(new Color(205, 163, 74));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new GestionTrenesPanel(frame));
            frame.revalidate();
        });
        
        header.add(backButton, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);
    }

    private void createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));
        formPanel.setBackground(Color.WHITE);

        marcaField = new JTextField(20);
        idField = new JTextField(20);
        capacidadField = new JTextField(20);
        horaSalidaField = new JTextField(20);
        horaLlegadaField = new JTextField(20);
        vagonesField = new JTextField(20);
        rutaField = new JTextField(20);

        addFormField(formPanel, "Marca del tren:", marcaField);
        addFormField(formPanel, "Identificador único:", idField);
        addFormField(formPanel, "Capacidad de pasajeros:", capacidadField);
        addFormField(formPanel, "Hora de salida (HH:MM):", horaSalidaField);
        addFormField(formPanel, "Hora de llegada (HH:MM):", horaLlegadaField);
        addFormField(formPanel, "Número de vagones:", vagonesField);
        addFormField(formPanel, "Ruta asignada:", rutaField);

        JButton submitButton = new JButton("REGISTRAR TREN");
        submitButton.setBackground(new Color(76, 175, 80));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(this::registrarTren);

        formPanel.add(Box.createVerticalStrut(30));
        formPanel.add(submitButton);
        add(formPanel, BorderLayout.CENTER);
    }

    private void addFormField(JPanel panel, String label, JTextField field) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        fieldPanel.setBackground(Color.WHITE);
        
        JLabel lbl = new JLabel(label);
        lbl.setPreferredSize(new Dimension(200, 25));
        
        fieldPanel.add(lbl);
        fieldPanel.add(field);
        panel.add(fieldPanel);
        panel.add(Box.createVerticalStrut(15));
    }

    private void registrarTren(ActionEvent e) {
        if (!validarCampos()) {
            JOptionPane.showMessageDialog(
                frame, 
                "Por favor complete todos los campos correctamente", 
                "Error de validación", 
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        try {
            Tren nuevoTren = new Tren(
                marcaField.getText().trim(),
                idField.getText().trim(),
                Integer.parseInt(capacidadField.getText().trim()),
                horaSalidaField.getText().trim(),
                horaLlegadaField.getText().trim(),
                Integer.parseInt(vagonesField.getText().trim()),
                rutaField.getText().trim()
            );

            // Verificar si el ID ya existe
            if (GestorTrenes.getInstance().buscarTren(nuevoTren.getIdentificador()) != null) {
                JOptionPane.showMessageDialog(
                    frame, 
                    "El identificador ya está en uso", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            GestorTrenes.getInstance().agregarTren(nuevoTren);
            mostrarConfirmacion();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                frame, 
                "Capacidad y número de vagones deben ser valores numéricos", 
                "Error de formato", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private boolean validarCampos() {
        return !marcaField.getText().trim().isEmpty() &&
               !idField.getText().trim().isEmpty() &&
               !capacidadField.getText().trim().isEmpty() &&
               !horaSalidaField.getText().trim().isEmpty() &&
               !horaLlegadaField.getText().trim().isEmpty() &&
               !vagonesField.getText().trim().isEmpty() &&
               !rutaField.getText().trim().isEmpty();
    }

    private void mostrarConfirmacion() {
        JPanel confirmPanel = new JPanel(new BorderLayout());
        confirmPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        confirmPanel.setBackground(Color.WHITE);

        JLabel iconLabel = new JLabel(new ImageIcon("success.png")); // Reemplaza con tu icono
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel messageLabel = new JLabel(
            "<html><center><h2>¡Tren registrado exitosamente!</h2>" +
            "<p>Los datos del tren han sido guardados en el sistema.</p></center></html>", 
            SwingConstants.CENTER
        );

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        
        JButton newButton = new JButton("Agregar otro tren");
        newButton.setBackground(new Color(205, 163, 74));
        newButton.setForeground(Color.WHITE);
        newButton.addActionListener(e -> {
            frame.setContentPane(new AgregarTrenPanel(frame));
            frame.revalidate();
        });

        JButton listButton = new JButton("Ver lista de trenes");
        listButton.setBackground(new Color(33, 150, 243));
        listButton.setForeground(Color.WHITE);
        listButton.addActionListener(e -> {
            frame.setContentPane(new ListaTrenesPanel(frame));
            frame.revalidate();
        });

        buttonPanel.add(newButton);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(listButton);

        confirmPanel.add(iconLabel, BorderLayout.NORTH);
        confirmPanel.add(messageLabel, BorderLayout.CENTER);
        confirmPanel.add(buttonPanel, BorderLayout.SOUTH);

        removeAll();
        add(confirmPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
