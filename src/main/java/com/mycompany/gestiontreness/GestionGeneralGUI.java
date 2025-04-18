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
import java.awt.FlowLayout;

public class GestionGeneralGUI {
    private GestorTren gestorTren;

    public GestionGeneralGUI(GestorTren gestorTren) {
        this.gestorTren = gestorTren;

        // Crear el marco de la GUI
        JFrame frame = new JFrame("Gestión General");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear un panel para el contenido
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout()); // Layout horizontal

        // Botón para abrir la gestión de trenes
        JButton btnGestionTrenes = new JButton("Gestionar Trenes");
        btnGestionTrenes.addActionListener(e -> {
            new GestionTrenesGUI(gestorTren); // Pasar el gestorTren al constructor
        });

        // Agregar el botón al panel
        panel.add(btnGestionTrenes);

        // Agregar el panel al marco
        frame.add(panel);
        frame.setVisible(true); // Hacer visible la ventana
    }
}