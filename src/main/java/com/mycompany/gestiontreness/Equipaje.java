/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

public class Equipaje {
    private String idEquipaje;
    private double peso;
    private String idVagonCarga;

    public Equipaje(String idEquipaje, double peso, String idVagonCarga) {
        this.idEquipaje = idEquipaje;
        this.peso = peso;
        this.idVagonCarga = idVagonCarga;
    }

    // Getters
    public String getIdEquipaje() { return idEquipaje; }
    public double getPeso() { return peso; }
    public String getIdVagonCarga() { return idVagonCarga; }
}