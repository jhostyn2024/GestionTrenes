/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class GestionRutasPanel extends JPanel {
    private JFrame frame;
    private JTextField txtIdRuta, txtOrigen, txtDestino, txtDistancia;
    private JComboBox<String> cbEstado;
    private JTextArea txtResultado;
    private final Color BLUE_COLOR = new Color(0, 86, 179);
    private final Color GOLD_COLOR = new Color(198, 168, 77);

    public GestionRutasPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUI();
    }

    private void createUI() {
        JPanel header = new JPanel();
        header.setBackground(BLUE_COLOR);
        header.setPreferredSize(new Dimension(800, 100));
        JLabel title = new JLabel("GESTIÓN DE RUTAS", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        txtIdRuta = new JTextField("RUTA-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        txtIdRuta.setEditable(false);
        txtOrigen = new JTextField();
        txtDestino = new JTextField();
        txtDistancia = new JTextField();
        cbEstado = new JComboBox<>(new String[]{"Activa", "Inactiva"});
        txtResultado = new JTextArea(5, 20);
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Arial", Font.PLAIN, 14));

        addFormField(formPanel, "ID Ruta:", txtIdRuta);
        addFormField(formPanel, "Origen:", txtOrigen);
        addFormField(formPanel, "Destino:", txtDestino);
        addFormField(formPanel, "Distancia (km):", txtDistancia);
        addFormField(formPanel, "Estado:", cbEstado);

        JButton btnAgregar = new JButton("AGREGAR");
        btnAgregar.setBackground(GOLD_COLOR);
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 16));
        btnAgregar.addActionListener(e -> agregarRuta());

        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setBackground(new Color(100, 100, 100));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));
        btnVolver.addActionListener(e -> {
            System.out.println("Navegando a MainMenuPanel desde GestionRutasPanel");
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

    private void agregarRuta() {
        try {
            String idRuta = txtIdRuta.getText().trim();
            String origen = txtOrigen.getText().trim();
            String destino = txtDestino.getText().trim();
            String distanciaStr = txtDistancia.getText().trim();
            String estado = (String) cbEstado.getSelectedItem();

            if (origen.isEmpty() || destino.isEmpty() || distanciaStr.isEmpty()) {
                txtResultado.setText("Error: Complete los campos obligatorios (Origen, Destino, Distancia).");
                System.out.println("Campos incompletos en agregarRuta");
                return;
            }

            if (!origen.matches("[a-zA-Z ]+") || !destino.matches("[a-zA-Z ]+")) {
                txtResultado.setText("Error: Origen y Destino solo deben contener letras y espacios.");
                System.out.println("Origen o Destino inválido en agregarRuta");
                return;
            }

            double distancia;
            try {
                distancia = Double.parseDouble(distanciaStr);
                if (distancia <= 0) {
                    txtResultado.setText("Error: La distancia debe ser mayor a 0.");
                    System.out.println("Distancia inválida en agregarRuta");
                    return;
                }
            } catch (NumberFormatException e) {
                txtResultado.setText("Error: La distancia debe ser un número válido.");
                System.out.println("Formato de distancia inválido en agregarRuta");
                return;
            }

            Ruta ruta = new Ruta(idRuta, origen, destino, distancia, estado);
            GestorRutas.getInstance().agregarRuta(ruta);
            txtResultado.setText("Ruta agregada exitosamente:\nID: " + idRuta + "\nOrigen: " + origen + "\nDestino: " + destino + "\nDistancia: " + distancia + " km\nEstado: " + estado);
            System.out.println("Ruta agregada: " + idRuta);

            txtIdRuta.setText("RUTA-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            txtOrigen.setText("");
            txtDestino.setText("");
            txtDistancia.setText("");
            cbEstado.setSelectedIndex(0);
        } catch (Exception e) {
            txtResultado.setText("Error al agregar ruta: " + e.getMessage());
            System.err.println("Excepción en agregarRuta: " + e.getMessage());
            e.printStackTrace();
        }
    }
}