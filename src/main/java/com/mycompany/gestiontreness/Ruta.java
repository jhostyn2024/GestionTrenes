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
    private String id;
    private String estacionOrigen;
    private String estacionDestino;
    private double distancia;

    public Ruta(String id, String estacionOrigen, String estacionDestino, double distancia) {
        this.id = id;
        this.estacionOrigen = estacionOrigen;
        this.estacionDestino = estacionDestino;
        this.distancia = distancia;
    }

    public String getId() {
        return id;
    }

    public String getEstacionOrigen() {
        return estacionOrigen;
    }

    public String getEstacionDestino() {
        return estacionDestino;
    }

    public double getDistancia() {
        return distancia;
    }
}