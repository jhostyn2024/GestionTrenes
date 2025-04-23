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

public class AgregarEmpleadoPanel extends JPanel {
    private JFrame frame;
    private JTextField txtId, txtNombre, txtDni, txtCargo, txtTelefono;

    public AgregarEmpleadoPanel(JFrame frame) {
        this.frame = frame;
        this.txtId = new JTextField(generarNuevoId());
        this.txtNombre = new JTextField(20);
        this.txtDni = new JTextField(20);
        this.txtCargo = new JTextField(20);
        this.txtTelefono = new JTextField(20);
        
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUI();
    }

    private String generarNuevoId() {
        return "EMP-" + String.format("%03d", GestorEmpleados.getInstance().getEmpleados().size() + 1);
    }

    private void createUI() {
        // Header (mismo estilo)
        JPanel header = new JPanel();
        header.setBackground(new Color(0, 86, 179));
        header.setPreferredSize(new Dimension(800, 80));
        
        JLabel title = new JLabel("AGREGAR EMPLEADO", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        header.add(title);
        
        add(header, BorderLayout.NORTH);

        // Formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));
        formPanel.setBackground(Color.WHITE);

        addFormField(formPanel, "ID Empleado:", txtId);
        addFormField(formPanel, "Nombre Completo:", txtNombre);
        addFormField(formPanel, "DNI:", txtDni);
        addFormField(formPanel, "Cargo:", txtCargo);
        addFormField(formPanel, "Teléfono:", txtTelefono);

        JButton btnGuardar = new JButton("GUARDAR");
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.setBackground(new Color(198, 168, 77));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 16));
        btnGuardar.setPreferredSize(new Dimension(200, 40));
        btnGuardar.addActionListener(this::guardarEmpleado);
        
        formPanel.add(Box.createVerticalStrut(30));
        formPanel.add(btnGuardar);

        add(new JScrollPane(formPanel), BorderLayout.CENTER);
    }

    private void addFormField(JPanel panel, String label, JTextField textField) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        fieldPanel.setBackground(Color.WHITE);
        
        JLabel lbl = new JLabel(label);
        lbl.setPreferredSize(new Dimension(150, 25));
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        
        textField.setPreferredSize(new Dimension(300, 30));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        fieldPanel.add(lbl);
        fieldPanel.add(textField);
        panel.add(fieldPanel);
        panel.add(Box.createVerticalStrut(15));
    }

    private void guardarEmpleado(ActionEvent e) {
        // Validación básica
        if (txtNombre.getText().trim().isEmpty() || txtDni.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Nombre y DNI son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear y guardar empleado
        Empleado nuevo = new Empleado(
            txtId.getText(),
            txtNombre.getText(),
            txtDni.getText(),
            txtCargo.getText(),
            txtTelefono.getText()
        );
        
        GestorEmpleados.getInstance().agregarEmpleado(nuevo);
        
        JOptionPane.showMessageDialog(frame, "Empleado agregado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        frame.setContentPane(new GestionEmpleadosPanel(frame));
        frame.revalidate();
    }
}