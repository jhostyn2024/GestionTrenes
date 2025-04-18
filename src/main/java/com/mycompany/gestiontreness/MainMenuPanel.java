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
        headerPanel.setBackground(new Color(0, 59, 92)); // Color azul
        headerPanel.setLayout(new FlowLayout());
        
        JLabel headerLabel = new JLabel("MEDINET");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Tamaño de fuente
        headerPanel.add(headerLabel);
        
        add(headerPanel, BorderLayout.NORTH); // Agregar el encabezado en la parte superior

        // Menú Principal
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(1, 3, 10, 10)); // 1 fila, 3 columnas

        JButton gestionGeneralButton = createMenuButton("GESTION GENERAL");
        JButton gestionHorariosButton = createMenuButton("GESTION HORARIOS");
        JButton gestionRutasButton = createMenuButton("GESTION RUTAS");

        menuPanel.add(gestionGeneralButton);
        menuPanel.add(gestionHorariosButton);
        menuPanel.add(gestionRutasButton);

        add(menuPanel, BorderLayout.CENTER); // Agregar el menú en el centro

        // Acción para el botón de Gestión General
        gestionGeneralButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new GestionGeneralPanel(frame));
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(205, 170, 98));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(120, 40)); // Tamaño preferido para los botones
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Estilo de fuente
        return button;
    }
}