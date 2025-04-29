/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;

public class ListaTrenesPanel extends JPanel {
    private JFrame frame;
    private JTextArea txtTrenes;
    private final Color BLUE_COLOR = new Color(0, 86, 179);
    private final Color GOLD_COLOR = new Color(198, 168, 77);

    public ListaTrenesPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUI();
    }

    private void createUI() {
        // Header
        JPanel header = new JPanel();
        header.setBackground(BLUE_COLOR);
        header.setPreferredSize(new Dimension(800, 100));
        JLabel title = new JLabel("LISTA DE TRENES", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        // Lista de trenes
        txtTrenes = new JTextArea();
        txtTrenes.setEditable(false);
        txtTrenes.setFont(new Font("Arial", Font.PLAIN, 14));
        actualizarLista();

        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setBackground(new Color(150, 40, 40));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));
        btnVolver.addActionListener(e -> {
            frame.setContentPane(new GestionTrenesPanel(frame));
            frame.revalidate();
        });

        add(new JScrollPane(txtTrenes), BorderLayout.CENTER);
        add(btnVolver, BorderLayout.SOUTH);
    }

    private void actualizarLista() {
        StringBuilder sb = new StringBuilder();
        for (Tren tren : GestorTrenes.getInstance().getTrenes()) {
            sb.append("ID Tren: ").append(tren.getIdTren()).append("\n");
            sb.append("Nombre: ").append(tren.getNombre()).append("\n");
            sb.append("Tipo: ").append(tren.getTipoTren()).append("\n");
            sb.append("Capacidad Carga: ").append(tren.getCapacidadCarga()).append(" vagones\n");
            sb.append("Kilometraje: ").append(tren.getKilometraje()).append(" km\n\n");
        }
        txtTrenes.setText(sb.toString());
    }
}