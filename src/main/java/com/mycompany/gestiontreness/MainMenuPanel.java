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

class MainMenuPanel extends JPanel {
    public MainMenuPanel(JFrame frame) {
        setLayout(new BorderLayout());

        // Encabezado
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 59, 92));
        headerPanel.setLayout(new FlowLayout());

        JLabel headerLabel = new JLabel("MEDINET");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerPanel.add(headerLabel);

        add(headerPanel, BorderLayout.NORTH);

        // Menú Principal
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(1, 3, 10, 10));

        JButton gestionGeneralButton = createMenuButton("GESTION GENERAL");
        JButton gestionHorariosButton = createMenuButton("GESTION HORARIOS");
        JButton gestionRutasButton = createMenuButton("GESTION RUTAS");

        menuPanel.add(gestionGeneralButton);
        menuPanel.add(gestionHorariosButton);
        menuPanel.add(gestionRutasButton);

        add(menuPanel, BorderLayout.CENTER);

        // Acción para "GESTION GENERAL"
        gestionGeneralButton.addActionListener(e -> {
            frame.setContentPane(new GestionGeneralPanel(frame));
            frame.revalidate();
            frame.repaint();
        });

        // Acción para "GESTION HORARIOS"
        gestionHorariosButton.addActionListener(e -> {
            frame.setContentPane(new GestionHorariosPanel(frame));
            frame.revalidate();
            frame.repaint();
        });

        // El botón de "GESTION RUTAS" aún no tiene acción asignada
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(205, 170, 98));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(120, 40));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }
}
