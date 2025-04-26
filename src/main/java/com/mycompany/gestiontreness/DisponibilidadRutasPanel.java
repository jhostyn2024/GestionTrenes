/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class DisponibilidadRutasPanel extends JPanel {
    private JFrame frame;
    private JTable tablaRutas;

    public DisponibilidadRutasPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(244, 244, 244));
        createUI();
    }

    private void createUI() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(26, 38, 116));
        header.setPreferredSize(new Dimension(800, 80));

        JLabel title = new JLabel("DISPONIBILIDAD DE RUTAS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        JButton backButton = new JButton("Volver");
        backButton.setBackground(new Color(205, 163, 74));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new MainMenuPanel(frame));
            frame.revalidate();
        });

        header.add(backButton, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        String[] columnNames = {"ID Ruta", "Estación Origen", "Estación Destino", "Distancia (km)", "Estado"};
        Vector<String> columnHeaders = new Vector<>();
        for (String columnName : columnNames) {
            columnHeaders.add(columnName);
        }

        Vector<Vector<Object>> data = new Vector<>();
        List<Ruta> rutas = GestorRutas.getInstance().getRutas();
        System.out.println("Cargando rutas en DisponibilidadRutasPanel - Total: " + rutas.size());

        for (Ruta ruta : rutas) {
            Vector<Object> dataRow = new Vector<>();
            addTableCell(dataRow, ruta.getIdRuta());
            addTableCell(dataRow, ruta.getEstacionOrigen());
            addTableCell(dataRow, ruta.getEstacionDestino());
            addTableCell(dataRow, String.valueOf(ruta.getDistancia()));
            addTableCell(dataRow, ruta.getEstado());
            data.add(dataRow);
        }

        tablaRutas = new JTable(data, columnHeaders);
        tablaRutas.setFillsViewportHeight(true);
        tablaRutas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaRutas.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(tablaRutas);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(scrollPane, BorderLayout.CENTER);
    }

    private void addTableCell(Vector<Object> dataRow, Object value) {
        dataRow.add(value);
    }
}