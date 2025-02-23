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

public class Ruta {
    private String origen;
    private String destino;
    private List<String> estacionesIntermedias;
    private double distanciaTotal; // En kilómetros

    // Constructor
    public Ruta(String origen, String destino, List<String> estacionesIntermedias, double distanciaTotal) {
        this.origen = origen;
        this.destino = destino;
        this.estacionesIntermedias = estacionesIntermedias;
        this.distanciaTotal = distanciaTotal;
    }

    // Getters y setters
    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public List<String> getEstacionesIntermedias() {
        return estacionesIntermedias;
    }

    public void setEstacionesIntermedias(List<String> estacionesIntermedias) {
        this.estacionesIntermedias = estacionesIntermedias;
    }

    public double getDistanciaTotal() {
        return distanciaTotal;
    }

    public void setDistanciaTotal(double distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
    }

    @Override
    public String toString() {
        return "Ruta{" +
                "origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", estacionesIntermedias=" + estacionesIntermedias +
                ", distanciaTotal=" + distanciaTotal +
                '}';
    }
}