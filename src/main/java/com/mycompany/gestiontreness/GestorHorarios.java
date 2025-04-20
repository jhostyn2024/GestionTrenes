/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

/**
 *
 * @author jhost
 */

import java.util.ArrayList;
import java.util.List;

public class GestorHorarios {
    private static GestorHorarios instance;
    private List<Horario> horarios;

    private GestorHorarios() {
        horarios = new ArrayList<>();
        // Datos de ejemplo (opcional)
        horarios.add(new Horario("RUTA-001", "08:00", "10:30", "Lunes, Miércoles, Viernes"));
    }

    public static synchronized GestorHorarios getInstance() {
        if (instance == null) {
            instance = new GestorHorarios();
        }
        return instance;
    }

    public void agregarHorario(Horario horario) {
        horarios.add(horario);
    }

    public List<Horario> getHorarios() {
        return new ArrayList<>(horarios);
    }
}