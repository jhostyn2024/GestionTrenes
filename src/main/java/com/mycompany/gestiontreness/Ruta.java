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

    public Ruta() {
    }

    public Ruta(String idRuta, String estacionOrigen, String estacionDestino, double distancia) {
        this.idRuta = idRuta;
        this.estacionOrigen = estacionOrigen;
        this.estacionDestino = estacionDestino;
        this.distancia = distancia;
    }

    Ruta(String idRuta, String origen, String destino, double distancia, String estado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getters
    public String getIdRuta() { return idRuta; }
    public String getEstacionOrigen() { return estacionOrigen; }
    public String getEstacionDestino() { return estacionDestino; }
    public double getDistancia() { return distancia; }

    // Setters
    public void setIdRuta(String idRuta) { this.idRuta = idRuta; }
    public void setEstacionOrigen(String estacionOrigen) { this.estacionOrigen = estacionOrigen; }
    public void setEstacionDestino(String estacionDestino) { this.estacionDestino = estacionDestino; }
    public void setDistancia(double distancia) { this.distancia = distancia; }

    Object getEstado() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}