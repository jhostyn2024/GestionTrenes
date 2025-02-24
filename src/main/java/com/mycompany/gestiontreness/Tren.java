/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

/**
 *
 * @author jhost
 */


import java.util.List;

public class Tren {
    private String nombre;
    private String identificador;
    private String marca; // Mercedes-Benz o Arnold
    private int capacidadCarga; // Número de vagones (1-28 para Mercedes-Benz, 1-32 para Arnold)
    private double kilometraje;
    private List<Vagon> vagones;

    // Constructor
    public Tren(String nombre, String identificador, String marca, int capacidadCarga, double kilometraje, List<Vagon> vagones) {
        this.nombre = nombre;
        this.identificador = identificador;
        this.marca = marca;
        // Validar la capacidad de carga según la marca
        if (marca.equalsIgnoreCase("Mercedes-Benz")) {
            if (capacidadCarga < 1 || capacidadCarga > 28) {
                throw new IllegalArgumentException("La capacidad de carga para un tren Mercedes-Benz debe estar entre 1 y 28 vagones.");
            }
        } else if (marca.equalsIgnoreCase("Arnold")) {
            if (capacidadCarga < 1 || capacidadCarga > 32) {
                throw new IllegalArgumentException("La capacidad de carga para un tren Arnold debe estar entre 1 y 32 vagones.");
            }
        } else {
            throw new IllegalArgumentException("Marca de tren no válida. Debe ser 'Mercedes-Benz' o 'Arnold'.");
        }
        this.capacidadCarga = capacidadCarga;
        this.kilometraje = kilometraje;
        this.vagones = vagones;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(int capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public double getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(double kilometraje) {
        this.kilometraje = kilometraje;
    }

    public List<Vagon> getVagones() {
        return vagones;
    }

    public void setVagones(List<Vagon> vagones) {
        this.vagones = vagones;
    }

    @Override
    public String toString() {
        return "Tren{" +
                "nombre='" + nombre + '\'' +
                ", identificador='" + identificador + '\'' +
                ", marca='" + marca + '\'' +
                ", capacidadCarga=" + capacidadCarga +
                ", kilometraje=" + kilometraje +
                ", vagones=" + vagones +
                '}';
    }
}