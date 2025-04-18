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

public class GestionTrenesPanel extends JPanel {
    public GestionTrenesPanel(JFrame frame) {
        setLayout(new BorderLayout());
        setBackground(new Color(244, 244, 244)); // fondo claro

        // Encabezado
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(30, 58, 138)); // Azul oscuro
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JLabel titleLabel = new JLabel("MEDINET");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerPanel.add(titleLabel);

        add(headerPanel, BorderLayout.NORTH);

        // Contenido central
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));

        JLabel subTitle = new JLabel("MENÚ GESTIÓN DE TRENES");
        subTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subTitle.setFont(new Font("Arial", Font.BOLD, 24));
        subTitle.setForeground(Color.DARK_GRAY);
        contentPanel.add(subTitle);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Botones
        contentPanel.add(createStyledButton("AGREGAR"));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(createStyledButton("DISPONIBILIDAD"));
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(createStyledButton("ELIMINAR O MODIFICAR"));

        add(contentPanel, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(new Color(205, 163, 74)); // Color dorado
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }
}

