/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

/**
 *
 * @author jhost
 */
import java.util.List;

public class Controlador {
    private GestorTren gestorTren;
    private GestorRuta gestorRuta;
    private GestorEmpleado gestorEmpleado;

    public Controlador() {
        gestorTren = new GestorTren();
        gestorRuta = new GestorRuta();
        gestorEmpleado = new GestorEmpleado();
    }

    public boolean verificarCredenciales(String usuario, String contrasena) {
        for (Empleado empleado : gestorEmpleado.listarEmpleados()) {
            if (empleado.getUsuario().equals(usuario) && empleado.getContrasena().equals(contrasena)) {
                return true; // Credenciales correctas
            }
        }
        return false; // Credenciales incorrectas
    }

    public List<Empleado> listarEmpleados() {
        return gestorEmpleado.listarEmpleados(); // Devuelve la lista de empleados
    }

    // Otros métodos para gestionar trenes y rutas...
}
