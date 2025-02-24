/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

/**
 *
 * @author jhost
 */


import modelo.Ruta;
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
        txtDistancia.set