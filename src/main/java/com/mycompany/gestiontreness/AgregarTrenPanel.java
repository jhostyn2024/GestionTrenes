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
    private JPanel formPanel;
    private JTextField marcaField, idField, capacidadField, horaSalidaField, horaLlegadaField, vagonesField, rutaField;

    public AgregarTrenPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        createHeader();
        createFormPanel();
    }

    private void createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(26, 38, 116));
        
        JLabel title = new JLabel("AGREGAR TREN", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        
        JButton backButton = new JButton("Volver");
        backButton.setBackground(new Color(205, 163, 74));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> frame.setContentPane(new GestionTrenesPanel(frame)));
        
        header.add(backButton, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);
    }

    private void createFormPanel() {
        formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        marcaField = new JTextField(15);
        idField = new JTextField(15);
        capacidadField = new JTextField(15);
        horaSalidaField = new JTextField(15);
        horaLlegadaField = new JTextField(15);
        vagonesField = new JTextField(15);
        rutaField = new JTextField(15);

        addField(formPanel, "MARCA:", marcaField);
        addField(formPanel, "IDENTIFICADOR:", idField);
        addField(formPanel, "CAPACIDAD:", capacidadField);
        addField(formPanel, "HORA SALIDA:", horaSalidaField);
        addField(formPanel, "HORA LLEGADA:", horaLlegadaField);
        addField(formPanel, "VAGONES:", vagonesField);
        addField(formPanel, "RUTA:", rutaField);

        JButton continuar = new JButton("AGREGAR TREN");
        continuar.setAlignmentX(Component.CENTER_ALIGNMENT);
        continuar.setBackground(new Color(205, 163, 74));
        continuar.setForeground(Color.WHITE);
        continuar.setFocusPainted(false);
        continuar.addActionListener(this::agregarTren);
        
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(continuar);
        add(formPanel, BorderLayout.CENTER);
    }

    private void agregarTren(ActionEvent e) {
        if (!validateFields()) {
            JOptionPane.showMessageDialog(frame, "Complete todos los campos correctamente", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Tren nuevoTren = new Tren(
                marcaField.getText(),
                idField.getText(),
                Integer.parseInt(capacidadField.getText()),
                horaSalidaField.getText(),
                horaLlegadaField.getText(),
                Integer.parseInt(vagonesField.getText()),
                rutaField.getText()
            );
            
            GestorTrenes.getInstance().agregarTren(nuevoTren);
            showConfirmationPanel();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Capacidad y Vagones deben ser números", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateFields() {
        return !marcaField.getText().isEmpty() &&
               !idField.getText().isEmpty() &&
               !capacidadField.getText().isEmpty() &&
               !horaSalidaField.getText().isEmpty() &&
               !horaLlegadaField.getText().isEmpty() &&
               !vagonesField.getText().isEmpty() &&
               !rutaField.getText().isEmpty();
    }

    private void showConfirmationPanel() {
        removeAll();
        
        JPanel confirmationPanel = new JPanel();
        confirmationPanel.setLayout(new BoxLayout(confirmationPanel, BoxLayout.Y_AXIS));
        confirmationPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        confirmationPanel.setBackground(Color.WHITE);

        JLabel iconLabel = new JLabel(new ImageIcon("success-icon.png")); // Cambia por tu icono
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel confirmationLabel = new JLabel("¡Tren agregado correctamente!");
        confirmationLabel.setFont(new Font("Arial", Font.BOLD, 20));
        confirmationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton okButton = new JButton("Continuar");
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        okButton.setBackground(new Color(205, 163, 74));
        okButton.setForeground(Color.WHITE);
        okButton.setFocusPainted(false);
        okButton.addActionListener(e -> {
            frame.setContentPane(new GestionTrenesPanel(frame));
            frame.revalidate();
        });
        
        confirmationPanel.add(iconLabel);
        confirmationPanel.add(Box.createVerticalStrut(20));
        confirmationPanel.add(confirmationLabel);
        confirmationPanel.add(Box.createVerticalStrut(30));
        confirmationPanel.add(okButton);
        
        add(confirmationPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void addField(JPanel panel, String labelText, JComponent input) {
        JPanel field = new JPanel(new FlowLayout(FlowLayout.LEFT));
        field.setBackground(Color.WHITE);
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(150, 25));
        field.add(label);
        field.add(input);
        panel.add(field);
        panel.add(Box.createVerticalStrut(10));
    }
}

