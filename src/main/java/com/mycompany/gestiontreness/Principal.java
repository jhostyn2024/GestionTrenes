/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

/**
 *
 * @author jhost
 */


public class Principal {
    public static void main(String[] args) {
        Controlador controlador = new Controlador(); // Crear el controlador
        new LoginGUI(controlador); // Iniciar la GUI de inicio de sesión
    }
}
