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
        // Datos de ejemplo
        empleados.add(new Empleado("EMP-001", "Juan Pérez", "12345678", "Conductor", "987654321"));
        empleados.add(new Empleado("EMP-002", "María López", "87654321", "Mantenimiento", "987123456"));
    }

    public static synchronized GestorEmpleados getInstance() {
        if (instance == null) {
            instance = new GestorEmpleados();
        }
        return instance;
    }

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public boolean eliminarEmpleado(String idEmpleado) {
        return empleados.removeIf(e -> e.getIdEmpleado().equals(idEmpleado));
    }

    public List<Empleado> getEmpleados() {
        return new ArrayList<>(empleados);
    }

    public boolean actualizarEmpleado(String idOriginal, Empleado empleadoActualizado) {
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getIdEmpleado().equals(idOriginal)) {
                empleados.set(i, empleadoActualizado);
                return true;
            }
        }
        return false;
    }
}