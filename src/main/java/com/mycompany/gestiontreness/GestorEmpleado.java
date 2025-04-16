/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

/**
 *
 * @author jhost
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestorEmpleado {
    private List<Empleado> empleados;
    private final File archivo = new File("empleados.json");
    private final ObjectMapper mapper = new ObjectMapper();

    public GestorEmpleado() {
        empleados = cargarEmpleados();
        if (empleados.isEmpty()) {
            // Registrar el admin por defecto si no hay empleados
            Empleado admin = new Empleado("1", "Administrador", "Admin", "admin", "admin123");
            agregarEmpleado(admin);
        }
    }

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
        guardarEmpleados();
    }

    public void eliminarEmpleado(String id) {
        empleados.removeIf(empleado -> empleado.getId().equals(id));
        guardarEmpleados();
    }

    public List<Empleado> listarEmpleados() {
        return empleados; // Devuelve la lista de empleados
    }

    private void guardarEmpleados() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(archivo, empleados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Empleado> cargarEmpleados() {
        if (!archivo.exists()) return new ArrayList<>();
        try {
            return mapper.readValue(archivo, new TypeReference<List<Empleado>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

