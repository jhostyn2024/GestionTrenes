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

public class GestionGeneralGUI {
    public GestionGeneralGUI() {
        // Crear el marco de la GUI de gestión general
        JFrame frame = new JFrame("Gestión General");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear un panel para el contenido
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Layout vertical
        panel.setBackground(new Color(229, 229, 229)); // Color de fondo

        // Título
        JLabel titleLabel = new JLabel("GESTION GENERAL");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(187, 153, 51)); // Color del texto
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre el título y los botones

        // Botones de gestión
        JButton btnGestionTrenes = new JButton("GESTION TRENES");
        JButton btnGestionVagones = new JButton("GESTION VAGONES");

        // Estilo de los botones
        for (JButton button : new JButton[]{btnGestionTrenes, btnGestionVagones}) {
            button.setFont(new Font("Arial", Font.BOLD, 16));
            button.setBackground(new Color(187, 153, 51)); // Color de fondo
            button.setForeground(Color.WHITE); // Color del texto
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setPreferredSize(new Dimension(200, 50)); // Tamaño del botón
            button.setFocusPainted(false); // Quitar el borde de enfoque
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Aquí puedes agregar la lógica para gestionar trenes o vagones
                    JOptionPane.showMessageDialog(frame, "Abrir gestión de " + button.getText().toLowerCase() + "...");
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