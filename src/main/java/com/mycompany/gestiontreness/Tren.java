/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

/**
 *
 * @author jhost
 */
public class Tren {
    private String marca;
    private String identificador;
    private String capacidad;
    private String horaSalidaLlegada;
    private String vagones;
    private String ruta;

    public Tren(String marca, String identificador, String capacidad, String horaSalidaLlegada, String vagones, String ruta) {
        this.marca = marca;
        this.identificador = identificador;
        this.capacidad = capacidad;
        this.horaSalidaLlegada = horaSalidaLlegada;
        this.vagones = vagones;
        this.ruta = ruta;
    }

    // Getters
    public String getMarca() { return marca; }
    public String getIdentificador() { return identificador; }
    public String getCapacidad() { return capacidad; }
    public String getHoraSalidaLlegada() { return horaSalidaLlegada; }
    public String getVagones() { return vagones; }
    public String getRuta() { return ruta; }
}
