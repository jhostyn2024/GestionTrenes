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

public class GestionGeneralPanel extends JPanel {
    public GestionGeneralPanel(JFrame frame) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("MEDINET", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);

        JPanel header = new JPanel();
        header.setBackground(new Color(30, 58, 138));
        header.setPreferredSize(new Dimension(600, 80));
        header.add(title);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBackground(new Color(244, 244, 244));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        String[] options = {"AGREGAR", "DISPONIBILIDAD", "ELIMINAR O MODIFICAR"};
        for (String text : options) {
            JButton button = new JButton(text);
            button.setBackground(new Color(205, 163, 74));
            button.setForeground(Color.WHITE);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(300, 50));
            buttonsPanel.add(button);
            buttonsPanel.add(Box.createVerticalStrut(20));
        }

        add(header, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.CENTER);
    }
}

