/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

/**
 *
 * @author jhost
 */


public class Empleado {
    private String idEmpleado;
    private String nombre;
    private String dni;
    private String cargo;
    private String telefono;

    public Empleado(String idEmpleado, String nombre, String dni, String cargo, String telefono) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.dni = dni;
        this.cargo = cargo;
        this.telefono = telefono;
    }

    // Getters
    public String getIdEmpleado() { return idEmpleado; }
    public String getNombre() { return nombre; }
    public String getDni() { return dni; }
    public String getCargo() { return cargo; }
    public String getTelefono() { return telefono; }
}