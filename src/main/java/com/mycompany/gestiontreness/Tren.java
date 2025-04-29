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

    @JsonProperty("capacidad")
    private int capacidad;

    @JsonProperty("estado")
    private String estado;

    public Tren() {}

    public Tren(String idTren, String nombre, int capacidad, String estado) {
        this.idTren = idTren;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.estado = estado;
    }

    public String getIdTren() { return idTren; }
    public String getNombre() { return nombre; }
    public int getCapacidad() { return capacidad; }
    public String getEstado() { return estado; }

    public void setIdTren(String idTren) { this.idTren = idTren; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
    public void setEstado(String estado) { this.estado = estado; }
}