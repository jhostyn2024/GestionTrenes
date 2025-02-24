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

public class GestionRutas extends JFrame {
    private JTextField txtOrigen, txtDestino, txtDistancia;
    private JButton btnAgregar, btnConsultar;
    private List<Ruta> rutas = new ArrayList<>();
    private ObjectMapper mapper = new ObjectMapper();

    public GestionRutas() {
        setTitle("Gestión de Rutas");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblOrigen = new JLabel("Origen:");
        lblOrigen.setBounds(10, 20, 80, 25);
        panel.add(lblOrigen);

        txtOrigen = new JTextField();
        txtOrigen.setBounds(100, 20, 165, 25);
        panel.add(txtOrigen);

        JLabel lblDestino = new JLabel("Destino:");
        lblDestino.setBounds(10, 50, 80, 25);
        panel.add(lblDestino);

        txtDestino = new JTextField();
        txtDestino.setBounds(100, 50, 165, 25);
        panel.add(txtDestino);

        JLabel lblDistancia = new JLabel("Distancia (km):");
        lblDistancia.setBounds(10, 80, 80, 25);
        panel.add(lblDistancia);

        txtDistancia = new JTextField();
        txtDistancia.setBounds(100, 80, 165, 25);
        panel.add(txtDistancia);

        btnAgregar = new JButton("Agregar Ruta");
        btnAgregar.setBounds(10, 120, 120, 25);
        panel.add(btnAgregar);

        btnConsultar = new JButton("Consultar Rutas");
        btnConsultar.setBounds(150, 120, 150, 25);
        panel.add(btnConsultar);

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String origen = txtOrigen.getText();
                    String destino = txtDestino.getText();
                    double distancia = Double.parseDouble(txtDistancia.getText());

                    Ruta ruta = new Ruta(origen, destino, new ArrayList<>(), distancia);
                    rutas.add(ruta);

                    
                    mapper.writeValue(new File("rutas.json"), rutas);

                    JOptionPane.showMessageDialog(null, "Ruta agregada correctamente");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    
                    rutas = mapper.readValue(new File("rutas.json"),
                            mapper.getTypeFactory().constructCollectionType(List.class, Ruta.class));

                    StringBuilder sb = new StringBuilder();
                    for (Ruta ruta : rutas) {
                        sb.append(ruta.toString()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al leer las rutas: " + ex.getMessage());
                }
            }
        });

        add(panel);
    }
}