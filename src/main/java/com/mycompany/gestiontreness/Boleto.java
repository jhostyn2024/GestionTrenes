/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import java.util.Objects;

/**
 *
 * @author jhost
 */


public class Boleto {
    private String idRegistro;
    private String fechaCompra;
    private String fechaSalida;
    private String idTren;
    private String idPasajero;

    // Constructor
    public Boleto(String idRegistro, String fechaCompra, String fechaSalida, String idTren, String idPasajero) {
        this.idRegistro = idRegistro;
        this.fechaCompra = fechaCompra;
        this.fechaSalida = fechaSalida;
        this.idTren = idTren;
        this.idPasajero = idPasajero;
    }

    // Getters y setters
    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getIdTren() {
        return idTren;
    }

    public void setIdTren(String idTren) {
        this.idTren = idTren;
    }

    public String getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(String idPasajero) {
        this.idPasajero = idPasajero;
    }

    @Override
    public String toString() {
        return "Boleto{" +
                "idRegistro='" + idRegistro + '\'' +
                ", fechaCompra='" + fechaCompra + '\'' +
                ", fechaSalida='" + fechaSalida + '\'' +
                ", idTren='" + idTren + '\'' +
                ", idPasajero='" + idPasajero + '\'' +
                '}';
    }
}
