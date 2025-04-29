/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

public class Horario {
    private String idHorario;
    private String idRuta;
    private String horaSalida; // Formato HH:mm, ej. "08:00"
    private String diasSemana; // Ej. "Lunes a Viernes"

    public Horario(String idHorario, String idRuta, String horaSalida, String diasSemana) {
        this.idHorario = idHorario;
        this.idRuta = idRuta;
        this.horaSalida = horaSalida;
        this.diasSemana = diasSemana;
    }

    // Getters
    public String getIdHorario() { return idHorario; }
    public String getIdRuta() { return idRuta; }
    public String getHoraSalida() { return horaSalida; }
    public String getDiasSemana() { return diasSemana; }

    // Setters
    public void setIdRuta(String idRuta) { this.idRuta = idRuta; }
    public void setHoraSalida(String horaSalida) { this.horaSalida = horaSalida; }
    public void setDiasSemana(String diasSemana) { this.diasSemana = diasSemana; }
}