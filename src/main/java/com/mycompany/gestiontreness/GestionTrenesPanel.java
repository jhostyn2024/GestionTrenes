/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;

public class GestionTrenesPanel extends JPanel {
    private JFrame frame;
    private JTable tablaTrenes;
    private final Color BLUE_COLOR = new Color(0, 86, 179);
    private final Color GOLD_COLOR = new Color(198, 168, 77);

    public GestionTrenesPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUI();
    }

    private void createUI() {
        JPanel header = new JPanel();
        header.setBackground(BLUE_COLOR);
        header.setPreferredSize(new Dimension(800, 100));
        JLabel title = new JLabel("GESTIÓN DE TRENES", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        String[] columnas = {"ID Tren", "Nombre", "Tipo", "Capacidad Carga", "Kilometraje"};
        List<Tren> trenes = GestorTrenes.getInstance().getTrenes();
        if (trenes == null) {
            JOptionPane.showMessageDialog(frame, "Error al cargar trenes", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String[][] datos = new String[trenes.size()][5];
        for (int i = 0; i < trenes.size(); i++) {
            Tren tren = trenes.get(i);
            datos[i][0] = tren.getIdTren();
            datos[i][1] = tren.getNombre();
            datos[i][2] = tren.getTipoTren();
            datos[i][3] = String.valueOf(tren.getCapacidadCarga());
            datos[i][4] = String.valueOf(tren.getKilometraje());
        }
        tablaTrenes = new JTable(datos, columnas);
        tablaTrenes.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaTrenes.setRowHeight(25);
        tablaTrenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(tablaTrenes);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.setBackground(new Color(240, 240, 240));

        JButton btnAgregar = new JButton("AGREGAR TREN");
        btnAgregar.setBackground(GOLD_COLOR);
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 16));
        btnAgregar.addActionListener(e -> {
            frame.setContentPane(new AgregarTrenPanel(frame));
            frame.revalidate();
        });

        JButton btnModificar = new JButton("MODIFICAR TREN");
        btnModificar.setBackground(GOLD_COLOR);
        btnModificar.setForeground(Color.WHITE);
        btnModificar.setFont(new Font("Arial", Font.BOLD, 16));
        btnModificar.addActionListener(e -> {
            int selectedRow = tablaTrenes.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Seleccione un tren", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String idTren = (String) tablaTrenes.getValueAt(selectedRow, 0);
            frame.setContentPane(new ModificarEliminarTrenPanel(frame, idTren));
            frame.revalidate();
        });

        JButton btnListar = new JButton("LISTAR TRENES");
        btnListar.setBackground(GOLD_COLOR);
        btnListar.setForeground(Color.WHITE);
        btnListar.setFont(new Font("Arial", Font.BOLD, 16));
        btnListar.addActionListener(e -> {
            frame.setContentPane(new ListaTrenesPanel(frame));
            frame.revalidate();
        });

        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setBackground(new Color(150, 40, 40));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));
        btnVolver.addActionListener(e -> {
            frame.setContentPane(new MainMenuPanel(frame));
            frame.revalidate();
        });

        buttonsPanel.add(btnAgregar);
        buttonsPanel.add(btnModificar);
        buttonsPanel.add(btnListar);
        buttonsPanel.add(btnVolver);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
}