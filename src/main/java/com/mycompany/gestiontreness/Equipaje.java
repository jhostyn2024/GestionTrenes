/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Equipaje {
    @JsonProperty("idEquipaje")
    private String idEquipaje;

    @JsonProperty("peso")
    private double peso;

    @JsonProperty("idVagonCarga")
    private String idVagonCarga;

    // Constructor sin argumentos
    public Equipaje() {
    }

    public Equipaje(String idEquipaje, double peso, String idVagonCarga) {
        this.idEquipaje = idEquipaje;
        this.peso = peso;
        this.idVagonCarga = idVagonCarga;
    }

    // Getters
    public String getIdEquipaje() { return idEquipaje; }
    public double getPeso() { return peso; }
    public String getIdVagonCarga() { return idVagonCarga; }

    // Setters
    public void setIdEquipaje(String idEquipaje) { this.idEquipaje = idEquipaje; }
    public void setPeso(double peso) { this.peso = peso; }
    public void setIdVagonCarga(String idVagonCarga) { this.idVagonCarga = idVagonCarga; }
}