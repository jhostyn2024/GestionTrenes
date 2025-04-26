/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EditarRutaPanel extends JPanel {
    private JFrame frame;
    private Ruta rutaOriginal;
    private JTextField txtEstacionOrigen;
    private JTextField txtEstacionDestino;
    private JTextField txtDistancia;
    private JComboBox<String> comboEstado;

    public EditarRutaPanel(JFrame frame, Ruta ruta) {
        this.frame = frame;
        this.rutaOriginal = ruta;
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

        JLabel title = new JLabel("EDITAR RUTA", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        JButton backButton = new JButton("Cancelar");
        backButton.setBackground(new Color(205, 163, 74));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new ModificarEliminarRutaPanel(frame));
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

        txtEstacionOrigen = createFormField(formPanel, "Estación Origen:", rutaOriginal.getEstacionOrigen());
        txtEstacionDestino = createFormField(formPanel, "Estación Destino:", rutaOriginal.getEstacionDestino());
        txtDistancia = createFormField(formPanel, "Distancia (km):", String.valueOf(rutaOriginal.getDistancia()));
        comboEstado = createComboBox(formPanel, "Estado:", new String[]{"Activa", "Inactiva"}, rutaOriginal.getEstado());

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

    private JComboBox<String> createComboBox(JPanel panel, String label, String[] options, String selected) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        fieldPanel.setBackground(Color.WHITE);
        fieldPanel.setMaximumSize(new Dimension(500, 50));

        JLabel lbl = new JLabel(label);
        lbl.setPreferredSize(new Dimension(180, 25));

        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        comboBox.setSelectedItem(selected);

        fieldPanel.add(lbl);
        fieldPanel.add(comboBox);
        panel.add(fieldPanel);
        panel.add(Box.createVerticalStrut(10));

        return comboBox;
    }

    private void guardarCambios(ActionEvent e) {
        if (!validarCampos()) {
            return;
        }

        try {
            String estacionOrigen = txtEstacionOrigen.getText().trim();
            String estacionDestino = txtEstacionDestino.getText().trim();
            double distancia = Double.parseDouble(txtDistancia.getText().trim());
            String estado = (String) comboEstado.getSelectedItem();

            GestorRutas.getInstance().modificarRuta(rutaOriginal.getIdRuta(), estacionOrigen, 
                                                  estacionDestino, distancia, estado);

            System.out.println("Ruta actualizada - ID: " + rutaOriginal.getIdRuta());

            JOptionPane.showMessageDialog(frame, 
                "Ruta actualizada correctamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
                
            frame.setContentPane(new ModificarEliminarRutaPanel(frame));
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
                "Error al guardar los cambios", 
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
