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
    private String id;
    private String nombre;
    private String cargo;
    private String usuario;
    private String contrasena;

    public Empleado(String id, String nombre, String cargo, String usuario, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.cargo = cargo;
        this.usuario = usuario;
        this.contrasena = contrasena; // En un entorno real, deberías encriptar esta contraseña
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public String getUsuario() {
        return usuario; // Método para obtener el usuario
    }

    public String getContrasena() {
        return contrasena; // Método para obtener la contraseña
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", cargo='" + cargo + '\'' +
                ", usuario='" + usuario + '\'' +
                '}';
    }
}
