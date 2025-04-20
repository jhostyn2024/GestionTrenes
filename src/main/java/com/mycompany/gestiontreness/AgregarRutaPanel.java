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

public class AgregarRutaPanel extends JPanel {
    private final JFrame frame;
    private final JTextField txtIdRuta, txtEstacionOrigen, txtEstacionDestino, txtDistancia, txtEstado;
    private final Color GOLD_COLOR = new Color(198, 168, 77); // Color #C6A84D

    public AgregarRutaPanel(JFrame frame) {
        this.frame = frame;
        this.txtIdRuta = new JTextField(20);
        this.txtEstacionOrigen = new JTextField(20);
        this.txtEstacionDestino = new JTextField(20);
        this.txtDistancia = new JTextField(20);
        this.txtEstado = new JTextField(20);
        
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
        backButton.setBackground(GOLD_COLOR);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new GestionRutasPanel(frame));
            frame.revalidate();
        });
        
        JLabel title = new JLabel("AGREGAR RUTA", SwingConstants.CENTER);
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
        addFormField(formPanel, "ESTACIÓN DE ORIGEN:", txtEstacionOrigen);
        addFormField(formPanel, "ESTACIÓN DESTINO:", txtEstacionDestino);
        addFormField(formPanel, "DISTANCIA (km):", txtDistancia);
        addFormField(formPanel, "ESTADO:", txtEstado);

        JButton btnContinuar = new JButton("CONTINUAR");
        btnContinuar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnContinuar.setBackground(GOLD_COLOR);
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setFont(new Font("Arial", Font.BOLD, 16));
        btnContinuar.setPreferredSize(new Dimension(250, 40));
        btnContinuar.addActionListener(this::guardarRuta);
        
        formPanel.add(Box.createVerticalStrut(30));
        formPanel.add(btnContinuar);

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

    private void guardarRuta(ActionEvent e) {
        String idRuta = txtIdRuta.getText().trim();
        String estacionOrigen = txtEstacionOrigen.getText().trim();
        String estacionDestino = txtEstacionDestino.getText().trim();
        String distanciaStr = txtDistancia.getText().trim();
        String estado = txtEstado.getText().trim();

        // Validación de campos
        if (idRuta.isEmpty() || estacionOrigen.isEmpty() || estacionDestino.isEmpty() || 
            distanciaStr.isEmpty() || estado.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double distancia = Double.parseDouble(distanciaStr);
            
            // Aquí iría la lógica para guardar la ruta
            // Ejemplo: GestorRutas.getInstance().agregarRuta(new Ruta(idRuta, estacionOrigen, ...));
            
            JOptionPane.showMessageDialog(frame, "Ruta guardada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            frame.setContentPane(new GestionRutasPanel(frame));
            frame.revalidate();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "La distancia debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}