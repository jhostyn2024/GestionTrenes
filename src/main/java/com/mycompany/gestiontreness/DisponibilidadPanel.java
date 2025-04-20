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

public class DisponibilidadPanel extends JPanel {
    private JFrame frame;

    public DisponibilidadPanel(JFrame frame) {
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
        
        JLabel title = new JLabel("DISPONIBILIDAD DE HORARIOS", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        header.add(title);
        
        add(header, BorderLayout.NORTH);

        // Main content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(Color.WHITE);

        List<Horario> horarios = GestorHorarios.getInstance().getHorarios();
        
        if (horarios.isEmpty()) {
            contentPanel.add(new JLabel("No hay horarios registrados", SwingConstants.CENTER));
        } else {
            // Tabla de horarios
            JPanel headerPanel = new JPanel(new GridLayout(1, 4));
            headerPanel.setBackground(new Color(220, 220, 220));
            
            addHeaderCell(headerPanel, "ID Ruta");
            addHeaderCell(headerPanel, "Hora Salida");
            addHeaderCell(headerPanel, "Hora Llegada");
            addHeaderCell(headerPanel, "Días Semana");
            
            contentPanel.add(headerPanel);
            
            for (Horario horario : horarios) {
                JPanel rowPanel = new JPanel(new GridLayout(1, 4));
                rowPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
                
                addDataCell(rowPanel, horario.getIdRuta());
                addDataCell(rowPanel, horario.getHoraSalida());
                addDataCell(rowPanel, horario.getHoraLlegada());
                addDataCell(rowPanel, horario.getDiasSemana());
                
                contentPanel.add(rowPanel);
            }
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Footer
        JPanel footer = new JPanel();
        footer.setBackground(new Color(240, 240, 240));
        
        JButton backButton = new JButton("Volver");
        backButton.setBackground(new Color(205, 163, 74));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new GestionHorariosPanel(frame));
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