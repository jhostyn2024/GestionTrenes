/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

public class Vagon {
    private String idVagon;
    private String tipo; // "Carga" o "Pasajeros"
    private String idTren; // Tren al que pertenece
    private int capacidadPasajeros; // 40 para vagones de pasajeros
    private int lugaresPremium; // 4
    private int lugaresEjecutiva; // 8
    private int lugaresEstandar; // 22

    public Vagon(String idVagon, String tipo, String idTren) {
        this.idVagon = idVagon;
        this.tipo = tipo;
        this.idTren = idTren;
        if (tipo.equals("Pasajeros")) {
            this.capacidadPasajeros = 40;
            this.lugaresPremium = 4;
            this.lugaresEjecutiva = 8;
            this.lugaresEstandar = 22;
        } else {
            this.capacidadPasajeros = 0;
            this.lugaresPremium = 0;
            this.lugaresEjecutiva = 0;
            this.lugaresEstandar = 0;
        }
    }

    // Getters
    public String getIdVagon() { return idVagon; }
    public String getTipo() { return tipo; }
    public String getIdTren() { return idTren; }
    public int getCapacidadPasajeros() { return capacidadPasajeros; }
    public int getLugaresPremium() { return lugaresPremium; }
    public int getLugaresEjecutiva() { return lugaresEjecutiva; }
    public int getLugaresEstandar() { return lugaresEstandar; }

    Object getTotalVagones() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Object getVagonesConEquipaje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Object getTotalAsientos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Object getAsientosEstandar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Object getAsientosEjecutivo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Object getAsientosPremium() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}