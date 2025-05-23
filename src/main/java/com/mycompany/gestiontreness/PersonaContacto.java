/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PersonaContacto {
    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("apellido")
    private String apellido;

    @JsonProperty("telefonos")
    private List<String> telefonos;

    // Constructor sin argumentos
    public PersonaContacto() {
    }

    public PersonaContacto(String nombre, String apellido, List<String> telefonos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefonos = telefonos;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public List<String> getTelefonos() { return telefonos; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setTelefonos(List<String> telefonos) { this.telefonos = telefonos; }
}