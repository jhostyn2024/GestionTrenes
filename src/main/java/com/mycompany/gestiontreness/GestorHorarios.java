/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorHorarios {
    private static GestorHorarios instance;
    private List<Horario> horarios;

    private GestorHorarios() {
        horarios = new ArrayList<>();
        inicializarHorarios();
    }

    public static synchronized GestorHorarios getInstance() {
        if (instance == null) {
            instance = new GestorHorarios();
        }
        return instance;
    }

    private void inicializarHorarios() {
        // Horarios para cada ruta (dos horarios por ruta: mañana y tarde, Lunes a Viernes)
        horarios.add(new Horario("HORARIO-1", "RUTA-1", "08:00", "Lunes a Viernes"));
        horarios.add(new Horario("HORARIO-2", "RUTA-1", "16:00", "Lunes a Viernes"));
        horarios.add(new Horario("HORARIO-3", "RUTA-2", "07:30", "Lunes a Viernes"));
        horarios.add(new Horario("HORARIO-4", "RUTA-2", "15:30", "Lunes a Viernes"));
        horarios.add(new Horario("HORARIO-5", "RUTA-3", "08:30", "Lunes a Viernes"));
        horarios.add(new Horario("HORARIO-6", "RUTA-3", "16:30", "Lunes a Viernes"));
        horarios.add(new Horario("HORARIO-7", "RUTA-4", "07:00", "Lunes a Viernes"));
        horarios.add(new Horario("HORARIO-8", "RUTA-4", "15:00", "Lunes a Viernes"));
        horarios.add(new Horario("HORARIO-9", "RUTA-5", "09:00", "Lunes a Viernes"));
        horarios.add(new Horario("HORARIO-10", "RUTA-5", "17:00", "Lunes a Viernes"));
        horarios.add(new Horario("HORARIO-11", "RUTA-6", "06:30", "Lunes a Viernes"));
        horarios.add(new Horario("HORARIO-12", "RUTA-6", "14:30", "Lunes a Viernes"));
        horarios.add(new Horario("HORARIO-13", "RUTA-7", "07:45", "Lunes a Viernes"));
        horarios.add(new Horario("HORARIO-14", "RUTA-7", "15:45", "Lunes a Viernes"));
        horarios.add(new Horario("HORARIO-15", "RUTA-8", "08:15", "Lunes a Viernes"));
        horarios.add(new Horario("HORARIO-16", "RUTA-8", "16:15", "Lunes a Viernes"));
        horarios.add(new Horario("HORARIO-17", "RUTA-9", "07:15", "Lunes a Viernes"));
        horarios.add(new Horario("HORARIO-18", "RUTA-9", "15:15", "Lunes a Viernes"));
        horarios.add(new Horario("HORARIO-19", "RUTA-10", "09:30", "Lunes a Viernes"));
        horarios.add(new Horario("HORARIO-20", "RUTA-10", "17:30", "Lunes a Viernes"));
        horarios.add(new Horario("HORARIO-21", "RUTA-11", "08:45", "Lunes a Viernes"));
        horarios.add(new Horario("HORARIO-22", "RUTA-11", "16:45", "Lunes a Viernes"));
        horarios.add(new Horario("HORARIO-23", "RUTA-12", "06:45", "Lunes a Viernes"));
        horarios.add(new Horario("HORARIO-24", "RUTA-12", "14:45", "Lunes a Viernes"));
    }

    public void agregarHorario(Horario horario) {
        horarios.add(horario);
    }

    public boolean eliminarHorario(String idHorario) {
        return horarios.removeIf(h -> h.getIdHorario().equals(idHorario));
    }

    public boolean modificarHorario(String idHorario, String idRuta, String horaSalida, String diasSemana) {
        for (Horario horario : horarios) {
            if (horario.getIdHorario().equals(idHorario)) {
                horario.setIdRuta(idRuta);
                horario.setHoraSalida(horaSalida);
                horario.setDiasSemana(diasSemana);
                return true;
            }
        }
        return false;
    }

    public List<Horario> getHorarios() {
        return new ArrayList<>(horarios);
    }

    public List<Horario> getHorariosPorRuta(String idRuta) {
        return horarios.stream()
                .filter(h -> h.getIdRuta().equals(idRuta))
                .collect(Collectors.toList());
    }
}