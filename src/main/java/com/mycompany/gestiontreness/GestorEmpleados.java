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

public class GestorEmpleados {
    private static GestorEmpleados instance;
    private List<Empleado> empleados;

    private GestorEmpleados() {
        empleados = new ArrayList<>();
        // Datos de ejemplo (puedes eliminarlos después)
        empleados.add(new Empleado("EMP-001", "Admin Ejemplo", "12345678", "Administrador", "999888777"));
    }

    public static synchronized GestorEmpleados getInstance() {
        if (instance == null) {
            instance = new GestorEmpleados();
        }
        return instance;
    }

    public List<Empleado> getEmpleados() {
        return new ArrayList<>(empleados); // Devuelve una copia para proteger los datos
    }

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }
}