/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

/**
 *
 * @author jhost
 */

public class Vagon {
    private int totalVagones;
    private int vagonesConEquipaje;
    private int totalAsientos;
    private int asientosEstandar;
    private int asientosEjecutivo;
    private int asientosPremium;

    public Vagon(int totalVagones, int vagonesConEquipaje, int totalAsientos, 
                int asientosEstandar, int asientosEjecutivo, int asientosPremium) {
        this.totalVagones = totalVagones;
        this.vagonesConEquipaje = vagonesConEquipaje;
        this.totalAsientos = totalAsientos;
        this.asientosEstandar = asientosEstandar;
        this.asientosEjecutivo = asientosEjecutivo;
        this.asientosPremium = asientosPremium;
    }

    // Getters
    public int getTotalVagones() { return totalVagones; }
    public int getVagonesConEquipaje() { return vagonesConEquipaje; }
    public int getTotalAsientos() { return totalAsientos; }
    public int getAsientosEstandar() { return asientosEstandar; }
    public int getAsientosEjecutivo() { return asientosEjecutivo; }
    public int getAsientosPremium() { return asientosPremium; }
}