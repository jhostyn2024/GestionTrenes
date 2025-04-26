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
    private String idVagon; // Campo para identificación única

    public Vagon(int totalVagones, int vagonesConEquipaje, int totalAsientos, 
                 int asientosEstandar, int asientosEjecutivo, int asientosPremium) {
        this.totalVagones = totalVagones;
        this.vagonesConEquipaje = vagonesConEquipaje;
        this.totalAsientos = totalAsientos;
        this.asientosEstandar = asientosEstandar;
        this.asientosEjecutivo = asientosEjecutivo;
        this.asientosPremium = asientosPremium;
        this.idVagon = "VAG-" + System.currentTimeMillis(); // ID único basado en timestamp
    }

    // Getters
    public int getTotalVagones() { return totalVagones; }
    public int getVagonesConEquipaje() { return vagonesConEquipaje; }
    public int getTotalAsientos() { return totalAsientos; }
    public int getAsientosEstandar() { return asientosEstandar; }
    public int getAsientosEjecutivo() { return asientosEjecutivo; }
    public int getAsientosPremium() { return asientosPremium; }
    public String getIdVagon() { return idVagon; }

    // Setters
    public void setTotalVagones(int totalVagones) { this.totalVagones = totalVagones; }
    public void setVagonesConEquipaje(int vagonesConEquipaje) { this.vagonesConEquipaje = vagonesConEquipaje; }
    public void setTotalAsientos(int totalAsientos) { this.totalAsientos = totalAsientos; }
    public void setAsientosEstandar(int asientosEstandar) { this.asientosEstandar = asientosEstandar; }
    public void setAsientosEjecutivo(int asientosEjecutivo) { this.asientosEjecutivo = asientosEjecutivo; }
    public void setAsientosPremium(int asientosPremium) { this.asientosPremium = asientosPremium; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vagon other = (Vagon) obj;
        return idVagon.equals(other.idVagon);
    }

    @Override
    public int hashCode() {
        return idVagon.hashCode();
    }
}