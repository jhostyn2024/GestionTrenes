/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

/**
 *
 * @author jhost
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionTrenesGUI {
    private GestorTren gestorTren;

    public GestionTrenesGUI(GestorTren gestorTren) {
        this.gestorTren = gestorTren;

        // Crear el marco de la GUI
        JFrame frame = new JFrame("Gestión de Trenes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear un panel para el contenido
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout()); // Layout horizontal

        // Botón para agregar tren
        JButton btnAgregar = new JButton("Agregar Tren");
                btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AgregarTrenGUI(gestorTren); // Abrir la ventana de agregar tren
            }
        });

        // Botón para eliminar o modificar tren
        JButton btnEliminarModificar = new JButton("Eliminar/Modificar Tren");
        btnEliminarModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EliminarModificarTrenGUI(gestorTren); // Abrir la ventana de eliminar/modificar tren
            }
        });

        // Agregar botones al panel
        panel.add(btnAgregar);
        panel.add(btnEliminarModificar);

        // Agregar el panel al marco
        frame.add(panel);
        frame.setVisible(true); // Hacer visible la ventana
    }
}