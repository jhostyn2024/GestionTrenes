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

    public Tren() {
        this.idTren = "";
        this.nombre = "";
        this.estado = "Activo";
    }

    public Tren(String idTren, String nombre, int capacidad, String estado) {
        this.idTren = idTren != null ? idTren : "";
        this.nombre = nombre != null ? nombre : "";
        this.capacidad = capacidad;
        this.estado = estado != null ? estado : "Activo";
    }

    public String getIdTren() { return idTren; }
    public String getNombre() { return nombre; }
    public int getCapacidad() { return capacidad; }
    public String getEstado() { return estado; }

    public void setIdTren(String idTren) { this.idTren = idTren != null ? idTren : ""; }
    public void setNombre(String nombre) { this.nombre = nombre != null ? nombre : ""; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
    public void setEstado(String estado) { this.estado = estado != null ? estado : "Activo"; }

    @Override
    public String toString() {
        return "Tren[idTren=" + (idTren != null ? idTren : "null") +
               ", nombre=" + (nombre != null ? nombre : "null") +
               ", capacidad=" + capacidad +
               ", estado=" + (estado != null ? estado : "null") + "]";
    }

    AbstractStringBuilder getTipoTren() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    AbstractStringBuilder getCapacidadCarga() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}