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
    private int capacidad;
    private String horaSalida;
    private String horaLlegada;
    private int vagones;
    private String ruta;

    public Tren(String marca, String identificador, int capacidad, 
                String horaSalida, String horaLlegada, int vagones, String ruta) {
        this.marca = marca;
        this.identificador = identificador;
        this.capacidad = capacidad;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.vagones = vagones;
        this.ruta = ruta;
    }

    // Getters
    public String getMarca() { return marca; }
    public String getIdentificador() { return identificador; }
    public int getCapacidad() { return capacidad; }
    public String getHoraSalida() { return horaSalida; }
    public String getHoraLlegada() { return horaLlegada; }
    public int getVagones() { return vagones; }
    public String getRuta() { return ruta; }

    // Setters
    public void setMarca(String marca) { this.marca = marca; }
    public void setIdentificador(String identificador) { this.identificador = identificador; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
    public void setHoraSalida(String horaSalida) { this.horaSalida = horaSalida; }
    public void setHoraLlegada(String horaLlegada) { this.horaLlegada = horaLlegada; }
    public void setVagones(int vagones) { this.vagones = vagones; }
    public void setRuta(String ruta) { this.ruta = ruta; }

    @Override
    public String toString() {
        return marca + " - " + identificador;
    }
}