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

public class RutaMasCortaPanel extends JPanel {
    private final JFrame frame;
    private final JComboBox<String> cbOrigen, cbDestino;
    private final JTextArea taResultado;

    public RutaMasCortaPanel(JFrame frame) {
        this.frame = frame;
        
        // Crear combobox con estaciones disponibles
        String[] estaciones = {"Lima", "Arequipa", "Cusco", "Trujillo", "Chiclayo"};
        this.cbOrigen = new JComboBox<>(estaciones);
        this.cbDestino = new JComboBox<>(estaciones);
        this.taResultado = new JTextArea(5, 30);
        
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
            frame.setContentPane(new GestionRutasPanel(frame));
            frame.revalidate();
        });
        
        JLabel title = new JLabel("RUTA MÁS CORTA", SwingConstants.CENTER);
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

        addFormField(formPanel, "Estación Origen:", cbOrigen);
        addFormField(formPanel, "Estación Destino:", cbDestino);

        taResultado.setEditable(false);
        taResultado.setFont(new Font("Arial", Font.PLAIN, 14));
        taResultado.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(taResultado);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(scrollPane);

        JButton btnCalcular = new JButton("CALCULAR RUTA MÁS CORTA");
        btnCalcular.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCalcular.setBackground(new Color(198, 168, 77));
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setFont(new Font("Arial", Font.BOLD, 16));
        btnCalcular.setPreferredSize(new Dimension(300, 40));
        btnCalcular.addActionListener(this::calcularRutaMasCorta);
        
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(btnCalcular);

        add(new JScrollPane(formPanel), BorderLayout.CENTER);
    }

    private void addFormField(JPanel panel, String label, JComponent component) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        fieldPanel.setBackground(Color.WHITE);
        
        JLabel lbl = new JLabel(label);
        lbl.setPreferredSize(new Dimension(200, 25));
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        
        component.setPreferredSize(new Dimension(300, 30));
        component.setFont(new Font("Arial", Font.PLAIN, 14));
        
        fieldPanel.add(lbl);
        fieldPanel.add(component);
        panel.add(fieldPanel);
        panel.add(Box.createVerticalStrut(15));
    }

    private void calcularRutaMasCorta(ActionEvent e) {
        String origen = (String) cbOrigen.getSelectedItem();
        String destino = (String) cbDestino.getSelectedItem();
        
        if (origen.equals(destino)) {
            JOptionPane.showMessageDialog(frame, 
                "La estación de origen y destino deben ser diferentes", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Aquí iría la lógica para calcular la ruta más corta
        // Esto es un ejemplo simplificado
        String resultado = "Ruta más corta de " + origen + " a " + destino + ":\n";
        resultado += "1. " + origen + " → " + destino + "\n";
        resultado += "Distancia total: 450 km\n";
        resultado += "Tiempo estimado: 6 horas";
        
        taResultado.setText(resultado);
    }
}