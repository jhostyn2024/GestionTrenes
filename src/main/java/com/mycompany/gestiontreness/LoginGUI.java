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

public class LoginGUI {
    private JFrame frame;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private GestorEmpleado gestorEmpleado;

    public LoginGUI() {
        gestorEmpleado = new GestorEmpleado();
        frame = new JFrame("Login de Empleados");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Crear un panel para los campos de texto y el botón
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado

        // Etiqueta y campo de texto para el nombre de usuario
        JLabel lblUsername = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblUsername, gbc);

        txtUsername = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtUsername, gbc);

        // Etiqueta y campo de texto para la contraseña
        JLabel lblPassword = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblPassword, gbc);

        txtPassword = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(txtPassword, gbc);

        // Botón de login
        JButton btnLogin = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Ocupa dos columnas
        panel.add(btnLogin, gbc);

        // Agregar el panel al marco
        frame.add(panel, BorderLayout.CENTER);

        // Acción del botón de login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());
                if (gestorEmpleado.autenticarEmpleado(username, password)) {
                    JOptionPane.showMessageDialog(frame, "Login exitoso!");
                    mostrarAdminGUI(); // Mostrar menú administrativo
                } else {
                    JOptionPane.showMessageDialog(frame, "Credenciales incorrectas. Intente de nuevo.");
                }
            }
        });

        // Configurar el tamaño y hacer que se ajuste al contenido
        frame.pack();
        frame.setLocationRelativeTo(null); // Centrar la ventana
        frame.setVisible(true);
    }

    // Método para mostrar la interfaz administrativa
    private void mostrarAdminGUI() {
        frame.dispose(); // Cerrar la ventana de login
        new AdminGUI(); // Abrir la interfaz administrativa
    }

    public static void main(String[] args) {
        new LoginGUI(); // Iniciar la aplicación
    }
}