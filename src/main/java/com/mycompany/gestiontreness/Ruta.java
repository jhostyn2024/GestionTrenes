/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

/**
 *
 * @author jhost
 */
public class Ruta {
    private String id;
    private String origen;
    private String destino;
    private int distancia;
    private String horario;

    public Ruta() {} // Constructor vacío requerido

    public Ruta(String id, String origen, String destino, int distancia, String horario) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
        this.horario = horario;
    }

    public String getId() { return id; }
    public String getOrigen() { return origen; }
    public String getDestino() { return destino; }
    public int getDistancia() { return distancia; }
    public String getHorario() { return horario; }

    public void setHorario(String horario) { this.horario = horario; }

    @Override
    public String toString() {
        return origen + " - " + destino + ": " + distancia + " km, Horario: " + horario;
    }
}

