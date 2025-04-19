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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class LoginPanel extends JPanel {
    private JFrame frame;

    public LoginPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240)); // Fondo gris claro
        initializeUI();
    }

    private void initializeUI() {
        // 1. Crear el encabezado
        createHeader();

        // 2. Crear el formulario de login
        createLoginForm();
    }

    private void createHeader() {
        JPanel header = new JPanel();
        header.setBackground(new Color(26, 38, 116)); // Azul oscuro corporativo
        header.setPreferredSize(new Dimension(600, 100));
        
        JLabel title = new JLabel("MEDINET", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        
        header.add(title);
        add(header, BorderLayout.NORTH);
    }

    private void createLoginForm() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));
        formPanel.setBackground(Color.WHITE);

        // Campo de usuario
        JTextField userField = new JTextField(20);
        userField.setText("Usuario");
        userField.setForeground(Color.GRAY);
        userField.addFocusListener(new PlaceholderFocusListener("Usuario", userField));

        // Campo de contraseña
        JPasswordField passField = new JPasswordField(20);
        passField.setText("Contraseña");
        passField.setForeground(Color.GRAY);
        passField.setEchoChar((char)0); // Mostrar texto normal inicialmente
        passField.addFocusListener(new PasswordFocusListener(passField));

        // Agregar campos al formulario
        addFormField(formPanel, "Usuario:", userField);
        addFormField(formPanel, "Contraseña:", passField);

        // Botón de login
        JButton loginButton = createLoginButton(userField, passField);
        formPanel.add(Box.createVerticalStrut(20));
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

    private JButton createLoginButton(JTextField userField, JPasswordField passField) {
        JButton button = new JButton("INICIAR SESIÓN");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(new Color(205, 163, 74)); // Dorado corporativo
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        
        // Efecto hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(225, 183, 94)); // Dorado más claro
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(205, 163, 74)); // Dorado original
            }
        });
        
        button.addActionListener(e -> authenticate(userField, passField));
        
        return button;
    }

    private void authenticate(JTextField userField, JPasswordField passField) {
        String username = userField.getText().trim();
        String password = new String(passField.getPassword()).trim();

        // Validar campos vacíos
        if (username.isEmpty() || username.equals("Usuario") || 
            password.isEmpty() || password.equals("Contraseña")) {
            showErrorMessage("Por favor ingrese usuario y contraseña");
            return;
        }

        // Validar credenciales (en un sistema real esto sería con base de datos)
        if (username.equals("admin") && password.equals("admin123")) {
            frame.setContentPane(new MainMenuPanel(frame));
            frame.revalidate();
        } else {
            showErrorMessage("Credenciales incorrectas");
            passField.setText("");
        }
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(
            frame, 
            message, 
            "Error de autenticación", 
            JOptionPane.ERROR_MESSAGE
        );
    }

    // Clases internas para manejar los placeholders
    private class PlaceholderFocusListener extends FocusAdapter {
        private String placeholder;
        private JTextField textField;

        public PlaceholderFocusListener(String placeholder, JTextField textField) {
            this.placeholder = placeholder;
            this.textField = textField;
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (textField.getText().equals(placeholder)) {
                textField.setText("");
                textField.setForeground(Color.BLACK);
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (textField.getText().isEmpty()) {
                textField.setForeground(Color.GRAY);
                textField.setText(placeholder);
            }
        }
    }

    private class PasswordFocusListener extends FocusAdapter {
        private JPasswordField passwordField;

        public PasswordFocusListener(JPasswordField passwordField) {
            this.passwordField = passwordField;
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (String.valueOf(passwordField.getPassword()).equals("Contraseña")) {
                passwordField.setText("");
                passwordField.setForeground(Color.BLACK);
                passwordField.setEchoChar('*'); // Mostrar caracteres de contraseña
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (passwordField.getPassword().length == 0) {
                passwordField.setEchoChar((char)0);
                passwordField.setForeground(Color.GRAY);
                passwordField.setText("Contraseña");
            }
        }
    }
}