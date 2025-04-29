/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;

public class LoginApp extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private final Color BLUE_COLOR = new Color(0, 86, 179);
    private final Color GOLD_COLOR = new Color(198, 168, 77);

    public LoginApp() {
        setTitle("Login - Gestiontreness");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        createUI();
    }

    private void createUI() {
        JPanel header = new JPanel();
        header.setBackground(BLUE_COLOR);
        header.setPreferredSize(new Dimension(400, 80));
        JLabel title = new JLabel("INICIAR SESIÓN", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        header.add(title);
        add(header, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        txtUsuario = new JTextField();
        txtContrasena = new JPasswordField();

        addFormField(formPanel, "Usuario:", txtUsuario);
        addFormField(formPanel, "Contraseña:", txtContrasena);

        JButton btnLogin = new JButton("INGRESAR");
        btnLogin.setBackground(GOLD_COLOR);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogin.addActionListener(e -> login());

        formPanel.add(btnLogin);

        add(formPanel, BorderLayout.CENTER);
    }

    private void addFormField(JPanel panel, String label, JComponent field) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(150, 30));
        panel.add(lbl);
        panel.add(field);
    }

    private void login() {
        String usuario = txtUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword()).trim();

        // Simulación de autenticación (reemplaza con tu lógica real)
        if (usuario.equals("admin") && contrasena.equals("1234")) {
            System.out.println("Login exitoso, navegando a MainMenuPanel");
            setContentPane(new MainMenuPanel(this));
            revalidate();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Login fallido: usuario=" + usuario);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginApp app = new LoginApp();
            app.setVisible(true);
        });
    }
}