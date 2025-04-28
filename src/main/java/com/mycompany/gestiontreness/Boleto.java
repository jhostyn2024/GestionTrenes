/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import java.time.LocalDateTime;

public class Boleto {
    private String idBoleto;
    private LocalDateTime fechaCompra;
    private String pasajeroNombre;
    private String pasajeroId;
    private String idTren;
    private double precio;

    public Boleto(String idTren, String pasajeroNombre, String pasajeroId, double precio) {
        this.idBoleto = "BOL-" + System.currentTimeMillis();
        this.fechaCompra = LocalDateTime.now();
        this.idTren = idTren;
        this.pasajeroNombre = pasajeroNombre;
        this.pasajeroId = pasajeroId;
        this.precio = precio;
    }

    // Getters
    public String getIdBoleto() { return idBoleto; }
    public String getPasajeroNombre() { return pasajeroNombre; }
    public String getIdTren() { return idTren; }
    public double getPrecio() { return precio; }
}