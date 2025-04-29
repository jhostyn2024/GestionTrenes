/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;

public class AgregarRutaPanel extends JPanel {
    private JFrame frame;
    private JTextField txtOrigen, txtDestino, txtDistancia;
    private JComboBox<String> cbEstado;
    private final Color BLUE_COLOR = new Color(0, 86, 179);
    private final Color GOLD_COLOR = new Color(198, 168, 77);

    public AgregarRutaPanel(JFrame frame) {
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
        JLabel title = new JLabel("AGREGAR RUTA", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        // Formulario
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        txtOrigen = new JTextField();
        txtDestino = new JTextField();
        txtDistancia = new JTextField();
        cbEstado = new JComboBox<>(new String[]{"Activa", "Inactiva"});

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
        btnVolver.setBackground(new Color(150, 40, 40));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));
        btnVolver.addActionListener(e -> {
            frame.setContentPane(new GestionRutasPanel(frame));
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

    private void agregarRuta() {
        String origen = txtOrigen.getText().trim();
        String destino = txtDestino.getText().trim();
        String distanciaStr = txtDistancia.getText().trim();
        String estado = (String) cbEstado.getSelectedItem();

        if (origen.isEmpty() || destino.isEmpty() || distanciaStr.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double distancia;
        try {
            distancia = Double.parseDouble(distanciaStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Distancia inválida", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String idRuta = "RUTA-" + (GestorRutas.getInstance().getRutas().size() + 1);
        Ruta ruta = new Ruta(idRuta, origen, destino, distancia, estado);
        GestorRutas.getInstance().agregarRuta(ruta);

        JOptionPane.showMessageDialog(frame, "Ruta agregada exitosamente - ID: " + idRuta);
        frame.setContentPane(new GestionRutasPanel(frame));
        frame.revalidate();
    }
}