/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tren {
    @JsonProperty("idTren")
    private String idTren;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("capacidadTotal")
    private int capacidadTotal;

    public Tren() {
    }

    public Tren(String idTren, String nombre, int capacidadTotal) {
        this.idTren = idTren;
        this.nombre = nombre;
        this.capacidadTotal = capacidadTotal;
    }

    Tren(String idTren, String nombre, String tipoTren, double kilometraje) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getIdTren() { return idTren; }
    public String getNombre() { return nombre; }
    public int getCapacidadTotal() { return capacidadTotal; }

    public void setIdTren(String idTren) { this.idTren = idTren; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCapacidadTotal(int capacidadTotal) { this.capacidadTotal = capacidadTotal; }

    Object getKilometraje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    String getTipoTren() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Object getCapacidadCarga() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}