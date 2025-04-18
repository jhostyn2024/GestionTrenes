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
import java.awt.event.ActionListener;

class LoginPanel extends JPanel {
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginPanel() {
        setLayout(new BorderLayout()); // Usar BorderLayout para colocar el encabezado y el formulario

        // Encabezado
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 51, 102)); // Color azul
        headerPanel.setLayout(new FlowLayout());
        
        JLabel headerLabel = new JLabel("MEDINET");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Tamaño de fuente
        headerPanel.add(headerLabel);
        
        add(headerPanel, BorderLayout.NORTH); // Agregar el encabezado en la parte superior

        // Panel de Login
        JPanel loginFormPanel = new JPanel();
        loginFormPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes

        // Título
        JLabel titleLabel = new JLabel("INICIA SESION", SwingConstants.CENTER);
        titleLabel.setForeground(new Color(214, 158, 46));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Ocupa dos columnas
        loginFormPanel.add(titleLabel, gbc);

        // Etiqueta y campo de correo electrónico
        JLabel emailLabel = new JLabel("CORREO ELECTRONICO*");
        gbc.gridwidth = 1; // Restablecer a una columna
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginFormPanel.add(emailLabel, gbc);

        emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(250, 30)); // Tamaño preferido
        gbc.gridx = 1;
        loginFormPanel.add(emailField, gbc);

        // Etiqueta y campo de contraseña
        JLabel passwordLabel = new JLabel("CONTRASEÑA*");
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginFormPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(250, 30)); // Tamaño preferido
        gbc.gridx = 1;
        loginFormPanel.add(passwordField, gbc);

        // Botón de inicio de sesión
        JButton loginButton = new JButton("INGRESAR");
        loginButton.setBackground(new Color(214, 158, 46));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(new LoginAction());
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Ocupa dos columnas
        loginFormPanel.add(loginButton, gbc);

        add(loginFormPanel, BorderLayout.CENTER); // Agregar el formulario de inicio de sesión en el centro
    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (email.equals("admin") && password.equals("admin123")) {
                JOptionPane.showMessageDialog(LoginPanel.this, "Inicio de sesión exitoso!");
            } else {
                JOptionPane.showMessageDialog(LoginPanel.this, "Correo o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}