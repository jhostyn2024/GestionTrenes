/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;

public class ModificarEliminarTrenPanel extends JPanel {
    private JFrame frame;
    private String idTren;
    private JTextField txtNombre, txtKilometraje;
    private final Color BLUE_COLOR = new Color(0, 86, 179);
    private final Color GOLD_COLOR = new Color(198, 168, 77);

    public ModificarEliminarTrenPanel(JFrame frame, String idTren) {
        this.frame = frame;
        this.idTren = idTren;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUI();
    }

    private void createUI() {
        // Header
        JPanel header = new JPanel();
        header.setBackground(BLUE_COLOR);
        header.setPreferredSize(new Dimension(800, 100));
        JLabel title = new JLabel("MODIFICAR/ELIMINAR TREN", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        // Formulario
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        Tren tren = GestorTrenes.getInstance().getTrenes().stream()
                .filter(t -> t.getIdTren().equals(idTren))
                .findFirst()
                .orElse(null);

        if (tren == null) {
            JOptionPane.showMessageDialog(frame, "Tren no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            frame.setContentPane(new GestionTrenesPanel(frame));
            frame.revalidate();
            return;
        }

        txtNombre = new JTextField(tren.getNombre());
        txtKilometraje = new JTextField(String.valueOf(tren.getKilometraje()));

        addFormField(formPanel, "Nombre:", txtNombre);
        addFormField(formPanel, "Kilometraje:", txtKilometraje);

        JButton btnModificar = new JButton("MODIFICAR");
        btnModificar.setBackground(GOLD_COLOR);
        btnModificar.setForeground(Color.WHITE);
        btnModificar.setFont(new Font("Arial", Font.BOLD, 16));
        btnModificar.addActionListener(e -> modificarTren());

        JButton btnEliminar = new JButton("ELIMINAR");
        btnEliminar.setBackground(GOLD_COLOR);
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 16));
        btnEliminar.addActionListener(e -> eliminarTren());

        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setBackground(new Color(150, 40, 40));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));
        btnVolver.addActionListener(e -> {
            frame.setContentPane(new GestionTrenesPanel(frame));
            frame.revalidate();
        });

        formPanel.add(btnModificar);
        formPanel.add(btnEliminar);
        formPanel.add(btnVolver);

        add(new JScrollPane(formPanel), BorderLayout.CENTER);
    }

    private void addFormField(JPanel panel, String label, JComponent field) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(200, 30));
        panel.add(lbl);
        panel.add(field);
    }

    private void modificarTren() {
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
            JOptionPane.showMessageDialog(frame, "Tren modificado exitosamente - ID: " + idTren);
            frame.setContentPane(new GestionTrenesPanel(frame));
            frame.revalidate();
        } else {
            JOptionPane.showMessageDialog(frame, "No se pudo modificar el tren", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarTren() {
        int confirm = JOptionPane.showConfirmDialog(frame, "¿Eliminar tren " + idTren + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean exito = GestorTrenes.getInstance().eliminarTren(idTren);
            if (exito) {
                JOptionPane.showMessageDialog(frame, "Tren eliminado exitosamente - ID: " + idTren);
                frame.setContentPane(new GestionTrenesPanel(frame));
                frame.revalidate();
            } else {
                JOptionPane.showMessageDialog(frame, "No se pudo eliminar el tren", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}