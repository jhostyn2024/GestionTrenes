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

public class GestionRutasGUI {
    public GestionRutasGUI() {
        // Crear el marco de la GUI de gestión de rutas
        JFrame frame = new JFrame("Menú de Gestión de Rutas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear un panel para el contenido
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Layout vertical
        panel.setBackground(new Color(242, 242, 242)); // Color de fondo

        // Título
        JLabel titleLabel = new JLabel("MENU GESTION DE RUTAS");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre el título y los botones

        // Botones de gestión
        JButton btnAgregar = new JButton("AGREGAR");
        JButton btnDisponibilidad = new JButton("DISPONIBILIDAD");
        JButton btnEliminarModificar = new JButton("ELIMINAR O MODIFICAR");
        JButton btnRutaMasCorta = new JButton("RUTA MAS CORTA");

        // Estilo de los botones
        for (JButton button : new JButton[]{btnAgregar, btnDisponibilidad, btnEliminarModificar, btnRutaMasCorta}) {
            button.setFont(new Font("Arial", Font.BOLD, 16));
            button.setBackground(new Color(197, 161, 0)); // Color de fondo
            button.setForeground(Color.WHITE); // Color del texto
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setPreferredSize(new Dimension(200, 50)); // Tamaño del botón
            button.setFocusPainted(false); // Quitar el borde de enfoque
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Aquí puedes agregar la lógica para cada botón
                    JOptionPane.showMessageDialog(frame, "Abrir opción: " + button.getText().toLowerCase() + "...");
                }
            });
            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre botones
        }

        // Agregar el panel al marco
        frame.add(panel);
        frame.setVisible(true); // Hacer visible la ventana
    }
}