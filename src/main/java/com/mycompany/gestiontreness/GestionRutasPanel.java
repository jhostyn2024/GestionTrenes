/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GestionRutasPanel extends JPanel {
    private JFrame frame;
    private JTable tablaRutas;
    private final Color BLUE_COLOR = new Color(0, 86, 179);
    private final Color GOLD_COLOR = new Color(198, 168, 77);

    public GestionRutasPanel(JFrame frame) {
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
        JLabel title = new JLabel("GESTIÓN DE RUTAS", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        // Tabla de rutas
        String[] columnas = {"ID Ruta", "Origen", "Destino", "Distancia (km)", "Estado"};
        List<Ruta> rutas = GestorRutas.getInstance().getRutas();
        String[][] datos = new String[rutas.size()][5];
        for (int i = 0; i < rutas.size(); i++) {
            Ruta ruta = rutas.get(i);
            datos[i][0] = ruta.getIdRuta();
            datos[i][1] = ruta.getEstacionOrigen();
            datos[i][2] = ruta.getEstacionDestino();
            datos[i][3] = String.valueOf(ruta.getDistancia());
            datos[i][4] = (String) ruta.getEstado();
        }
        tablaRutas = new JTable(datos, columnas);
        tablaRutas.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaRutas.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(tablaRutas);
        add(scrollPane, BorderLayout.CENTER);

        // Botones
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.setBackground(new Color(240, 240, 240));

        JButton btnAgregar = new JButton("AGREGAR RUTA");
        btnAgregar.setBackground(GOLD_COLOR);
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 16));
        btnAgregar.addActionListener(e -> agregarRuta());

        JButton btnModificar = new JButton("MODIFICAR RUTA");
        btnModificar.setBackground(GOLD_COLOR);
        btnModificar.setForeground(Color.WHITE);
        btnModificar.setFont(new Font("Arial", Font.BOLD, 16));
        btnModificar.addActionListener(e -> modificarRuta());

        JButton btnEliminar = new JButton("ELIMINAR RUTA");
        btnEliminar.setBackground(GOLD_COLOR);
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 16));
        btnEliminar.addActionListener(e -> eliminarRuta());

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
        buttonsPanel.add(btnEliminar);
        buttonsPanel.add(btnVolver);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void agregarRuta() {
        JTextField txtOrigen = new JTextField();
        JTextField txtDestino = new JTextField();
        JTextField txtDistancia = new JTextField();
        JComboBox<String> cbEstado = new JComboBox<>(new String[]{"Activa", "Inactiva"});
        Object[] message = {
                "Origen:", txtOrigen,
                "Destino:", txtDestino,
                "Distancia (km):", txtDistancia,
                "Estado:", cbEstado
        };
        int option = JOptionPane.showConfirmDialog(frame, message, "Agregar Ruta", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                double distancia = Double.parseDouble(txtDistancia.getText());
                String idRuta = "RUTA-" + (GestorRutas.getInstance().getRutas().size() + 1);
                Ruta ruta = new Ruta(idRuta, txtOrigen.getText(), txtDestino.getText(), distancia, (String) cbEstado.getSelectedItem());
                GestorRutas.getInstance().agregarRuta(ruta);
                JOptionPane.showMessageDialog(frame, "Ruta agregada exitosamente");
                frame.setContentPane(new GestionRutasPanel(frame));
                frame.revalidate();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Distancia inválida", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void modificarRuta() {
        int selectedRow = tablaRutas.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Seleccione una ruta", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String idRuta = (String) tablaRutas.getValueAt(selectedRow, 0);
        JTextField txtOrigen = new JTextField((String) tablaRutas.getValueAt(selectedRow, 1));
        JTextField txtDestino = new JTextField((String) tablaRutas.getValueAt(selectedRow, 2));
        JTextField txtDistancia = new JTextField((String) tablaRutas.getValueAt(selectedRow, 3));
        JComboBox<String> cbEstado = new JComboBox<>(new String[]{"Activa", "Inactiva"});
        cbEstado.setSelectedItem(tablaRutas.getValueAt(selectedRow, 4));
        Object[] message = {
                "Origen:", txtOrigen,
                "Destino:", txtDestino,
                "Distancia (km):", txtDistancia,
                "Estado:", cbEstado
        };
        int option = JOptionPane.showConfirmDialog(frame, message, "Modificar Ruta", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                double distancia = Double.parseDouble(txtDistancia.getText());
                boolean exito = GestorRutas.getInstance().modificarRuta(idRuta, txtOrigen.getText(), txtDestino.getText(), distancia, (String) cbEstado.getSelectedItem());
                if (exito) {
                    JOptionPane.showMessageDialog(frame, "Ruta modificada exitosamente");
                    frame.setContentPane(new GestionRutasPanel(frame));
                    frame.revalidate();
                } else {
                    JOptionPane.showMessageDialog(frame, "No se encontró la ruta", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Distancia inválida", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarRuta() {
        int selectedRow = tablaRutas.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Seleccione una ruta", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String idRuta = (String) tablaRutas.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(frame, "¿Eliminar ruta " + idRuta + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean exito = GestorRutas.getInstance().eliminarRuta(idRuta);
            if (exito) {
                JOptionPane.showMessageDialog(frame, "Ruta eliminada exitosamente");
                frame.setContentPane(new GestionRutasPanel(frame));
                frame.revalidate();
            } else {
                JOptionPane.showMessageDialog(frame, "No se encontró la ruta", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}