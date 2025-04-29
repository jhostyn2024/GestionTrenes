/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorEmpleados {
    private static GestorEmpleados instance;
    private List<Empleado> empleados;
    private static final String FILE_PATH = "data/empleados.json";

    private GestorEmpleados() {
        empleados = JsonUtil.readJson(FILE_PATH, Empleado.class);
        System.out.println("GestorEmpleados inicializado con " + empleados.size() + " empleados cargados");
    }

    public static synchronized GestorEmpleados getInstance() {
        if (instance == null) {
            instance = new GestorEmpleados();
        }
        return instance;
    }

    public void agregarEmpleado(Empleado empleado) {
        System.out.println("Agregando empleado con ID: " + empleado.getIdEmpleado());
        empleados.add(empleado);
        JsonUtil.writeJson(FILE_PATH, empleados);
        System.out.println("Empleados almacenados: " + empleados.size());
    }

    public boolean modificarEmpleado(String idEmpleado, String nombre, String apellido, String rol, String estado) {
        System.out.println("Modificando empleado con ID: " + idEmpleado);
        for (Empleado empleado : empleados) {
            if (empleado.getIdEmpleado().equals(idEmpleado)) {
                empleado.setNombre(nombre);
                empleado.setApellido(apellido);
                empleado.setRol(rol);
                empleado.setEstado(estado);
                JsonUtil.writeJson(FILE_PATH, empleados);
                System.out.println("Empleado modificado y almacenado: " + idEmpleado);
                return true;
            }
        }
        System.out.println("Empleado no encontrado para modificar: " + idEmpleado);
        return false;
    }

    public boolean eliminarEmpleado(String idEmpleado) {
        System.out.println("Eliminando empleado con ID: " + idEmpleado);
        boolean removed = empleados.removeIf(empleado -> empleado.getIdEmpleado().equals(idEmpleado));
        if (removed) {
            JsonUtil.writeJson(FILE_PATH, empleados);
            System.out.println("Empleado eliminado y almacenado: " + idEmpleado);
        } else {
            System.out.println("Empleado no encontrado para eliminar: " + idEmpleado);
        }
        return removed;
    }

    public List<Empleado> getEmpleados() {
        return new ArrayList<>(empleados);
    }

    public List<Empleado> getEmpleadosActivos() {
        return empleados.stream()
                .filter(empleado -> "Activo".equals(empleado.getEstado()))
                .collect(Collectors.toList());
    }
}