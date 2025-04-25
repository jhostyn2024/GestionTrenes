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

public class EditarVagonPanel extends JPanel {
    private final JFrame frame;
    private final Vagon vagonOriginal;
    private final JTextField txtTotalVagones, txtVagonesEquipaje, txtTotalAsientos;
    private final JTextField txtAsientosEstandar, txtAsientosEjecutivo, txtAsientosPremium;

    public EditarVagonPanel(JFrame frame, Vagon vagon) {
        this.frame = frame;
        this.vagonOriginal = vagon;
        
        // Inicializar campos
        this.txtTotalVagones = new JTextField(String.valueOf(vagon.getTotalVagones()), 10);
        this.txtVagonesEquipaje = new JTextField(String.valueOf(vagon.getVagonesConEquipaje()), 10);
        this.txtTotalAsientos = new JTextField(String.valueOf(vagon.getTotalAsientos()), 10);
        this.txtAsientosEstandar = new JTextField(String.valueOf(vagon.getAsientosEstandar()), 10);
        this.txtAsientosEjecutivo = new JTextField(String.valueOf(vagon.getAsientosEjecutivo()), 10);
        this.txtAsientosPremium = new JTextField(String.valueOf(vagon.getAsientosPremium()), 10);
        
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        initializeUI();
    }

    private void initializeUI() {
        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(0, 86, 179));
        header.setPreferredSize(new Dimension(800, 80));

        JLabel title = new JLabel("EDITAR VAGÓN", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        JButton backButton = new JButton("← VOLVER");
        backButton.setBackground(new Color(198, 168, 77));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new ModificarVagonPanel(frame));
            frame.revalidate();
        });

        header.add(backButton, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        // Formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        addFormField(formPanel, "Total Vagones:", txtTotalVagones);
        addFormField(formPanel, "Vagones con Equipaje:", txtVagonesEquipaje);
        addFormField(formPanel, "Total Asientos:", txtTotalAsientos);
        addFormField(formPanel, "Asientos Estandar:", txtAsientosEstandar);
        addFormField(formPanel, "Asientos Ejecutivo:", txtAsientosEjecutivo);
        addFormField(formPanel, "Asientos Premium:", txtAsientosPremium);

        JButton btnGuardar = new JButton("GUARDAR CAMBIOS");
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.setBackground(new Color(0, 86, 179));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 16));
        btnGuardar.setPreferredSize(new Dimension(250, 40));
        btnGuardar.addActionListener(this::guardarCambios);
        
        formPanel.add(Box.createVerticalStrut(30));
        formPanel.add(btnGuardar);

        add(new JScrollPane(formPanel), BorderLayout.CENTER);
    }

    private void addFormField(JPanel panel, String label, JTextField textField) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        fieldPanel.setBackground(Color.WHITE);
        
        JLabel lbl = new JLabel(label);
        lbl.setPreferredSize(new Dimension(180, 25));
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        
        textField.setPreferredSize(new Dimension(100, 30));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        fieldPanel.add(lbl);
        fieldPanel.add(textField);
        panel.add(fieldPanel);
        panel.add(Box.createVerticalStrut(10));
    }

    private void guardarCambios(ActionEvent e) {
        try {
            Vagon vagonActualizado = new Vagon(
                Integer.parseInt(txtTotalVagones.getText()),
                Integer.parseInt(txtVagonesEquipaje.getText()),
                Integer.parseInt(txtTotalAsientos.getText()),
                Integer.parseInt(txtAsientosEstandar.getText()),
                Integer.parseInt(txtAsientosEjecutivo.getText()),
                Integer.parseInt(txtAsientosPremium.getText())
            );

            boolean actualizado = GestorVagones.getInstance()
                .actualizarVagon(vagonOriginal.getIdVagon(), vagonActualizado);

            if (actualizado) {
                JOptionPane.showMessageDialog(frame, 
                    "Vagón actualizado correctamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
                frame.setContentPane(new ModificarVagonPanel(frame));
                frame.revalidate();
            } else {
                JOptionPane.showMessageDialog(frame, 
                    "Error al actualizar el vagón", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, 
                "Ingrese valores numéricos válidos", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}