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
import java.util.List;

public class DisponibilidadVagonesPanel extends JPanel {
    private JFrame frame;

    public DisponibilidadVagonesPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(244, 244, 244));
        createUI();
    }

    private void createUI() {
        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(26, 38, 116));
        header.setPreferredSize(new Dimension(800, 80));

        JLabel title = new JLabel("DISPONIBILIDAD DE VAGONES", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        JButton backButton = new JButton("Volver");
        backButton.setBackground(new Color(205, 163, 74));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new GestionVagonesPanel(frame));
            frame.revalidate();
        });

        header.add(backButton, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        // Cuerpo
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(Color.WHITE);

        List<Vagon> vagones = GestorVagones.getInstance().getVagones();

        if (vagones.isEmpty()) {
            contentPanel.add(new JLabel("No hay datos de vagones registrados", SwingConstants.CENTER));
        } else {
            // Tabla de disponibilidad
            String[] columnNames = {"Total Vagones", "Vagones con Equipaje", "Total Asientos", 
                                  "Estándar", "Ejecutivo", "Premium"};
            Object[][] data = new Object[vagones.size()][6];

            for (int i = 0; i < vagones.size(); i++) {
                Vagon v = vagones.get(i);
                data[i][0] = v.getTotalVagones();
                data[i][1] = v.getVagonesConEquipaje();
                data[i][2] = v.getTotalAsientos();
                data[i][3] = v.getAsientosEstandar();
                data[i][4] = v.getAsientosEjecutivo();
                data[i][5] = v.getAsientosPremium();
            }

            JTable table = new JTable(data, columnNames);
            table.setFillsViewportHeight(true);
            JScrollPane scrollPane = new JScrollPane(table);
            contentPanel.add(scrollPane);
        }

        add(new JScrollPane(contentPanel), BorderLayout.CENTER);
    }
}