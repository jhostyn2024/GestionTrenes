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

public class AgregarTrenPanel extends JPanel {
    public AgregarTrenPanel(JFrame frame) {
        setLayout(new BorderLayout());

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(26, 38, 116));
        JLabel title = new JLabel("MEDINET", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        JButton backButton = new JButton("<");
        backButton.setBackground(new Color(205, 163, 74));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> frame.setContentPane(new GestionTrenesPanel(frame)));

        header.add(backButton, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        addField(formPanel, "FOTO TREN (placeholder)", new JButton("+"));
        addField(formPanel, "MARCA:", new JTextField(15));
        addField(formPanel, "IDENTIFICADOR:", new JTextField(15));
        addField(formPanel, "CAPACIDAD:", new JTextField(15));
        addField(formPanel, "HORA SALIDA:", new JTextField(15));
        addField(formPanel, "HORA LLEGADA:", new JTextField(15));
        addField(formPanel, "VAGONES:", new JTextField(15));
        addField(formPanel, "RUTA:", new JTextField(15));

        JButton continuar = new JButton("CONTINUAR");
        continuar.setAlignmentX(Component.CENTER_ALIGNMENT);
        continuar.setBackground(new Color(205, 163, 74));
        continuar.setForeground(Color.WHITE);
        continuar.setFocusPainted(false);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(continuar);

        add(header, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }

    private void addField(JPanel panel, String labelText, JComponent input) {
        JPanel field = new JPanel(new FlowLayout(FlowLayout.LEFT));
        field.setBackground(Color.WHITE);
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(180, 25));
        field.add(label);
        field.add(input);
        panel.add(field);
    }
}

