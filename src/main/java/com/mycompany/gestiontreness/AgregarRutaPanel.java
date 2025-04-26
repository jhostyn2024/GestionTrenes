/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AgregarRutaPanel extends JPanel {
    private JFrame frame;
    private JTextField txtEstacionOrigen;
    private JTextField txtEstacionDestino;
    private JTextField txtDistancia;
    private JComboBox<String> comboEstado;

    public AgregarRutaPanel(JFrame frame) {
        this.frame = frame;
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

        JLabel title = new JLabel("AGREGAR RUTA", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        JButton backButton = new JButton("Volver");
        backButton.setBackground(new Color(205, 163, 74));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new GestionRutasPanel(frame));
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

        txtEstacionOrigen = createFormField(formPanel, "Estación Origen:", "");
        txtEstacionDestino = createFormField(formPanel, "Estación Destino:", "");
        txtDistancia = createFormField(formPanel, "Distancia (km):", "");
        comboEstado = createComboBox(formPanel, "Estado:", new String[]{"Activa", "Inactiva"});

        JButton btnGuardar = new JButton("GUARDAR RUTA");
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.setBackground(new Color(39, 174, 96));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 16));
        btnGuardar.addActionListener(this::guardarRuta);

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

    private JComboBox<String> createComboBox(JPanel panel, String label, String[] options) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        fieldPanel.setBackground(Color.WHITE);
        fieldPanel.setMaximumSize(new Dimension(500, 50));

        JLabel lbl = new JLabel(label);
        lbl.setPreferredSize(new Dimension(180, 25));

        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));

        fieldPanel.add(lbl);
        fieldPanel.add(comboBox);
        panel.add(fieldPanel);
        panel.add(Box.createVerticalStrut(10));

        return comboBox;
    }

    private void guardarRuta(ActionEvent e) {
        if (!validarCampos()) {
            return;
        }

        try {
            String estacionOrigen = txtEstacionOrigen.getText().trim();
            String estacionDestino = txtEstacionDestino.getText().trim();
            double distancia = Double.parseDouble(txtDistancia.getText().trim());
            String estado = (String) comboEstado.getSelectedItem();

            Ruta nuevaRuta = new Ruta(estacionOrigen, estacionDestino, distancia, estado);
            GestorRutas.getInstance().agregarRuta(nuevaRuta);

            System.out.println("Ruta creada - ID: " + nuevaRuta.getIdRuta() +
                              ", Origen: " + estacionOrigen + ", Destino: " + estacionDestino +
                              ", Distancia: " + distancia + ", Estado: " + estado);

            JOptionPane.showMessageDialog(frame, 
                "Ruta guardada correctamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);

            frame.setContentPane(new GestionRutasPanel(frame));
            frame.revalidate();
        } catch (NumberFormatException ex) {
            System.out.println("Error de formato en distancia: " + ex.getMessage());
            JOptionPane.showMessageDialog(frame, 
                "La distancia debe ser un número válido", 
                "Error de validación", 
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            System.out.println("Error al guardar ruta: " + ex.getMessage());
            JOptionPane.showMessageDialog(frame, 
                "Error al guardar la ruta", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validarCampos() {
        if (txtEstacionOrigen.getText().trim().isEmpty() ||
            txtEstacionDestino.getText().trim().isEmpty() ||
            txtDistancia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, 
                "Todos los campos son obligatorios", 
                "Error de validación", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Double.parseDouble(txtDistancia.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, 
                "La distancia debe ser un número válido", 
                "Error de validación", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}