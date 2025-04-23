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
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;
import java.util.Map;

public class LoginPanel extends JPanel {
    private JFrame frame;
    private final Color GOLD_COLOR = new Color(198, 168, 77); // Color #C6A84D
    private final Color BLUE_COLOR = new Color(0, 86, 179); // Color azul corporativo

    public LoginPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        initializeUI();
    }

    private void initializeUI() {
        // Header
        JPanel header = new JPanel();
        header.setBackground(BLUE_COLOR);
        header.setPreferredSize(new Dimension(800, 100));
        
        JLabel title = new JLabel("MEDINET", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        header.add(title);
        
        add(header, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 150));
        formPanel.setBackground(Color.WHITE);

        // Usuario Field
        JTextField userField = new JTextField(20);
        userField.setText("Usuario");
        userField.setForeground(Color.GRAY);
        userField.addFocusListener(new PlaceholderFocusListener("Usuario", userField));

        // Password Field
        JPasswordField passField = new JPasswordField(20);
        passField.setText("Contraseña");
        passField.setForeground(Color.GRAY);
        passField.setEchoChar((char)0);
        passField.addFocusListener(new PasswordFocusListener(passField));

        // Add Fields
        addFormField(formPanel, "Usuario:", userField);
        addFormField(formPanel, "Contraseña:", passField);

        // Login Button
        JButton loginButton = new JButton("INICIAR SESIÓN");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setBackground(GOLD_COLOR);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setPreferredSize(new Dimension(250, 40));
        loginButton.addActionListener(e -> authenticate(userField, passField));
        
        formPanel.add(Box.createVerticalStrut(30));
        formPanel.add(loginButton);

        add(new JScrollPane(formPanel), BorderLayout.CENTER);
    }

    private void addFormField(JPanel panel, String label, JComponent field) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        fieldPanel.setBackground(Color.WHITE);
        
        JLabel lbl = new JLabel(label);
        lbl.setPreferredSize(new Dimension(100, 25));
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        
        field.setPreferredSize(new Dimension(200, 30));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        
        fieldPanel.add(lbl);
        fieldPanel.add(field);
        panel.add(fieldPanel);
        panel.add(Box.createVerticalStrut(15));
    }

    private void authenticate(JTextField userField, JPasswordField passField) {
        String username = userField.getText().trim();
        String password = new String(passField.getPassword()).trim();

        // Validar campos vacíos
        if (username.isEmpty() || username.equals("Usuario") || 
            password.isEmpty() || password.equals("Contraseña")) {
            showErrorMessage("Por favor ingrese usuario y contraseña válidos");
            return;
        }

        // Credenciales hardcodeadas (solo para desarrollo)
        Map<String, String> admins = Map.of(
            "admin1", "clave123",
            "admin2", "password456",
            "jhost", "medinet2023"
        );

        if (admins.containsKey(username) && admins.get(username).equals(password)) {
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

    // Clases internas para manejar placeholders
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
                passwordField.setEchoChar('*');
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