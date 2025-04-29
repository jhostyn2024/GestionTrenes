/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Horario {
    @JsonProperty("idHorario")
    private String idHorario;

    @JsonProperty("idRuta")
    private String idRuta;

    @JsonProperty("horaSalida")
    private String horaSalida; // Formato HH:mm

    @JsonProperty("diasSemana")
    private String diasSemana; // Ejemplo: "Lunes,Martes,Miércoles"

    @JsonProperty("fechaInicio")
    private LocalDateTime fechaInicio;

    @JsonProperty("fechaFin")
    private LocalDateTime fechaFin;

    public Horario() {
    }

    public Horario(String idHorario, String idRuta, String horaSalida, String diasSemana, 
                   LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.idHorario = idHorario;
        this.idRuta = idRuta;
        this.horaSalida = horaSalida;
        this.diasSemana = diasSemana;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    Horario(String idRuta, String horaSalida, String horaLlegada, String diasSemana) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getIdHorario() { return idHorario; }
    public String getIdRuta() { return idRuta; }
    public String getHoraSalida() { return horaSalida; }
    public String getDiasSemana() { return diasSemana; }
    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public LocalDateTime getFechaFin() { return fechaFin; }

    public void setIdHorario(String idHorario) { this.idHorario = idHorario; }
    public void setIdRuta(String idRuta) { this.idRuta = idRuta; }
    public void setHoraSalida(String horaSalida) { this.horaSalida = horaSalida; }
    public void setDiasSemana(String diasSemana) { this.diasSemana = diasSemana; }
    public void setFechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }
    public void setFechaFin(LocalDateTime fechaFin) { this.fechaFin = fechaFin; }
}