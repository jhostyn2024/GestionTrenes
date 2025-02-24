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

public class Empleado {
    private String nombre;
    private String identificacion;
    private List<Tren> trenes;
    private List<Ruta> rutas;

    
    public Empleado(String nombre, String identificacion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public List<Tren> getTrenes() {
        return trenes;
    }

    public void setTrenes(List<Tren> trenes) {
        this.trenes = trenes;
    }

    public List<Ruta> getRutas() {
        return rutas;
    }

    public void setRutas(List<Ruta> rutas) {
        this.rutas = rutas;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", identificacion='" + identificacion + '\'' +
                '}';
    }
}
