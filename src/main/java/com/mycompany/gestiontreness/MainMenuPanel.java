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

public class MainMenuPanel extends JPanel {
    public MainMenuPanel(JFrame frame) {
        setLayout(new BorderLayout());

        // Encabezado
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 51, 102));
        JLabel titleLabel = new JLabel("MEDINET");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        headerPanel.add(titleLabel);

        // Botones de menú
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(4, 1, 20, 20));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));
        menuPanel.setBackground(new Color(240, 240, 240));

        JButton gestionGeneralButton = createStyledButton("Gestión General");
        JButton gestionHorariosButton = createStyledButton("Gestión de Horarios");
        JButton gestionRutasButton = createStyledButton("Gestión Rutas");

        // Acciones de botones
        gestionGeneralButton.addActionListener(e -> frame.setContentPane(new GestionGeneralPanel(frame)));
        gestionHorariosButton.addActionListener(e -> frame.setContentPane(new GestionHorariosPanel(frame)));
        gestionRutasButton.addActionListener(e -> frame.setContentPane(new GestionRutasPanel(frame)));

        menuPanel.add(gestionGeneralButton);
        menuPanel.add(gestionHorariosButton);
        menuPanel.add(gestionRutasButton);

        add(headerPanel, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(205, 163, 74));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }
}
