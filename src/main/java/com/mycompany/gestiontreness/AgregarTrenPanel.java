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

        // Encabezado
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(30, 58, 138));
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel title = new JLabel("MEDINET", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        header.add(title, BorderLayout.CENTER);

        JButton backButton = new JButton("<");
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(Color.WHITE);
        backButton.addActionListener(e -> frame.setContentPane(new GestionTrenesPanel(frame)));
        header.add(backButton, BorderLayout.WEST);

        add(header, BorderLayout.NORTH);

        // Contenido principal
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JLabel subtitle = new JLabel("AGREGAR");
        subtitle.setFont(new Font("Arial", Font.BOLD, 22));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setForeground(new Color(30, 58, 138));
        content.add(subtitle);

        content.add(Box.createVerticalStrut(20));

        // FOTO TREN
        JPanel photoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        photoPanel.setMaximumSize(new Dimension(400, 120));
        JLabel photoLabel = new JLabel("FOTO TREN");
        photoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        content.add(photoLabel);

        JLabel placeholder = new JLabel();
        placeholder.setPreferredSize(new Dimension(100, 100));
        placeholder.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JButton addPhotoButton = new JButton("+");
        photoPanel.add(placeholder);
        photoPanel.add(addPhotoButton);
        content.add(photoPanel);

        content.add(createFormGroup("MARCA:"));
        content.add(createFormGroup("IDENTIFICADOR:"));
        content.add(createFormGroup("CAPACIDAD:", JSpinner.class));

        // Hora salida y llegada
        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        timePanel.add(new JLabel("HORA SALIDA Y LLEGADA:"));
        timePanel.add(new JTextField(5));
        timePanel.add(new JTextField(5));
        content.add(timePanel);

        content.add(createFormGroup("VAGONES:", JSpinner.class));
        content.add(createFormGroup("RUTA:"));

        JButton continueButton = new JButton("CONTINUAR");
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        continueButton.setBackground(new Color(205, 163, 74));
        continueButton.setForeground(Color.WHITE);
        continueButton.setFont(new Font("Arial", Font.BOLD, 16));

        content.add(Box.createVerticalStrut(20));
        content.add(continueButton);

        add(content, BorderLayout.CENTER);
    }

    private JPanel createFormGroup(String labelText) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(labelText));
        JTextField textField = new JTextField(20);
        panel.add(textField);
        return panel;
    }

    private JPanel createFormGroup(String labelText, Class<?> fieldType) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(labelText));
        if (fieldType == JSpinner.class) {
            JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
            spinner.setPreferredSize(new Dimension(80, 25));
            panel.add(spinner);
        }
        return panel;
    }
}
