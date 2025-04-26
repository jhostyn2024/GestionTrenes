/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DisponibilidadPanel extends JPanel {
    private JFrame frame;
    private JList<Ruta> listaRutas;
    private DefaultListModel<Ruta> listModel;

    public DisponibilidadPanel(JFrame frame) {
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

        listModel = new DefaultListModel<>();
        List<Ruta> rutas = GestorRutas.getInstance().getRutas();
        System.out.println("Cargando rutas en DisponibilidadPanel - Total: " + rutas.size());
        rutas.forEach(listModel::addElement);

        listaRutas = new JList<>(listModel);
        listaRutas.setCellRenderer(new RutaListRenderer());
        listaRutas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(listaRutas);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(scrollPane, BorderLayout.CENTER);
    }

    private static class RutaListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, 
                                                     boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Ruta) {
                Ruta r = (Ruta) value;
                setText(String.format("ID: %s - %s a %s, Distancia: %.2f km, Estado: %s", 
                    r.getIdRuta(), r.getEstacionOrigen(), r.getEstacionDestino(), 
                    r.getDistancia(), r.getEstado()));
            }
            return this;
        }
    }
}