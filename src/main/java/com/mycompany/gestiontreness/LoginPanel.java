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
    private JFrame frame;

    public LoginPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUI();
    }

    private void createUI() {
        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(26, 38, 116));
        header.setPreferredSize(new Dimension(800, 120));
        
        JLabel title = new JLabel("MEDINET", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        header.add(title);
        
        add(header, BorderLayout.NORTH);

        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));
        formPanel.setBackground(Color.WHITE);

        JLabel loginTitle = new JLabel("INICIO DE SESIÓN", SwingConstants.CENTER);
        loginTitle.setFont(new Font("Arial", Font.BOLD, 20));
        loginTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(loginTitle);
        formPanel.add(Box.createVerticalStrut(30));

        JTextField userField = new JTextField(20);
        JPasswordField passField = new JPasswordField(20);

        addFormField(formPanel, "Usuario:", userField);
        addFormField(formPanel, "Contraseña:", passField);

        JButton loginButton = new JButton("INGRESAR");
        loginButton.setBackground(new Color(205, 163, 74));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(e -> authenticate(userField, passField));

        formPanel.add(Box.createVerticalStrut(30));
        formPanel.add(loginButton);

        add(formPanel, BorderLayout.CENTER);
    }

    private void addFormField(JPanel panel, String label, JComponent field) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        fieldPanel.setBackground(Color.WHITE);
        
        JLabel lbl = new JLabel(label);
        lbl.setPreferredSize(new Dimension(100, 25));
        
        field.setPreferredSize(new Dimension(200, 30));
        
        fieldPanel.add(lbl);
        fieldPanel.add(field);
        panel.add(fieldPanel);
        panel.add(Box.createVerticalStrut(15));
    }

    private void authenticate(JTextField userField, JPasswordField passField) {
        String username = userField.getText().trim();
        String password = new String(passField.getPassword()).trim();

        if (username.equals("admin") && password.equals("admin123")) {
            frame.setContentPane(new MainMenuPanel(frame));
            frame.revalidate();
        } else {
            JOptionPane.showMessageDialog(
                frame, 
                "Usuario o contraseña incorrectos", 
                "Error de autenticación", 
                JOptionPane.ERROR_MESSAGE
            );
            passField.setText("");
        }
    }
}