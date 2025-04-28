/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

public class LoginPanel extends JPanel {
    private JFrame frame;

    public LoginPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        initializeUI();
    }

    private void initializeUI() {
        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(0, 86, 179));
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

        JTextField userField = new JTextField(20);
        JPasswordField passField = new JPasswordField(20);

        // Configurar placeholders
        setPlaceholder(userField, "Usuario");
        setPlaceholder(passField, "Contraseña");

        addFormField(formPanel, "Usuario:", userField);
        addFormField(formPanel, "Contraseña:", passField);

        JButton loginButton = new JButton("INICIAR SESIÓN");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setBackground(new Color(198, 168, 77));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.addActionListener(e -> authenticate(userField, passField));

        formPanel.add(Box.createVerticalStrut(30));
        formPanel.add(loginButton);

        add(new JScrollPane(formPanel), BorderLayout.CENTER);
    }

    private void authenticate(JTextField userField, JPasswordField passField) {
        String username = userField.getText().trim();
        String password = new String(passField.getPassword()).trim();

        Map<String, String> admins = Map.of(
            "admin1", "clave123",
            "admin2", "password456",
            "jhost", "medinet2023"
        );
        
        Map<String, String> pasajeros = Map.of(
            "pasajero1", "clave123",
            "pasajero2", "viaje2024",
            "invitado", "guest"
        );

        if (admins.containsKey(username) && admins.get(username).equals(password)) {
            frame.setContentPane(new MainMenuPanel(frame));
        } 
        else if (pasajeros.containsKey(username) && pasajeros.get(username).equals(password)) {
            frame.setContentPane(new MenuPasajerosPanel(frame));
        } 
        else {
            JOptionPane.showMessageDialog(frame, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
            passField.setText("");
            return;
        }
        frame.revalidate();
    }

    private void setPlaceholder(JTextField field, String placeholder) {
        field.setText(placeholder);
        field.setForeground(Color.GRAY);
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setForeground(Color.GRAY);
                    field.setText(placeholder);
                }
            }
        });
    }

    private void addFormField(JPanel panel, String label, JComponent field) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        fieldPanel.setBackground(Color.WHITE);
        
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        
        field.setPreferredSize(new Dimension(200, 30));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        
        fieldPanel.add(lbl);
        fieldPanel.add(field);
        panel.add(fieldPanel);
        panel.add(Box.createVerticalStrut(15));
    }
}