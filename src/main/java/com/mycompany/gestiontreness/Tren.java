/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

public class Tren {
    private String idTren;
    private String nombre;
    private String tipoTren; // "Mercedes-Benz" o "Arnold"
    private int capacidadCarga; // Máximo 28 o 32 vagones
    private double kilometraje;

    public Tren(String idTren, String nombre, String tipoTren, double kilometraje) {
        this.idTren = idTren;
        this.nombre = nombre;
        this.tipoTren = tipoTren;
        this.capacidadCarga = tipoTren.equals("Mercedes-Benz") ? 28 : 32;
        this.kilometraje = kilometraje;
    }

    // Getters
    public String getIdTren() { return idTren; }
    public String getNombre() { return nombre; }
    public String getTipoTren() { return tipoTren; }
    public int getCapacidadCarga() { return capacidadCarga; }
    public double getKilometraje() { return kilometraje; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setKilometraje(double kilometraje) { this.kilometraje = kilometraje; }
}