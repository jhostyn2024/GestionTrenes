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
    private String estacionOrigen;
    private String estacionDestino;
    private String horaSalida;
    private String horaLlegada;
    private String fecha;

    public Horario(String estacionOrigen, String estacionDestino, String horaSalida, 
                   String horaLlegada, String fecha) {
        this.idHorario = "HOR-" + System.currentTimeMillis(); // ID único basado en timestamp
        this.estacionOrigen = estacionOrigen;
        this.estacionDestino = estacionDestino;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.fecha = fecha;
    }

    // Getters
    public String getIdHorario() { return idHorario; }
    public String getEstacionOrigen() { return estacionOrigen; }
    public String getEstacionDestino() { return estacionDestino; }
    public String getHoraSalida() { return horaSalida; }
    public String getHoraLlegada() { return horaLlegada; }
    public String getFecha() { return fecha; }

    // Setters (para edición)
    public void setEstacionOrigen(String estacionOrigen) { this.estacionOrigen = estacionOrigen; }
    public void setEstacionDestino(String estacionDestino) { this.estacionDestino = estacionDestino; }
    public void setHoraSalida(String horaSalida) { this.horaSalida = horaSalida; }
    public void setHoraLlegada(String horaLlegada) { this.horaLlegada = horaLlegada; }
    public void setFecha(String fecha) { this.fecha = fecha; }

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