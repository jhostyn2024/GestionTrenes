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
        setBackground(new Color(240, 240, 240)); // Fondo gris claro
        
        createHeader();
        createCenteredLoginForm(frame);
    }

    private void createHeader() {
        JPanel header = new JPanel();
        header.setBackground(new Color(26, 38, 116)); // Azul oscuro
        header.setPreferredSize(new Dimension(600, 100));
        header.setLayout(new BorderLayout());
        
        JLabel title = new JLabel("MEDINET", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);
    }

    private void createCenteredLoginForm(JFrame frame) {
        // Panel principal que contendrá el formulario centrado
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(240, 240, 240));
        
        // Constraints para centrar los componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Panel del formulario con borde y sombra
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        formPanel.setBackground(Color.WHITE);
        
        // Título del formulario
        JLabel formTitle = new JLabel("INICIO DE SESIÓN", SwingConstants.CENTER);
        formTitle.setFont(new Font("Arial", Font.BOLD, 20));
        formTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(formTitle);
        formPanel.add(Box.createVerticalStrut(30));
        
        // Campo de usuario
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        userPanel.setBackground(Color.WHITE);
        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setPreferredSize(new Dimension(100, 25));
        JTextField userField = new JTextField(15);
        userPanel.add(userLabel);
        userPanel.add(userField);
        formPanel.add(userPanel);
        formPanel.add(Box.createVerticalStrut(15));
        
        // Campo de contraseña
        JPanel passPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        passPanel.setBackground(Color.WHITE);
        JLabel passLabel = new JLabel("Contraseña:");
        passLabel.setPreferredSize(new Dimension(100, 25));
        JPasswordField passField = new JPasswordField(15);
        passPanel.add(passLabel);
        passPanel.add(passField);
        formPanel.add(passPanel);
        formPanel.add(Box.createVerticalStrut(30));
        
        // Botón de login
        JButton loginBtn = new JButton("INICIAR SESIÓN");
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.setBackground(new Color(205, 163, 74)); // Dorado
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setFont(new Font("Arial", Font.BOLD, 14));
        loginBtn.setPreferredSize(new Dimension(180, 40));
        loginBtn.addActionListener(e -> handleLogin(frame, userField, passField));
        formPanel.add(loginBtn);
        
        // Añadir el formulario al panel centrado
        centerPanel.add(formPanel);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void handleLogin(JFrame frame, JTextField userField, JPasswordField passField) {
        String usuario = userField.getText().trim();
        String password = new String(passField.getPassword());
        
        if (usuario.equals("admin") && password.equals("admin123")) {
            frame.setContentPane(new MainMenuPanel(frame));
            frame.revalidate();
        } else {
            JOptionPane.showMessageDialog(frame, 
                "Credenciales incorrectas. Intente nuevamente.", 
                "Error de autenticación", 
                JOptionPane.ERROR_MESSAGE);
            passField.setText("");
        }
    }
}