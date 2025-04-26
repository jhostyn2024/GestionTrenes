/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

/**
 *
 * @author jhost
 */
public class Horario {
    private String idHorario; // Campo para identificación única
    private String idRuta; // Identificador de la ruta
    private String horaSalida; // Hora de salida
    private String horaLlegada; // Hora de llegada
    private String diasSemana; // Días de la semana (ej. "Lunes,Miércoles,Viernes")

    public Horario(String idRuta, String horaSalida, String horaLlegada, String diasSemana) {
        this.idHorario = "HOR-" + System.currentTimeMillis(); // ID único basado en timestamp
        this.idRuta = idRuta;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.diasSemana = diasSemana;
    }

    // Getters
    public String getIdHorario() { return idHorario; }
    public String getIdRuta() { return idRuta; }
    public String getHoraSalida() { return horaSalida; }
    public String getHoraLlegada() { return horaLlegada; }
    public String getDiasSemana() { return diasSemana; }

    // Setters (para edición)
    public void setIdRuta(String idRuta) { this.idRuta = idRuta; }
    public void setHoraSalida(String horaSalida) { this.horaSalida = horaSalida; }
    public void setHoraLlegada(String horaLlegada) { this.horaLlegada = horaLlegada; }
    public void setDiasSemana(String diasSemana) { this.diasSemana = diasSemana; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Horario other = (Horario) obj;
        return idHorario.equals(other.idHorario);
    }

    @Override
    public int hashCode() {
        return idHorario.hashCode();
    }
}