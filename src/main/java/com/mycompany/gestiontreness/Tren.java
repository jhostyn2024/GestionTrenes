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

public class Tren {
    private String id;
    private String marca;
    private String modelo;
    private int capacidad;
    private double velocidadPromedio;
    private List<Vagon> vagones;

    public Tren(String id, String marca, String modelo, int capacidad, double velocidadPromedio, List<Vagon> vagones) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.capacidad = capacidad;
        this.velocidadPromedio = velocidadPromedio;
        this.vagones = vagones != null ? vagones : new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getVelocidadPromedio() {
        return velocidadPromedio;
    }

    public void setVelocidadPromedio(double velocidadPromedio) {
        this.velocidadPromedio = velocidadPromedio;
    }

    public List<Vagon> getVagones() {
        return vagones;
    }

    public void setVagones(List<Vagon> vagones) {
        this.vagones = vagones;
    }
}