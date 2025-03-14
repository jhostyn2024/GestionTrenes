/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

/**
 *
 * @author jhost
 */
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaGestion {
    private List<Tren> trenes;
    private List<Ruta> rutas;
    private List<Horario> horarios;
    private List<Empleado> empleados;
    private Map<String, String> usuarios; // usuario -> contraseña
    private Map<String, String> roles; // usuario -> rol

    public SistemaGestion() {
        trenes = new ArrayList<>();
        rutas = new ArrayList<>();
        horarios = new ArrayList<>();
        empleados = new ArrayList<>();
        usuarios = new HashMap<>();
        roles = new HashMap<>();
        inicializarDatos();
    }

    private void inicializarDatos() {
        // Usuarios predefinidos para prueba
        usuarios.put("admin@tren.com", "admin123");
        roles.put("admin@tren.com", "empleado");
        usuarios.put("user@tren.com", "user123");
        roles.put("user@tren.com", "pasajero");

        // Ejemplo de rutas
        rutas.add(new Ruta("R1", "Estacion A", "Estacion B", 50.0));
        rutas.add(new Ruta("R2", "Estacion B", "Estacion C", 30.0));
        rutas.add(new Ruta("R3", "Estacion A", "Estacion C", 90.0));
    }

    // Autenticación (RF15)
    public String autenticar(String usuario, String contrasena) {
        if (usuarios.containsKey(usuario) && usuarios.get(usuario).equals(contrasena)) {
            return roles.get(usuario);
        }
        return null;
    }

    // Gestión de Trenes (RF1-RF3)
    public void agregarTren(Tren tren) {
        trenes.add(tren);
        registrarAccion("Tren agregado: " + tren.getId());
    }

    public void modificarTren(int indice, Tren tren) {
        trenes.set(indice, tren);
        registrarAccion("Tren modificado: " + tren.getId());
    }

    public void eliminarTren(int indice) {
        Tren tren = trenes.remove(indice);
        registrarAccion("Tren eliminado: " + tren.getId());
    }

    public List<Tren> getTrenes() {
        return trenes;
    }

    // Gestión de Rutas (RF4)
    public void agregarRuta(Ruta ruta) {
        rutas.add(ruta);
        registrarAccion("Ruta agregada: " + ruta.getId());
    }

    public void modificarRuta(int indice, Ruta ruta) {
        rutas.set(indice, ruta);
        registrarAccion("Ruta modificada: " + ruta.getId());
    }

    public void eliminarRuta(int indice) {
        Ruta ruta = rutas.remove(indice);
        registrarAccion("Ruta eliminada: " + ruta.getId());
    }

    public List<Ruta> getRutas() {
        return rutas;
    }

    // Gestión de Horarios (RF5)
    public void agregarHorario(Horario horario) {
        horarios.add(horario);
        registrarAccion("Horario agregado para ruta: " + horario.getIdRuta());
    }

    public void modificarHorario(int indice, Horario horario) {
        horarios.set(indice, horario);
        registrarAccion("Horario modificado para ruta: " + horario.getIdRuta());
    }

    public void eliminarHorario(int indice) {
        Horario horario = horarios.remove(indice);
        registrarAccion("Horario eliminado para ruta: " + horario.getIdRuta());
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    // Trazabilidad (RF16)
    private void registrarAccion(String accion) {
        try (FileWriter writer = new FileWriter("log.txt", true)) {
            writer.write(LocalDateTime.now() + " - " + accion + "\n");
        } catch (IOException e) {
            System.out.println("Error al registrar acción: " + e.getMessage());
        }
    }

    // Método para Dijkstra (RF6) - Implementar en Semana 8
    public Map<String, Object> calcularRutaMasCorta(String origen, String destino) {
        // Placeholder: Implementar Dijkstra aquí
        Map<String, Object> resultado = new HashMap<>();
        resultado.put("distancia", 0.0);
        resultado.put("tiempo", "0 minutos");
        return resultado;
    }
}