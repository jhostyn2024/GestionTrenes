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

public class EditarTrenPanel extends JPanel {
    private JFrame frame;
    private Tren trenOriginal;
    private JTextField marcaField, idField, capacidadField, horaSalidaField, horaLlegadaField, vagonesField, rutaField;

    public EditarTrenPanel(JFrame frame, Tren tren) {
        this.frame = frame;
        this.trenOriginal = tren;
        setLayout(new BorderLayout());
        createHeader();
        createFormPanel();
    }

    private void createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(0, 86, 179));
        
        JLabel title = new JLabel("EDITAR TREN: " + trenOriginal.getIdentificador(), SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        
        JButton backButton = new JButton("Cancelar");
        backButton.setBackground(new Color(205, 163, 74));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new ModificarEliminarTrenPanel(frame));
            frame.revalidate();
        });
        
        header.add(backButton, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);
    }

    private void createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        marcaField = new JTextField(trenOriginal.getMarca(), 20);
        idField = new JTextField(trenOriginal.getIdentificador(), 20);
        capacidadField = new JTextField(String.valueOf(trenOriginal.getCapacidad()), 20);
        horaSalidaField = new JTextField(trenOriginal.getHoraSalida(), 20);
        horaLlegadaField = new JTextField(trenOriginal.getHoraLlegada(), 20);
        vagonesField = new JTextField(String.valueOf(trenOriginal.getVagones()), 20);
        rutaField = new JTextField(trenOriginal.getRuta(), 20);

        addFormField(formPanel, "Marca:", marcaField);
        addFormField(formPanel, "Identificador:", idField);
        addFormField(formPanel, "Capacidad:", capacidadField);
        addFormField(formPanel, "Hora Salida:", horaSalidaField);
        addFormField(formPanel, "Hora Llegada:", horaLlegadaField);
        addFormField(formPanel, "Vagones:", vagonesField);
        addFormField(formPanel, "Ruta:", rutaField);

        JButton saveButton = new JButton("Guardar Cambios");
        saveButton.setBackground(new Color(76, 175, 80));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("Arial", Font.BOLD, 16));
        saveButton.addActionListener(this::guardarCambios);

        formPanel.add(Box.createVerticalStrut(30));
        formPanel.add(saveButton);
        add(formPanel, BorderLayout.CENTER);
    }

    private void addFormField(JPanel panel, String label, JTextField field) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fieldPanel.setBackground(Color.WHITE);
        
        JLabel lbl = new JLabel(label);
        lbl.setPreferredSize(new Dimension(120, 25));
        
        fieldPanel.add(lbl);
        fieldPanel.add(field);
        panel.add(fieldPanel);
        panel.add(Box.createVerticalStrut(10));
    }

    private void guardarCambios(ActionEvent e) {
        try {
            Tren trenActualizado = new Tren(
                marcaField.getText(),
                idField.getText(),
                Integer.parseInt(capacidadField.getText()),
                horaSalidaField.getText(),
                horaLlegadaField.getText(),
                Integer.parseInt(vagonesField.getText()),
                rutaField.getText()
            );

            boolean actualizado = GestorTrenes.getInstance()
                .actualizarTren(trenOriginal.getIdentificador(), trenActualizado);

            if (actualizado) {
                JOptionPane.showMessageDialog(
                    frame, 
                    "Tren actualizado correctamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE
                );
                frame.setContentPane(new ModificarEliminarTrenPanel(frame));
                frame.revalidate();
            } else {
                JOptionPane.showMessageDialog(
                    frame, 
                    "Error al actualizar el tren", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                frame, 
                "Capacidad y Vagones deben ser números válidos", 
                "Error de formato", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}