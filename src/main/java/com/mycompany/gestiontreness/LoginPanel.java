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
        createHeader();
        createLoginForm(frame);
    }

    private void createHeader() {
        JPanel header = new JPanel();
        header.setBackground(new Color(26, 38, 116));
        header.setPreferredSize(new Dimension(600, 80));
        
        JLabel title = new JLabel("MEDINET", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        header.add(title);
        
        add(header, BorderLayout.NORTH);
    }

    private void createLoginForm(JFrame frame) {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();

        addFormField(formPanel, "Usuario:", userField);
        addFormField(formPanel, "Contraseña:", passField);

        JButton loginBtn = createLoginButton(frame, userField, passField);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(loginBtn);

        add(formPanel, BorderLayout.CENTER);
    }

    private void addFormField(JPanel panel, String label, JComponent field) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fieldPanel.setBackground(Color.WHITE);
        
        JLabel lbl = new JLabel(label);
        lbl.setPreferredSize(new Dimension(100, 25));
        
        field.setPreferredSize(new Dimension(200, 30));
        
        fieldPanel.add(lbl);
        fieldPanel.add(field);
        panel.add(fieldPanel);
        panel.add(Box.createVerticalStrut(10));
    }

    private JButton createLoginButton(JFrame frame, JTextField userField, JPasswordField passField) {
        JButton button = new JButton("INICIAR SESIÓN");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(new Color(205, 163, 74));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        
        button.addActionListener(e -> {
            String usuario = userField.getText().trim();
            String password = new String(passField.getPassword());
            
            if (usuario.equals("admin") && password.equals("admin123")) {
                frame.setContentPane(new MainMenuPanel(frame));
                frame.revalidate();
            } else {
                JOptionPane.showMessageDialog(frame, 
                    "Credenciales incorrectas", 
                    "Error de autenticación", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        
        return button;
    }
}
