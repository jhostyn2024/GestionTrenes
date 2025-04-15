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
    private int distancia; // Distancia en kilómetros
    private String horario; // Horario de la ruta como String

    public Ruta(String id, String origen, String destino, int distancia, String horario) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
        this.horario = horario;
    }

    public String getId() {
        return id;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public int getDistancia() {
        return distancia; // Método para obtener la distancia
    }

    public String getHorario() {
        return horario; // Método para obtener el horario
    }

    public void setHorario(String horario) {
        this.horario = horario; // Método para actualizar el horario
    }

    @Override
    public String toString() {
        return origen + " - " + destino + ": " + distancia + " km, Horario: " + horario;
    }
}
