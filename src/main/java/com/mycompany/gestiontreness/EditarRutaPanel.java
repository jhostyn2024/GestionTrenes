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

public class EditarRutaPanel extends JPanel {
    private final JFrame frame;
    private final Ruta rutaOriginal;
    private final JTextField txtIdRuta, txtEstacionOrigen, txtEstacionDestino, txtDistancia, txtEstado;

    public EditarRutaPanel(JFrame frame, Ruta ruta) {
        this.frame = frame;
        this.rutaOriginal = ruta;
        this.txtIdRuta = new JTextField(ruta.getIdRuta(), 20);
        this.txtEstacionOrigen = new JTextField(ruta.getEstacionOrigen(), 20);
        this.txtEstacionDestino = new JTextField(ruta.getEstacionDestino(), 20);
        this.txtDistancia = new JTextField(String.valueOf(ruta.getDistancia()), 20);
        this.txtEstado = new JTextField(ruta.getEstado(), 20);
        
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUI();
    }

    private void createUI() {
        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(0, 86, 179));
        header.setPreferredSize(new Dimension(800, 80));
        
        JButton backButton = new JButton("← VOLVER");
        backButton.setBackground(new Color(198, 168, 77));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new ModificarEliminarRutaPanel(frame));
            frame.revalidate();
        });
        
        JLabel title = new JLabel("EDITAR RUTA", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        
        header.add(backButton, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        // Formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));
        formPanel.setBackground(Color.WHITE);

        addFormField(formPanel, "ID RUTA:", txtIdRuta);
        addFormField(formPanel, "ESTACIÓN ORIGEN:", txtEstacionOrigen);
        addFormField(formPanel, "ESTACIÓN DESTINO:", txtEstacionDestino);
        addFormField(formPanel, "DISTANCIA (km):", txtDistancia);
        addFormField(formPanel, "ESTADO:", txtEstado);

        JButton btnGuardar = new JButton("GUARDAR CAMBIOS");
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.setBackground(new Color(198, 168, 77));
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
        lbl.setPreferredSize(new Dimension(200, 25));
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        
        textField.setPreferredSize(new Dimension(300, 30));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        fieldPanel.add(lbl);
        fieldPanel.add(textField);
        panel.add(fieldPanel);
        panel.add(Box.createVerticalStrut(15));
    }

    private void guardarCambios(ActionEvent e) {
        String idRuta = txtIdRuta.getText().trim();
        String estacionOrigen = txtEstacionOrigen.getText().trim();
        String estacionDestino = txtEstacionDestino.getText().trim();
        String distanciaStr = txtDistancia.getText().trim();
        String estado = txtEstado.getText().trim();

        if (idRuta.isEmpty() || estacionOrigen.isEmpty() || estacionDestino.isEmpty() || 
            distanciaStr.isEmpty() || estado.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double distancia = Double.parseDouble(distanciaStr);
            
            // Actualizar la ruta
            rutaOriginal.setIdRuta(idRuta);
            rutaOriginal.setEstacionOrigen(estacionOrigen);
            rutaOriginal.setEstacionDestino(estacionDestino);
            rutaOriginal.setDistancia(distancia);
            rutaOriginal.setEstado(estado);

            JOptionPane.showMessageDialog(frame, "Ruta actualizada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            frame.setContentPane(new ModificarEliminarRutaPanel(frame));
            frame.revalidate();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "La distancia debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
