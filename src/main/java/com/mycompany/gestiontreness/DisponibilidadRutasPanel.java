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

public class DisponibilidadRutasPanel extends JPanel {
    private final JFrame frame;

    public DisponibilidadRutasPanel(JFrame frame) {
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
        
        JLabel title = new JLabel("DISPONIBILIDAD DE RUTAS", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        header.add(title);
        
        add(header, BorderLayout.NORTH);

        // Main content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(Color.WHITE);

        List<Ruta> rutas = GestorRutas.getInstance().getRutas();
        
        if (rutas.isEmpty()) {
            contentPanel.add(new JLabel("No hay rutas registradas", SwingConstants.CENTER));
        } else {
            // Cabecera de la tabla
            JPanel headerPanel = new JPanel(new GridLayout(1, 5));
            headerPanel.setBackground(new Color(0, 86, 179));
            addTableHeader(headerPanel, "ID RUTA");
            addTableHeader(headerPanel, "ORIGEN");
            addTableHeader(headerPanel, "DESTINO");
            addTableHeader(headerPanel, "DISTANCIA (km)");
            addTableHeader(headerPanel, "ESTADO");
            contentPanel.add(headerPanel);

            // Filas de datos
            for (Ruta ruta : rutas) {
                JPanel dataRow = new JPanel(new GridLayout(1, 5));
                dataRow.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
                addTableCell(dataRow, ruta.getIdRuta());
                addTableCell(dataRow, ruta.getEstacionOrigen());
                addTableCell(dataRow, ruta.getEstacionDestino());
                addTableCell(dataRow, String.valueOf(ruta.getDistancia()));
                addTableCell(dataRow, ruta.getEstado());
                contentPanel.add(dataRow);
            }
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Footer
        JPanel footer = new JPanel();
        footer.setBackground(new Color(240, 240, 240));
        
        JButton backButton = new JButton("VOLVER");
        backButton.setBackground(new Color(198, 168, 77));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new GestionRutasPanel(frame));
            frame.revalidate();
        });
        
        footer.add(backButton);
        add(footer, BorderLayout.SOUTH);
    }

    private void addTableHeader(JPanel panel, String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        panel.add(label);
    }

    private void addTableCell(JPanel panel, String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 13));
        label.setBorder(BorderFactory.createEmptyBorder(8, 5, 8, 5));
        panel.add(label);
    }
}