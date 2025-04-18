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

class GestionGeneralPanel extends JPanel {
    public GestionGeneralPanel(JFrame frame) {
        setLayout(new BorderLayout());

        // Encabezado
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 51, 102));
        headerPanel.setLayout(new FlowLayout());

        JLabel headerLabel = new JLabel("MEDINET");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerPanel.add(headerLabel);

        add(headerPanel, BorderLayout.NORTH);

        // Título
        JLabel titleLabel = new JLabel("GESTION GENERAL", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(184, 134, 11));
        add(titleLabel, BorderLayout.CENTER);

        // Botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton gestionTrenesButton = new JButton("GESTION TRENES");
        JButton gestionVagonesButton = new JButton("GESTION VAGONES");

        gestionTrenesButton.setBackground(new Color(184, 134, 11));
        gestionTrenesButton.setForeground(Color.WHITE);
        gestionVagonesButton.setBackground(new Color(184, 134, 11));
        gestionVagonesButton.setForeground(Color.WHITE);

        // Acción para abrir Gestión Trenes
        gestionTrenesButton.addActionListener(e -> {
            frame.setContentPane(new GestionTrenesPanel(frame));
            frame.revalidate();
            frame.repaint();
        });

        // Acción para abrir Gestión Vagones
        gestionVagonesButton.addActionListener(e -> {
            frame.setContentPane(new GestionVagonesPanel(frame));
            frame.revalidate();
            frame.repaint();
        });

        buttonPanel.add(gestionTrenesButton);
        buttonPanel.add(gestionVagonesButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}
