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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnLogin;

    public Login() {
        setTitle("Inicio de Sesión");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(10, 20, 80, 25);
        panel.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(100, 20, 165, 25);
        panel.add(txtUsuario);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(10, 50, 80, 25);
        panel.add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(100, 50, 165, 25);
        panel.add(txtContrasena);

        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBounds(100, 100, 120, 25);
        panel.add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String contrasena = new String(txtContrasena.getPassword());

                // Validar usuario y contraseña (esto es un ejemplo básico)
                if (usuario.equals("empleaado") && contrasena.equals("proyectoAula")) {
                    new GestionTrenes().setVisible(true);
                    dispose(); // Cierra la ventana de inicio de sesión
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }
            }
        });

        add(panel);
    }

    public void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}