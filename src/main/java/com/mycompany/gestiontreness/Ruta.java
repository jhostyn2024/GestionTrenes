/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ruta {
    @JsonProperty("idRuta")
    private String idRuta;

    @JsonProperty("estacionOrigen")
    private String estacionOrigen;

    @JsonProperty("estacionDestino")
    private String estacionDestino;

    @JsonProperty("distancia")
    private double distancia;

    @JsonProperty("estado")
    private String estado;

    public Ruta() {}

    public Ruta(String idRuta, String estacionOrigen, String estacionDestino, double distancia, String estado) {
        this.idRuta = idRuta;
        this.estacionOrigen = estacionOrigen;
        this.estacionDestino = estacionDestino;
        this.distancia = distancia;
        this.estado = estado;
    }

    public String getIdRuta() { return idRuta; }
    public String getEstacionOrigen() { return estacionOrigen; }
    public String getEstacionDestino() { return estacionDestino; }
    public double getDistancia() { return distancia; }
    public String getEstado() { return estado; }

    public void setIdRuta(String idRuta) { this.idRuta = idRuta; }
    public void setEstacionOrigen(String estacionOrigen) { this.estacionOrigen = estacionOrigen; }
    public void setEstacionDestino(String estacionDestino) { this.estacionDestino = estacionDestino; }
    public void setDistancia(double distancia) { this.distancia = distancia; }
    public void setEstado(String estado) { this.estado = estado; }
}