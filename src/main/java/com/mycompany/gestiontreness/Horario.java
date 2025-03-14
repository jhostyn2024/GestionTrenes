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
    private String idRuta;
    private String horaSalida;
    private String horaLlegada;
    private String diaSemana;

    public Horario(String idRuta, String horaSalida, String horaLlegada, String diaSemana) {
        this.idRuta = idRuta;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.diaSemana = diaSemana;
    }

    public String getIdRuta() {
        return idRuta;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public String getDiaSemana() {
        return diaSemana;
    }
}