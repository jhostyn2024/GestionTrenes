/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import java.util.List;

public class PersonaContacto {
    private String nombre;
    private String apellido;
    private List<String> telefonos;

    public PersonaContacto(String nombre, String apellido, List<String> telefonos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefonos = telefonos;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public List<String> getTelefonos() { return telefonos; }
}