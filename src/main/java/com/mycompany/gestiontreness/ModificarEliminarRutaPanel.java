/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;

public class ModificarEliminarRutaPanel extends JPanel {
    private JFrame frame;
    private JTextField txtIdRuta, txtOrigen, txtDestino, txtDistancia;
    private JComboBox<String> cbEstado;
    private JTextArea txtResultado;
    private final Color BLUE_COLOR = new Color(0, 86, 179);
    private final Color GOLD_COLOR = new Color(198, 168, 77);

    public ModificarEliminarRutaPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUI();
    }

    private void createUI() {
        JPanel header = new JPanel();
        header.setBackground(BLUE_COLOR);
        header.setPreferredSize(new Dimension(800, 100));
        JLabel title = new JLabel("MODIFICAR O ELIMINAR RUTA", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        txtIdRuta = new JTextField();
        txtOrigen = new JTextField();
        txtDestino = new JTextField();
        txtDistancia = new JTextField();
        cbEstado = new JComboBox<>(new String[]{"Activa", "Inactiva"});
        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Arial", Font.PLAIN, 14));

        addFormField(formPanel, "ID Ruta:", txtIdRuta);
        addFormField(formPanel, "Origen:", txtOrigen);
        addFormField(formPanel, "Destino:", txtDestino);
        addFormField(formPanel, "Distancia (km):", txtDistancia);
        addFormField(formPanel, "Estado:", cbEstado);

        JButton btnModificar = new JButton("MODIFICAR");
        btnModificar.setBackground(GOLD_COLOR);
        btnModificar.setForeground(Color.WHITE);
        btnModificar.setFont(new Font("Arial", Font.BOLD, 16));
        btnModificar.addActionListener(e -> modificarRuta());

        JButton btnEliminar = new JButton("ELIMINAR");
        btnEliminar.setBackground(new Color(150, 40, 40));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 16));
        btnEliminar.addActionListener(e -> eliminarRuta());

        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setBackground(new Color(100, 100, 100));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));
        btnVolver.addActionListener(e -> {
            System.out.println("Navegando a MainMenuPanel desde ModificarEliminarRutaPanel");
            frame.setContentPane(new MainMenuPanel(frame));
            frame.revalidate();
        });

        formPanel.add(btnModificar);
        formPanel.add(btnEliminar);
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

    private void modificarRuta() {
        String idRuta = txtIdRuta.getText().trim();
        String origen = txtOrigen.getText().trim();
        String destino = txtDestino.getText().trim();
        String distanciaStr = txtDistancia.getText().trim();
        String estado = (String) cbEstado.getSelectedItem();

        if (idRuta.isEmpty() || origen.isEmpty() || destino.isEmpty() || distanciaStr.isEmpty()) {
            txtResultado.setText("Complete todos los campos");
            System.out.println("Campos incompletos en modificarRuta");
            return;
        }

        double distancia;
        try {
            distancia = Double.parseDouble(distanciaStr);
            if (distancia <= 0) {
                txtResultado.setText("La distancia debe ser mayor que 0");
                System.out.println("Distancia inválida: " + distanciaStr);
                return;
            }
        } catch (NumberFormatException e) {
            txtResultado.setText("Distancia inválida");
            System.out.println("Error en formato de distancia: " + e.getMessage());
            return;
        }

        boolean exito = GestorRutas.getInstance().modificarRuta(idRuta, origen, destino, distancia, estado);
        if (exito) {
            txtResultado.setText("Ruta modificada exitosamente\nID: " + idRuta + "\nOrigen: " + origen + 
                                 "\nDestino: " + destino + "\nDistancia: " + distancia + " km\nEstado: " + estado);
            System.out.println("Ruta modificada: " + idRuta);
        } else {
            txtResultado.setText("No se encontró la ruta con ID: " + idRuta);
            System.out.println("No se pudo modificar ruta: " + idRuta);
        }
        txtResultado.repaint();
    }

    private void eliminarRuta() {
        String idRuta = txtIdRuta.getText().trim();
        if (idRuta.isEmpty()) {
            txtResultado.setText("Ingrese un ID de ruta");
            System.out.println("ID de ruta vacío en eliminarRuta");
            return;
        }

        boolean exito = GestorRutas.getInstance().eliminarRuta(idRuta);
        if (exito) {
            txtResultado.setText("Ruta eliminada exitosamente\nID: " + idRuta);
            System.out.println("Ruta eliminada: " + idRuta);
        } else {
            txtResultado.setText("No se encontró la ruta con ID: " + idRuta);
            System.out.println("No se pudo eliminar ruta: " + idRuta);
        }
        txtResultado.repaint();
    }
}