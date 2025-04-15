/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

/**
 *
 * @author jhost
 */
import java.util.ArrayList;
import java.util.List;

public class GestorEmpleado {
    private List<Empleado> empleados;

    public GestorEmpleado() {
        empleados = new ArrayList<>();
        // Agregar un empleado de ejemplo
        empleados.add(new Empleado("admin", "admin123"));
    }

    public boolean autenticarEmpleado(String username, String password) {
        for (Empleado empleado : empleados) {
            if (empleado.getUsername().equals(username) && empleado.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

}
