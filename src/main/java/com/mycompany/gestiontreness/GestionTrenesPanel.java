/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class GestionTrenesPanel extends JPanel {
    private JFrame frame;
    private JTextField txtIdTren, txtNombre, txtCapacidad;
    private JComboBox<String> cbEstado;
    private JTextArea txtResultado;
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

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        txtIdTren = new JTextField("TREN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        txtIdTren.setEditable(false);
        txtNombre = new JTextField();
        txtCapacidad = new JTextField();
        cbEstado = new JComboBox<>(new String[]{"Activo", "Inactivo"});
        txtResultado = new JTextArea(5, 20);
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Arial", Font.PLAIN, 14));

        addFormField(formPanel, "ID Tren:", txtIdTren);
        addFormField(formPanel, "Nombre:", txtNombre);
        addFormField(formPanel, "Capacidad:", txtCapacidad);
        addFormField(formPanel, "Estado:", cbEstado);

        JButton btnAgregar = new JButton("AGREGAR");
        btnAgregar.setBackground(GOLD_COLOR);
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 16));
        btnAgregar.addActionListener(e -> agregarTren());

        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setBackground(new Color(100, 100, 100));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));
        btnVolver.addActionListener(e -> {
            System.out.println("Navegando a MainMenuPanel desde GestionTrenesPanel");
            frame.setContentPane(new MainMenuPanel(frame));
            frame.revalidate();
        });

        formPanel.add(btnAgregar);
        formPanel.add(btnVolver);

        add(new JScrollPane(formPanel), BorderLayout.CENTER);
        add(new JScrollPane(txtResultado), BorderLayout.SOUTH);
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
        try {
            String idTren = txtIdTren.getText().trim();
            String nombre = txtNombre.getText().trim();
            String capacidadStr = txtCapacidad.getText().trim();
            String estado = (String) cbEstado.getSelectedItem();

            if (nombre.isEmpty() || capacidadStr.isEmpty()) {
                txtResultado.setText("Error: Complete los campos obligatorios (Nombre, Capacidad).");
                System.out.println("Campos incompletos en agregarTren");
                return;
            }

            if (!nombre.matches("[a-zA-Z0-9 ]+")) {
                txtResultado.setText("Error: El nombre solo debe contener letras, números y espacios.");
                System.out.println("Nombre inválido en agregarTren");
                return;
            }

            int capacidad;
            try {
                capacidad = Integer.parseInt(capacidadStr);
                if (capacidad <= 0) {
                    txtResultado.setText("Error: La capacidad debe ser mayor a 0.");
                    System.out.println("Capacidad inválida en agregarTren");
                    return;
                }
            } catch (NumberFormatException e) {
                txtResultado.setText("Error: La capacidad debe ser un número válido.");
                System.out.println("Formato de capacidad inválido en agregarTren");
                return;
            }

            Tren tren = new Tren(idTren, nombre, capacidad, estado);
            GestorTrenes.getInstance().agregarTren(tren);
            txtResultado.setText("Tren agregado exitosamente:\nID: " + idTren + "\nNombre: " + nombre + "\nCapacidad: " + capacidad + "\nEstado: " + estado);
            System.out.println("Tren agregado: " + idTren);

            txtIdTren.setText("TREN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            txtNombre.setText("");
            txtCapacidad.setText("");
            cbEstado.setSelectedIndex(0);
        } catch (Exception e) {
            txtResultado.setText("Error al agregar tren: " + e.getMessage());
            System.err.println("Excepción en agregarTren: " + e.getMessage());
            e.printStackTrace();
        }
    }
}