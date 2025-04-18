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

public class GestionRutasPanel extends JPanel {
    public GestionRutasPanel(JFrame frame) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Encabezado
        JPanel header = new JPanel();
        header.setBackground(new Color(0, 51, 102)); // Azul oscuro
        header.setLayout(new FlowLayout(FlowLayout.CENTER));
        header.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JLabel title = new JLabel("MEDINET");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        // Título del menú
        JLabel menuTitle = new JLabel("MENU GESTION DE RUTAS");
        menuTitle.setFont(new Font("Arial", Font.BOLD, 24));
        menuTitle.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        menuTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 40, 40));
        buttonPanel.setBackground(Color.WHITE);

        buttonPanel.add(createStyledButton("AGREGAR"));
        buttonPanel.add(createStyledButton("DISPONIBILIDAD"));
        buttonPanel.add(createStyledButton("ELIMINAR O MODIFICAR"));
        buttonPanel.add(createStyledButton("RUTA MAS CORTA"));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(menuTitle);
        centerPanel.add(buttonPanel);

        add(centerPanel, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(255, 215, 0)); // Dorado
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }
}
