/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Horario {
    @JsonProperty("idHorario")
    private String idHorario;

    @JsonProperty("idRuta")
    private String idRuta;

    @JsonProperty("idTren")
    private String idTren;

    @JsonProperty("fechaSalida")
    private String fechaSalida;

    @JsonProperty("fechaLlegada")
    private String fechaLlegada;

    @JsonProperty("estado")
    private String estado;

    public Horario() {}

    public Horario(String idHorario, String idRuta, String idTren, String fechaSalida, String fechaLlegada, String estado) {
        this.idHorario = idHorario;
        this.idRuta = idRuta;
        this.idTren = idTren;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.estado = estado;
    }

    Horario(String idRuta, String horaSalida, String horaLlegada, String diasSemana) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getIdHorario() { return idHorario; }
    public String getIdRuta() { return idRuta; }
    public String getIdTren() { return idTren; }
    public String getFechaSalida() { return fechaSalida; }
    public String getFechaLlegada() { return fechaLlegada; }
    public String getEstado() { return estado; }

    public void setIdHorario(String idHorario) { this.idHorario = idHorario; }
    public void setIdRuta(String idRuta) { this.idRuta = idRuta; }
    public void setIdTren(String idTren) { this.idTren = idTren; }
    public void setFechaSalida(String fechaSalida) { this.fechaSalida = fechaSalida; }
    public void setFechaLlegada(String fechaLlegada) { this.fechaLlegada = fechaLlegada; }
    public void setEstado(String estado) { this.estado = estado; }
}