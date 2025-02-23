/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

/**
 *
 * @author jhost
 */



public class Tren {
    private String nombre;
    private String identificador;
    private int capacidadCarga; // Número de vagones
    private double kilometraje;

    
    public Tren(String nombre, String identificador, int capacidadCarga, double kilometraje) {
        this.nombre = nombre;
        this.identificador = identificador;
        this.capacidadCarga = capacidadCarga;
        this.kilometraje = kilometraje;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public int getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(int capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public double getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(double kilometraje) {
        this.kilometraje = kilometraje;
    }

    @Override
    public String toString() {
        return "Tren{" +
                "nombre='" + nombre + '\'' +
                ", identificador='" + identificador + '\'' +
                ", capacidadCarga=" + capacidadCarga +
                ", kilometraje=" + kilometraje +
                '}';
    }
}