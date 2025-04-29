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
        // Header
        JPanel header = new JPanel();
        header.setBackground(BLUE_COLOR);
        header.setPreferredSize(new Dimension(800, 100));
        JLabel title = new JLabel("GESTIÓN DE TRENES", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        // Tabla de trenes
        String[] columnas = {"ID Tren", "Nombre", "Tipo", "Capacidad Carga", "Kilometraje"};
        List<Tren> trenes = GestorTrenes.getInstance().getTrenes();
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
        JScrollPane scrollPane = new JScrollPane(tablaTrenes);
        add(scrollPane, BorderLayout.CENTER);

        // Botones
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.setBackground(new Color(240, 240, 240));

        JButton btnAgregar = new JButton("AGREGAR TREN");
        btnAgregar.setBackground(GOLD_COLOR);
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 16));
        btnAgregar.addActionListener(e -> agregarTren());

        JButton btnModificar = new JButton("MODIFICAR TREN");
        btnModificar.setBackground(GOLD_COLOR);
        btnModificar.setForeground(Color.WHITE);
        btnModificar.setFont(new Font("Arial", Font.BOLD, 16));
        btnModificar.addActionListener(e -> modificarTren());

        JButton btnEliminar = new JButton("ELIMINAR TREN");
        btnEliminar.setBackground(GOLD_COLOR);
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 16));
        btnEliminar.addActionListener(e -> eliminarTren());

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

    private void agregarTren() {
        JTextField txtIdTren = new JTextField();
        JTextField txtNombre = new JTextField();
        JComboBox<String> cbTipoTren = new JComboBox<>(new String[]{"Mercedes-Benz", "Arnold"});
        JTextField txtKilometraje = new JTextField("0.0");
        Object[] message = {
                "ID Tren:", txtIdTren,
                "Nombre:", txtNombre,
                "Tipo Tren:", cbTipoTren,
                "Kilometraje:", txtKilometraje
        };
        int option = JOptionPane.showConfirmDialog(frame, message, "Agregar Tren", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String idTren = txtIdTren.getText().trim();
            String nombre = txtNombre.getText().trim();
            String tipoTren = (String) cbTipoTren.getSelectedItem();
            double kilometraje;
            try {
                kilometraje = Double.parseDouble(txtKilometraje.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Kilometraje inválido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (idTren.isEmpty() || nombre.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Tren tren = new Tren(idTren, nombre, tipoTren, kilometraje);
            GestorTrenes.getInstance().agregarTren(tren);
            JOptionPane.showMessageDialog(frame, "Tren agregado exitosamente");
            frame.setContentPane(new GestionTrenesPanel(frame));
            frame.revalidate();
        }
    }

    private void modificarTren() {
        int selectedRow = tablaTrenes.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Seleccione un tren", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String idTren = (String) tablaTrenes.getValueAt(selectedRow, 0);
        JTextField txtNombre = new JTextField((String) tablaTrenes.getValueAt(selectedRow, 1));
        JTextField txtKilometraje = new JTextField((String) tablaTrenes.getValueAt(selectedRow, 4));
        Object[] message = {
                "Nombre:", txtNombre,
                "Kilometraje:", txtKilometraje
        };
        int option = JOptionPane.showConfirmDialog(frame, message, "Modificar Tren", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String nombre = txtNombre.getText().trim();
            double kilometraje;
            try {
                kilometraje = Double.parseDouble(txtKilometraje.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Kilometraje inválido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean exito = GestorTrenes.getInstance().modificarTren(idTren, nombre, kilometraje);
            if (exito) {
                JOptionPane.showMessageDialog(frame, "Tren modificado exitosamente");
                frame.setContentPane(new GestionTrenesPanel(frame));
                frame.revalidate();
            } else {
                JOptionPane.showMessageDialog(frame, "No se encontró el tren", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarTren() {
        int selectedRow = tablaTrenes.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Seleccione un tren", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String idTren = (String) tablaTrenes.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(frame, "¿Eliminar tren " + idTren + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean exito = GestorTrenes.getInstance().eliminarTren(idTren);
            if (exito) {
                JOptionPane.showMessageDialog(frame, "Tren eliminado exitosamente");
                frame.setContentPane(new GestionTrenesPanel(frame));
                frame.revalidate();
            } else {
                JOptionPane.showMessageDialog(frame, "No se encontró el tren", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}