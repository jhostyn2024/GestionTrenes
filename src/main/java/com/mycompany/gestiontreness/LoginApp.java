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

public class LoginApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Iniciar Sesión");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300); // Tamaño inicial de la ventana
            frame.setLocationRelativeTo(null);
            frame.add(new LoginPanel(frame)); // Pasar el frame para cambiar a la ventana del menú
            frame.setVisible(true);
        });
    }
}