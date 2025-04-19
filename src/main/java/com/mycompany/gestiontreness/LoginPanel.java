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

public class LoginPanel extends JPanel {
    public LoginPanel(JFrame frame) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("MEDINET", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);

        JPanel header = new JPanel();
        header.setBackground(new Color(26, 38, 116));
        header.setPreferredSize(new Dimension(600, 80));
        header.setLayout(new BorderLayout());
        header.add(title, BorderLayout.CENTER);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(Color.WHITE);
        center.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        JLabel userLabel = new JLabel("Correo:");
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Contraseña:");
        JPasswordField passField = new JPasswordField();

        JButton ingresarBtn = new JButton("INGRESAR");
        ingresarBtn.setFont(new Font("Arial", Font.BOLD, 16));
        ingresarBtn.setBackground(new Color(205, 163, 74));
        ingresarBtn.setForeground(Color.WHITE);
        ingresarBtn.setFocusPainted(false);

        ingresarBtn.addActionListener((ActionEvent e) -> {
            String correo = userField.getText().trim();
            String password = new String(passField.getPassword());

            if (correo.equals("admin") && password.equals("admin123")) {
                frame.setContentPane(new MainMenuPanel(frame));
                frame.revalidate();
            } else {
                JOptionPane.showMessageDialog(frame, "Correo o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        Dimension inputSize = new Dimension(200, 30);
        userField.setMaximumSize(inputSize);
        passField.setMaximumSize(inputSize);

        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passField.setAlignmentX(Component.CENTER_ALIGNMENT);
        ingresarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        center.add(userLabel);
        center.add(userField);
        center.add(Box.createVerticalStrut(10));
        center.add(passLabel);
        center.add(passField);
        center.add(Box.createVerticalStrut(20));
        center.add(ingresarBtn);

        add(header, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
    }
}
