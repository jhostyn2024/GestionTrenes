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
    private JTextField totalVagonesField;
    private JTextField vagonesEquipajeField;
    private JTextField totalAsientosField;
    private JTextField asientosEstandarField;
    private JTextField asientosEjecutivoField;
    private JTextField asientosPremiumField;

    public AgregarVagonPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(244, 244, 244));
        createUI();
    }

    private void createUI() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(Color.WHITE);
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("AGREGAR VAGONES", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(0, 51, 102));
        container.add(title);
        container.add(Box.createVerticalStrut(20));

        // Campos del formulario
        totalVagonesField = createFormField(container, "TOTAL VAGONES:", "Ej: 5");
        vagonesEquipajeField = createFormField(container, "VAGONES CON EQUIPAJE:", "Ej: 2");
        totalAsientosField = createFormField(container, "TOTAL ASIENTOS:", "Ej: 150");
        asientosEstandarField = createFormField(container, "ASIENTOS ESTÁNDAR:", "Ej: 100");
        asientosEjecutivoField = createFormField(container, "ASIENTOS EJECUTIVOS:", "Ej: 30");
        asientosPremiumField = createFormField(container, "ASIENTOS PREMIUM:", "Ej: 20");

        JButton continuarBtn = new JButton("CONTINUAR");
        continuarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        continuarBtn.setBackground(new Color(0, 51, 102));
        continuarBtn.setForeground(Color.WHITE);
        continuarBtn.setFont(new Font("Arial", Font.BOLD, 16));
        continuarBtn.addActionListener(this::guardarVagon);
        
        container.add(Box.createVerticalStrut(30));
        container.add(continuarBtn);

        add(new JScrollPane(container), BorderLayout.CENTER);
    }

    private JTextField createFormField(JPanel container, String label, String placeholder) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel.setBackground(Color.WHITE);
        panel.setMaximumSize(new Dimension(500, 50));

        JLabel lbl = new JLabel(label);
        lbl.setPreferredSize(new Dimension(200, 25));

        JTextField field = new JTextField(15);
        field.setText(placeholder);
        field.setForeground(Color.GRAY);
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (field.getText().isEmpty()) {
                    field.setForeground(Color.GRAY);
                    field.setText(placeholder);
                }
            }
        });

        panel.add(lbl);
        panel.add(field);
        container.add(panel);
        container.add(Box.createVerticalStrut(10));

        return field;
    }

    private void guardarVagon(ActionEvent e) {
        if (!validarCampos()) return;

        try {
            Vagon nuevoVagon = new Vagon(
                Integer.parseInt(totalVagonesField.getText()),
                Integer.parseInt(vagonesEquipajeField.getText()),
                Integer.parseInt(totalAsientosField.getText()),
                Integer.parseInt(asientosEstandarField.getText()),
                Integer.parseInt(asientosEjecutivoField.getText()),
                Integer.parseInt(asientosPremiumField.getText())
            );

            GestorVagones.getInstance().agregarVagon(nuevoVagon);
            mostrarConfirmacion();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Error en los datos ingresados", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validarCampos() {
        try {
            int totalV = Integer.parseInt(totalVagonesField.getText());
            int equipaje = Integer.parseInt(vagonesEquipajeField.getText());
            int totalA = Integer.parseInt(totalAsientosField.getText());
            int estandar = Integer.parseInt(asientosEstandarField.getText());
            int ejecutivo = Integer.parseInt(asientosEjecutivoField.getText());
            int premium = Integer.parseInt(asientosPremiumField.getText());

            if (equipaje > totalV) {
                mostrarError("Los vagones con equipaje no pueden ser más que el total");
                return false;
            }

            if (estandar + ejecutivo + premium != totalA) {
                mostrarError("La suma de los asientos no coincide con el total");
                return false;
            }

            return true;
        } catch (NumberFormatException e) {
            mostrarError("Todos los campos deben ser números válidos");
            return false;
        }
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(frame, mensaje, "Error de validación", JOptionPane.ERROR_MESSAGE);
    }

    private void mostrarConfirmacion() {
        JPanel confirmPanel = new JPanel(new BorderLayout());
        confirmPanel.setBackground(Color.WHITE);
        confirmPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JLabel iconLabel = new JLabel(new ImageIcon("success.png"));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel messageLabel = new JLabel(
            "<html><center><h2>¡Vagones registrados exitosamente!</h2>" +
            "<p>La información ha sido guardada en el sistema.</p>" +
            "<p>Puede verificar la disponibilidad en el módulo correspondiente.</p></center></html>", 
            SwingConstants.CENTER
        );

        JButton okButton = new JButton("Aceptar");
        okButton.setBackground(new Color(0, 51, 102));
        okButton.setForeground(Color.WHITE);
        okButton.addActionListener(e -> {
            frame.setContentPane(new GestionVagonesPanel(frame));
            frame.revalidate();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(okButton);

        confirmPanel.add(iconLabel, BorderLayout.NORTH);
        confirmPanel.add(messageLabel, BorderLayout.CENTER);
        confirmPanel.add(buttonPanel, BorderLayout.SOUTH);

        removeAll();
        add(confirmPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}