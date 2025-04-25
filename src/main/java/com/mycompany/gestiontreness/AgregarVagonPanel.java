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

public class AgregarVagonPanel extends JPanel {
    private JFrame frame;
    private JTextField txtTotalVagones, txtVagonesEquipaje, txtTotalAsientos, txtAsientosEstandar, txtAsientosEjecutivo, txtAsientosPremium;

    public AgregarVagonPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(244, 244, 244));
        initializeUI();
    }

    private void initializeUI() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(26, 38, 116));
        header.setPreferredSize(new Dimension(800, 80));

        JLabel title = new JLabel("AGREGAR VAGÓN", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        JButton backButton = new JButton("Cancelar");
        backButton.setBackground(new Color(205, 163, 74));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new GestionVagonesPanel(frame));
            frame.revalidate();
        });

        header.add(backButton, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        txtTotalVagones = createFormField(formPanel, "Total Vagones:", "");
        txtVagonesEquipaje = createFormField(formPanel, "Vagones con Equipaje:", "");
        txtTotalAsientos = createFormField(formPanel, "Total Asientos:", "");
        txtAsientosEstandar = createFormField(formPanel, "Asientos Estándar:", "");
        txtAsientosEjecutivo = createFormField(formPanel, "Asientos Ejecutivos:", "");
        txtAsientosPremium = createFormField(formPanel, "Asientos Premium:", "");

        JButton btnGuardar = new JButton("GUARDAR VAGÓN");
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.setBackground(new Color(39, 174, 96));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 16));
        btnGuardar.addActionListener(this::guardarVagon);

        formPanel.add(Box.createVerticalStrut(30));
        formPanel.add(btnGuardar);

        add(new JScrollPane(formPanel), BorderLayout.CENTER);
    }

    private JTextField createFormField(JPanel panel, String label, String value) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        fieldPanel.setBackground(Color.WHITE);
        fieldPanel.setMaximumSize(new Dimension(500, 50));

        JLabel lbl = new JLabel(label);
        lbl.setPreferredSize(new Dimension(180, 25));

        JTextField textField = new JTextField(value, 20);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));

        fieldPanel.add(lbl);
        fieldPanel.add(textField);
        panel.add(fieldPanel);
        panel.add(Box.createVerticalStrut(10));

        return textField;
    }

    private void guardarVagon(ActionEvent e) {
        if (!validarCampos()) {
            return;
        }

        try {
            Vagon nuevoVagon = new Vagon(
                Integer.parseInt(txtTotalVagones.getText()),
                Integer.parseInt(txtVagonesEquipaje.getText()),
                Integer.parseInt(txtTotalAsientos.getText()),
                Integer.parseInt(txtAsientosEstandar.getText()),
                Integer.parseInt(txtAsientosEjecutivo.getText()),
                Integer.parseInt(txtAsientosPremium.getText())
            );

            GestorVagones.getInstance().agregarVagon(nuevoVagon);
            JOptionPane.showMessageDialog(frame, 
                "Vagón agregado correctamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
                
            frame.setContentPane(new GestionVagonesPanel(frame));
            frame.revalidate();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, 
                "Error en los datos ingresados. Asegúrese de ingresar números válidos.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validarCampos() {
        try {
            int totalVagones = Integer.parseInt(txtTotalVagones.getText());
            int vagonesEquipaje = Integer.parseInt(txtVagonesEquipaje.getText());
            int totalAsientos = Integer.parseInt(txtTotalAsientos.getText());
            int asientosEstandar = Integer.parseInt(txtAsientosEstandar.getText());
            int asientosEjecutivo = Integer.parseInt(txtAsientosEjecutivo.getText());
            int asientosPremium = Integer.parseInt(txtAsientosPremium.getText());

            if (vagonesEquipaje > totalVagones) {
                JOptionPane.showMessageDialog(frame, 
                    "Los vagones con equipaje no pueden ser más que el total de vagones", 
                    "Error de validación", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (asientosEstandar + asientosEjecutivo + asientosPremium != totalAsientos) {
                JOptionPane.showMessageDialog(frame, 
                    "La suma de los asientos no coincide con el total", 
                    "Error de validación", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }

            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, 
                "Todos los campos deben contener números válidos", 
                "Error de formato", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}