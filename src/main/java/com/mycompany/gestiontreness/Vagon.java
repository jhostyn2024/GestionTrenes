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
    private int numero;
    private int asientosEstandar;
    private int asientosEjecutivos;
    private int asientosPremium;
    private boolean tieneEspacioEquipaje;

    public Vagon(int numero, int asientosEstandar, int asientosEjecutivos, int asientosPremium, boolean tieneEspacioEquipaje) {
        this.numero = numero;
        this.asientosEstandar = asientosEstandar;
        this.asientosEjecutivos = asientosEjecutivos;
        this.asientosPremium = asientosPremium;
        this.tieneEspacioEquipaje = tieneEspacioEquipaje;
    }

    public int getNumero() {
        return numero;
    }

    public int getAsientosEstandar() {
        return asientosEstandar;
    }

    public int getAsientosEjecutivos() {
        return asientosEjecutivos;
    }

    public int getAsientosPremium() {
        return asientosPremium;
    }

    public boolean isTieneEspacioEquipaje() {
        return tieneEspacioEquipaje;
    }
}
