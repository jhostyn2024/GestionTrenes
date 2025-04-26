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
    private String origen; // Estación de origen
    private String destino; // Estación de destino

    public Ruta(String origen, String destino) {
        this.idRuta = "RUTA-" + System.currentTimeMillis(); // ID único
        this.origen = origen;
        this.destino = destino;
    }

    // Getters
    public String getIdRuta() { return idRuta; }
    public String getOrigen() { return origen; }
    public String getDestino() { return destino; }

    // Setters (para edición)
    public void setOrigen(String origen) { this.origen = origen; }
    public void setDestino(String destino) { this.destino = destino; }

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