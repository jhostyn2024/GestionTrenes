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
    private Controlador controlador;

    public LoginGUI(Controlador controlador) {
        this.controlador = controlador; // Recibe el controlador

        // Crear el marco de inicio de sesión
        JFrame frame = new JFrame("Inicio de Sesión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Centramos la ventana utilizando el Toolkit para obtener la resolución de la pantalla
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2; // Centro en X
        int y = (screenSize.height - frame.getHeight()) / 2; // Centro en Y
        frame.setLocation(x, y); // Ajusta la posición en el centro de la pantalla

        // Crear un panel para el contenido
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2)); // Layout de cuadrícula

        // Campos de entrada
        JLabel usuarioLabel = new JLabel("Usuario:");
        JTextField usuarioField = new JTextField();
        JLabel contrasenaLabel = new JLabel("Contraseña:");
        JPasswordField contrasenaField = new JPasswordField();

        // Botón de inicio de sesión
        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioField.getText();
                String contrasena = new String(contrasenaField.getPassword());

                // Verificar las credenciales
                if (controlador.verificarCredenciales(usuario, contrasena)) {
                    // Si las credenciales son correctas, abrir el menú principal
                    new MenuPrincipalGUI(controlador);
                    frame.dispose(); // Cerrar la ventana de inicio de sesión
                } else {
                    JOptionPane.showMessageDialog(frame, "Credenciales incorrectas. Inténtalo de nuevo.");
                }
            }
        });

        // Agregar componentes al panel
        panel.add(usuarioLabel);
        panel.add(usuarioField);
        panel.add(contrasenaLabel);
        panel.add(contrasenaField);
        panel.add(btnLogin);

        // Agregar el panel al marco
        frame.add(panel);
        frame.setVisible(true); // Hacer visible la ventana
    }
}

