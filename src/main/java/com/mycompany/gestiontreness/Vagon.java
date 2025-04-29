/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vagon {
    @JsonProperty("idVagon")
    private String idVagon;

    @JsonProperty("idTren")
    private String idTren;

    @JsonProperty("tipo")
    private String tipo;

    @JsonProperty("capacidad")
    private int capacidad;

    @JsonProperty("estado")
    private String estado;

    public Vagon() {}

    public Vagon(String idVagon, String idTren, String tipo, int capacidad, String estado) {
        this.idVagon = idVagon;
        this.idTren = idTren;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.estado = estado;
    }

    public String getIdVagon() { return idVagon; }
    public String getIdTren() { return idTren; }
    public String getTipo() { return tipo; }
    public int getCapacidad() { return capacidad; }
    public String getEstado() { return estado; }

    public void setIdVagon(String idVagon) { this.idVagon = idVagon; }
    public void setIdTren(String idTren) { this.idTren = idTren; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
    public void setEstado(String estado) { this.estado = estado; }
}