/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import java.util.ArrayList;
import java.util.List;

public class GestorHorarios {
    private static GestorHorarios instance;
    private List<Horario> horarios;

    private GestorHorarios() {
        horarios = new ArrayList<>();
        System.out.println("Nueva instancia de GestorHorarios creada");
    }

    public static synchronized GestorHorarios getInstance() {
        if (instance == null) {
            instance = new GestorHorarios();
        }
        return instance;
    }

    public void agregarHorario(Horario horario) {
        horarios.add(horario);
        System.out.println("Horario agregado - ID: " + horario.getIdHorario() + ", Total horarios: " + horarios.size());
    }

    public List<Horario> getHorarios() {
        return new ArrayList<>(horarios);
    }

    public boolean eliminarHorario(String idHorario) {
        boolean removed = horarios.removeIf(h -> h.getIdHorario().equals(idHorario));
        if (removed) {
            System.out.println("Horario eliminado - ID: " + idHorario + ", Total horarios: " + horarios.size());
        } else {
            System.out.println("No se encontró horario con ID: " + idHorario);
        }
        return removed;
    }

    public void printHorarios() {
        System.out.println("Lista de horarios:");
        if (horarios.isEmpty()) {
            System.out.println("  (Vacía)");
        } else {
            for (Horario h : horarios) {
                System.out.println("  ID: " + h.getIdHorario() + ", Ruta: " + h.getIdRuta() +
                                   ", Salida: " + h.getHoraSalida() + ", Días: " + h.getDiasSemana());
            }
        }
    }
}