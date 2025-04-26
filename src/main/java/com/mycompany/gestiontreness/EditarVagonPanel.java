/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class EditarVagonPanel extends JPanel {
    private JFrame frame;
    private Vagon vagonOriginal;
    
    private JTextField txtTotalVagones;
    private JTextField txtVagonesEquipaje;
    private JTextField txtTotalAsientos;
    private JTextField txtAsientosEstandar;
    private JTextField txtAsientosEjecutivo;
    private JTextField txtAsientosPremium;

    public EditarVagonPanel(JFrame frame, Vagon vagon) {
        this.frame = frame;
        this.vagonOriginal = vagon;
        setLayout(new BorderLayout());
        setBackground(new Color(244, 244, 244));
        initializeUI();
    }

    private void initializeUI() {
        createHeader();
        createFormPanel();
    }

    private void createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(26, 38, 116));
        header.setPreferredSize(new Dimension(800, 80));

        JLabel title = new JLabel("EDITAR VAGÓN", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        JButton backButton = new JButton("Cancelar");
        backButton.setBackground(new Color(205, 163, 74));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new ModificarVagonPanel(frame));
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

        txtTotalVagones = createFormField(formPanel, "Total Vagones:", String.valueOf(vagonOriginal.getTotalVagones()));
        txtVagonesEquipaje = createFormField(formPanel, "Vagones con Equipaje:", String.valueOf(vagonOriginal.getVagonesConEquipaje()));
        txtTotalAsientos = createFormField(formPanel, "Total Asientos:", String.valueOf(vagonOriginal.getTotalAsientos()));
        txtAsientosEstandar = createFormField(formPanel, "Asientos Estándar:", String.valueOf(vagonOriginal.getAsientosEstandar()));
        txtAsientosEjecutivo = createFormField(formPanel, "Asientos Ejecutivos:", String.valueOf(vagonOriginal.getAsientosEjecutivo()));
        txtAsientosPremium = createFormField(formPanel, "Asientos Premium:", String.valueOf(vagonOriginal.getAsientosPremium()));

        JButton btnGuardar = new JButton("GUARDAR CAMBIOS");
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.setBackground(new Color(39, 174, 96));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 16));
        btnGuardar.addActionListener(this::guardarCambios);

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

    private void guardarCambios(ActionEvent e) {
        if (!validarCampos()) {
            return;
        }

        try {
            // Actualizar los campos de vagonOriginal directamente
            vagonOriginal.setTotalVagones(Integer.parseInt(txtTotalVagones.getText()));
            vagonOriginal.setVagonesConEquipaje(Integer.parseInt(txtVagonesEquipaje.getText()));
            vagonOriginal.setTotalAsientos(Integer.parseInt(txtTotalAsientos.getText()));
            vagonOriginal.setAsientosEstandar(Integer.parseInt(txtAsientosEstandar.getText()));
            vagonOriginal.setAsientosEjecutivo(Integer.parseInt(txtAsientosEjecutivo.getText()));
            vagonOriginal.setAsientosPremium(Integer.parseInt(txtAsientosPremium.getText()));

            // Depuración
            System.out.println("Vagón actualizado - ID: " + vagonOriginal.getIdVagon());
            System.out.println("Nuevos valores: Total Vagones=" + vagonOriginal.getTotalVagones() +
                              ", Asientos Totales=" + vagonOriginal.getTotalAsientos());

            // No necesitamos actualizar la lista, ya que modificamos el objeto existente
            JOptionPane.showMessageDialog(frame, 
                "Vagón actualizado correctamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
                
            frame.setContentPane(new ModificarVagonPanel(frame));
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