/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

/**
 *
 * @author jhost
 */
public class Ruta {
    private String idRuta; // Identificador único
    private String estacionOrigen; // Estación de origen
    private String estacionDestino; // Estación de destino
    private double distancia; // Distancia en kilómetros
    private String estado; // Estado (ej. "Activa", "Inactiva", "Óptima")

    public Ruta(String estacionOrigen, String estacionDestino, double distancia, String estado) {
        this.idRuta = "RUTA-" + System.currentTimeMillis(); // ID único
        this.estacionOrigen = estacionOrigen;
        this.estacionDestino = estacionDestino;
        this.distancia = distancia;
        this.estado = estado;
    }

    // Getters
    public String getIdRuta() { return idRuta; }
    public String getEstacionOrigen() { return estacionOrigen; }
    public String getEstacionDestino() { return estacionDestino; }
    public double getDistancia() { return distancia; }
    public String getEstado() { return estado; }

    // Setters
    public void setEstacionOrigen(String estacionOrigen) { this.estacionOrigen = estacionOrigen; }
    public void setEstacionDestino(String estacionDestino) { this.estacionDestino = estacionDestino; }
    public void setDistancia(double distancia) { this.distancia = distancia; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ruta other = (Ruta) obj;
        return idRuta.equals(other.idRuta);
    }

    @Override
    public int hashCode() {
        return idRuta.hashCode();
    }
}