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

public class Principal {
    public static void main(String[] args) {
        // Crear el gestor de trenes
        GestorTren gestorTren = new GestorTren();

        // Mostrar la ventana de gestión de trenes
        SwingUtilities.invokeLater(() -> new GestionTrenesGUI(gestorTren));
    }
}