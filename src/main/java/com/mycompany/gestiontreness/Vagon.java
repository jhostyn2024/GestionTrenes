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
    private int numeroVagon;
    private int capacidad; // Capacidad total de pasajeros (40 por defecto)
    private int asientosPremium; // 4 asientos premium por vagón
    private int asientosEjecutivo; // 8 asientos ejecutivo por vagón
    private int asientosEstandar; // 22 asientos estándar por vagón

    
    public Vagon(int numeroVagon) {
        this.numeroVagon = numeroVagon;
        this.capacidad = 40; // Capacidad fija según el documento
        this.asientosPremium = 4;
        this.asientosEjecutivo = 8;
        this.asientosEstandar = 22;
    }

    
    public int getNumeroVagon() {
        return numeroVagon;
    }

    public void setNumeroVagon(int numeroVagon) {
        this.numeroVagon = numeroVagon;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getAsientosPremium() {
        return asientosPremium;
    }

    public void setAsientosPremium(int asientosPremium) {
        this.asientosPremium = asientosPremium;
    }

    public int getAsientosEjecutivo() {
        return asientosEjecutivo;
    }

    public void setAsientosEjecutivo(int asientosEjecutivo) {
        this.asientosEjecutivo = asientosEjecutivo;
    }

    public int getAsientosEstandar() {
        return asientosEstandar;
    }

    public void setAsientosEstandar(int asientosEstandar) {
        this.asientosEstandar = asientosEstandar;
    }

    @Override
    public String toString() {
        return "Vagon{" +
                "numeroVagon=" + numeroVagon +
                ", capacidad=" + capacidad +
                ", asientosPremium=" + asientosPremium +
                ", asientosEjecutivo=" + asientosEjecutivo +
                ", asientosEstandar=" + asientosEstandar +
                '}';
    }
}