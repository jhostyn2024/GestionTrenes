/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

/**
 *
 * @author jhost
 */


public class Pasajero {
    private String identificacion;
    private String nombres;
    private String apellidos;
    private String tipoBoleto; // Premium, ejecutivo, estándar
    private String idTren;
    private String lugarAsignado;

    // Constructor
    public Pasajero(String identificacion, String nombres, String apellidos, String tipoBoleto, String idTren, String lugarAsignado) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoBoleto = tipoBoleto;
        this.idTren = idTren;
        this.lugarAsignado = lugarAsignado;
    }

    // Getters y setters
    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipoBoleto() {
        return tipoBoleto;
    }

    public void setTipoBoleto(String tipoBoleto) {
        this.tipoBoleto = tipoBoleto;
    }

    public String getIdTren() {
        return idTren;
    }

    public void setIdTren(String idTren) {
        this.idTren = idTren;
    }

    public String getLugarAsignado() {
        return lugarAsignado;
    }

    public void setLugarAsignado(String lugarAsignado) {
        this.lugarAsignado = lugarAsignado;
    }

    @Override
    public String toString() {
        return "Pasajero{" +
                "identificacion='" + identificacion + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", tipoBoleto='" + tipoBoleto + '\'' +
                ", idTren='" + idTren + '\'' +
                ", lugarAsignado='" + lugarAsignado + '\'' +
                '}';
    }
}