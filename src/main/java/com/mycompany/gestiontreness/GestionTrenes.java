/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

/**
 *
 * @author jhost
 */



import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestionTrenes extends JFrame {
    private JTextField txtNombre, txtIdentificador, txtMarca, txtCapacidad, txtKilometraje;
    private JButton btnAgregar, btnConsultar;
    private List<Tren> trenes = new ArrayList<>();
    private ObjectMapper mapper = new ObjectMapper();

    public GestionTrenes() {
        setTitle("Gestión de Trenes");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 20, 80, 25);
        panel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(100, 20, 165, 25);
        panel.add(txtNombre);

        JLabel lblIdentificador = new JLabel("Identificador:");
        lblIdentificador.setBounds(10, 50, 80, 25);
        panel.add(lblIdentificador);

        txtIdentificador = new JTextField();
        txtIdentificador.setBounds(100, 50, 165, 25);
        panel.add(txtIdentificador);

        JLabel lblMarca = new JLabel("Marca:");
        lblMarca.setBounds(10, 80, 80, 25);
        panel.add(lblMarca);

        txtMarca = new JTextField();
        txtMarca.setBounds(100, 80, 165, 25);
        panel.add(txtMarca);

        JLabel lblCapacidad = new JLabel("Capacidad:");
        lblCapacidad.setBounds(10, 110, 80, 25);
        panel.add(lblCapacidad);

        txtCapacidad = new JTextField();
        txtCapacidad.setBounds(100, 110, 165, 25);
        panel.add(txtCapacidad);

        JLabel lblKilometraje = new JLabel("Kilometraje:");
        lblKilometraje.setBounds(10, 140, 80, 25);
        panel.add(lblKilometraje);

        txtKilometraje = new JTextField();
        txtKilometraje.setBounds(100, 140, 165, 25);
        panel.add(txtKilometraje);

        btnAgregar = new JButton("Agregar Tren");
        btnAgregar.setBounds(10, 180, 120, 25);
        panel.add(btnAgregar);

        btnConsultar = new JButton("Consultar Trenes");
        btnConsultar.setBounds(150, 180, 150, 25);
        panel.add(btnConsultar);

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre = txtNombre.getText();
                    String identificador = txtIdentificador.getText();
                    String marca = txtMarca.getText();
                    int capacidad = Integer.parseInt(txtCapacidad.getText());
                    double kilometraje = Double.parseDouble(txtKilometraje.getText());

                    Tren tren = new Tren(nombre, identificador, marca, capacidad, kilometraje, new ArrayList<>());
                    trenes.add(tren);

                    
                    mapper.writeValue(new File("trenes.json"), trenes);

                    JOptionPane.showMessageDialog(null, "Tren agregado correctamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    
                    trenes = mapper.readValue(new File("trenes.json"),
                            mapper.getTypeFactory().constructCollectionType(List.class, Tren.class));

                    StringBuilder sb = new StringBuilder();
                    for (Tren tren : trenes) {
                        sb.append(tren.toString()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al leer los trenes: " + ex.getMessage());
                }
            }
        });

        add(panel);
    }
}