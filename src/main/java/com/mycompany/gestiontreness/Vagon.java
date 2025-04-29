/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vagon {
    @JsonProperty("idVagon")
    private String idVagon;

    @JsonProperty("idTren")
    private String idTren;

    @JsonProperty("tipo")
    private String tipo; // "Pasajeros" o "Carga"

    @JsonProperty("capacidadPasajeros")
    private int capacidadPasajeros;

    @JsonProperty("lugaresPremium")
    private int lugaresPremium;

    @JsonProperty("lugaresEjecutiva")
    private int lugaresEjecutiva;

    @JsonProperty("lugaresEstandar")
    private int lugaresEstandar;

    @JsonProperty("capacidadCarga")
    private double capacidadCarga; // En kg, para vagones de carga

    public Vagon() {
    }

    public Vagon(String idVagon, String idTren, String tipo, int capacidadPasajeros, 
                 int lugaresPremium, int lugaresEjecutiva, int lugaresEstandar, double capacidadCarga) {
        this.idVagon = idVagon;
        this.idTren = idTren;
        this.tipo = tipo;
        this.capacidadPasajeros = capacidadPasajeros;
        this.lugaresPremium = lugaresPremium;
        this.lugaresEjecutiva = lugaresEjecutiva;
        this.lugaresEstandar = lugaresEstandar;
        this.capacidadCarga = capacidadCarga;
    }

    Vagon(int parseInt, int parseInt0, int parseInt1, int parseInt2, int parseInt3, int parseInt4) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getIdVagon() { return idVagon; }
    public String getIdTren() { return idTren; }
    public String getTipo() { return tipo; }
    public int getCapacidadPasajeros() { return capacidadPasajeros; }
    public int getLugaresPremium() { return lugaresPremium; }
    public int getLugaresEjecutiva() { return lugaresEjecutiva; }
    public int getLugaresEstandar() { return lugaresEstandar; }
    public double getCapacidadCarga() { return capacidadCarga; }

    public void setIdVagon(String idVagon) { this.idVagon = idVagon; }
    public void setIdTren(String idTren) { this.idTren = idTren; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setCapacidadPasajeros(int capacidadPasajeros) { this.capacidadPasajeros = capacidadPasajeros; }
    public void setLugaresPremium(int lugaresPremium) { this.lugaresPremium = lugaresPremium; }
    public void setLugaresEjecutiva(int lugaresEjecutiva) { this.lugaresEjecutiva = lugaresEjecutiva; }
    public void setLugaresEstandar(int lugaresEstandar) { this.lugaresEstandar = lugaresEstandar; }
    public void setCapacidadCarga(double capacidadCarga) { this.capacidadCarga = capacidadCarga; }

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

    void setTotalVagones(int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setVagonesConEquipaje(int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setTotalAsientos(int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setAsientosEstandar(int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setAsientosEjecutivo(int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setAsientosPremium(int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}