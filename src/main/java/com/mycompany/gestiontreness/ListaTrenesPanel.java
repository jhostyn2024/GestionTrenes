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

public class ListaTrenesPanel extends JPanel {
    private JFrame frame;

    public ListaTrenesPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUI();
    }

    private void createUI() {
        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(0, 86, 179));
        header.setPreferredSize(new Dimension(800, 80));
        
        JLabel title = new JLabel("LISTADO COMPLETO DE TRENES", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        header.add(title);
        
        add(header, BorderLayout.NORTH);

        // Main content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(Color.WHITE);

        List<Tren> trenes = GestorTrenes.getInstance().getTrenes();
        if (trenes.isEmpty()) {
            contentPanel.add(new JLabel("No hay trenes registrados", SwingConstants.CENTER));
        } else {
            // Table header
            JPanel headerPanel = new JPanel(new GridLayout(1, 7));
            headerPanel.setBackground(new Color(220, 220, 220));
            
            addHeaderCell(headerPanel, "Marca");
            addHeaderCell(headerPanel, "Identificador");
            addHeaderCell(headerPanel, "Capacidad");
            addHeaderCell(headerPanel, "H. Salida");
            addHeaderCell(headerPanel, "H. Llegada");
            addHeaderCell(headerPanel, "Vagones");
            addHeaderCell(headerPanel, "Ruta");
            
            contentPanel.add(headerPanel);
            contentPanel.add(Box.createVerticalStrut(5));

            // Table rows
            for (Tren tren : trenes) {
                JPanel rowPanel = new JPanel(new GridLayout(1, 7));
                rowPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
                
                addDataCell(rowPanel, tren.getMarca());
                addDataCell(rowPanel, tren.getIdentificador());
                addDataCell(rowPanel, String.valueOf(tren.getCapacidad()));
                addDataCell(rowPanel, tren.getHoraSalida());
                addDataCell(rowPanel, tren.getHoraLlegada());
                addDataCell(rowPanel, String.valueOf(tren.getVagones()));
                addDataCell(rowPanel, tren.getRuta());
                
                contentPanel.add(rowPanel);
                contentPanel.add(Box.createVerticalStrut(5));
            }
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        // Footer
        JPanel footer = new JPanel();
        footer.setBackground(new Color(240, 240, 240));
        JButton backButton = new JButton("Volver");
        backButton.setBackground(new Color(205, 163, 74));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new GestionTrenesPanel(frame));
            frame.revalidate();
        });
        footer.add(backButton);
        add(footer, BorderLayout.SOUTH);
    }

    private void addHeaderCell(JPanel panel, String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        panel.add(label);
    }

    private void addDataCell(JPanel panel, String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 13));
        label.setBorder(BorderFactory.createEmptyBorder(8, 5, 8, 5));
        panel.add(label);
    }
}