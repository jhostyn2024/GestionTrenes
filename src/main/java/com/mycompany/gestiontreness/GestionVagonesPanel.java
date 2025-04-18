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

public class GestionVagonesPanel extends JPanel {
    public GestionVagonesPanel(JFrame frame) {
        setLayout(new BorderLayout());
        setBackground(new Color(244, 244, 244)); // Fondo claro

        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(26, 26, 79)); // #1a1a4f
        JLabel title = new JLabel("MEDINET");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        header.add(title);
        add(header, BorderLayout.NORTH);

        // Contenido
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(new Color(244, 244, 244));
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel menuLabel = new JLabel("MENU GESTIÓN DE VAGONES");
        menuLabel.setFont(new Font("Arial", Font.BOLD, 24));
        menuLabel.setForeground(new Color(212, 175, 55)); // #d4af37
        menuLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(menuLabel);
        container.add(Box.createRigidArea(new Dimension(0, 20)));

        container.add(createStyledButton("AGREGAR"));
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(createStyledButton("DISPONIBILIDAD"));
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(createStyledButton("ELIMINAR O MODIFICAR"));

        add(container, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(new Color(212, 175, 55)); // #d4af37
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }
}
