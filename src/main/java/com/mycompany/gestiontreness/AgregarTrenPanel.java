/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;

public class AgregarTrenPanel extends JPanel {
    private JFrame frame;
    private JTextField txtIdTren, txtNombre, txtKilometraje;
    private JComboBox<String> cbTipoTren;
    private final Color BLUE_COLOR = new Color(0, 86, 179);
    private final Color GOLD_COLOR = new Color(198, 168, 77);

    public AgregarTrenPanel(JFrame frame) {
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
        JLabel title = new JLabel("AGREGAR TREN", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        // Formulario
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        txtIdTren = new JTextField();
        txtNombre = new JTextField();
        cbTipoTren = new JComboBox<>(new String[]{"Mercedes-Benz", "Arnold"});
        txtKilometraje = new JTextField("0.0");

        addFormField(formPanel, "ID Tren:", txtIdTren);
        addFormField(formPanel, "Nombre:", txtNombre);
        addFormField(formPanel, "Tipo Tren:", cbTipoTren);
        addFormField(formPanel, "Kilometraje:", txtKilometraje);

        JButton btnAgregar = new JButton("AGREGAR");
        btnAgregar.setBackground(GOLD_COLOR);
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 16));
        btnAgregar.addActionListener(e -> agregarTren());

        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setBackground(new Color(150, 40, 40));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));
        btnVolver.addActionListener(e -> {
            frame.setContentPane(new GestionTrenesPanel(frame));
            frame.revalidate();
        });

        formPanel.add(btnAgregar);
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

    private void agregarTren() {
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

        // Verificar si el ID ya existe
        if (GestorTrenes.getInstance().getTrenes().stream().anyMatch(t -> t.getIdTren().equals(idTren))) {
            JOptionPane.showMessageDialog(frame, "El ID del tren ya existe", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Tren tren = new Tren(idTren, nombre, tipoTren, kilometraje);
        GestorTrenes.getInstance().agregarTren(tren);
        JOptionPane.showMessageDialog(frame, "Tren agregado exitosamente - ID: " + idTren);
        frame.setContentPane(new GestionTrenesPanel(frame));
        frame.revalidate();
    }
}