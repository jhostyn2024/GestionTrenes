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

public class GestionHorariosPanel extends JPanel {
    public GestionHorariosPanel(JFrame frame) {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 242)); // fondo claro

        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(26, 38, 116)); // Azul oscuro
        header.setLayout(new FlowLayout(FlowLayout.LEFT));
        header.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel title = new JLabel("MEDINET");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        // Contenido principal
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        mainContent.setBackground(new Color(240, 240, 242));

        JLabel subtitle = new JLabel("MENU GESTION DE HORARIOS");
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setForeground(new Color(201, 162, 39)); // Dorado
        subtitle.setFont(new Font("Arial", Font.BOLD, 24));
        subtitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
        mainContent.add(subtitle);

        // Botones en grid
        JPanel buttonsContainer = new JPanel();
        buttonsContainer.setLayout(new GridLayout(2, 2, 20, 20));
        buttonsContainer.setMaximumSize(new Dimension(800, 200));
        buttonsContainer.setBackground(new Color(240, 240, 242));

        buttonsContainer.add(createStyledButton("AGREGAR"));
        buttonsContainer.add(createStyledButton("DISPONIBILIDAD"));
        buttonsContainer.add(createStyledButton("ELIMINAR O MODIFICAR"));
        buttonsContainer.add(new JLabel()); // espacio vacío para mantener simetría

        mainContent.add(buttonsContainer);
        add(mainContent, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(201, 162, 39)); // Dorado
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }
}
