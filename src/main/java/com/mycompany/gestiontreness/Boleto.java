/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import java.time.LocalDateTime;
import java.util.List;

public class Boleto {
    private String idBoleto;
    private String idRuta;
    private String idHorario;
    private String idTren;
    private String nombre;
    private String apellido;
    private String idPasajero;
    private String tipoIdentificacion;
    private String direccion;
    private List<String> telefonos;
    private String lugar;
    private String categoriaPasajero;
    private double precio;
    private LocalDateTime fechaSalida;
    private LocalDateTime fechaLlegada;
    private PersonaContacto contacto;
    private List<Equipaje> equipajes;
    private boolean usado;

    public Boleto(String idBoleto, String idRuta, String idHorario, String idTren, String nombre, String apellido,
                  String idPasajero, String tipoIdentificacion, String direccion, List<String> telefonos,
                  String lugar, String categoriaPasajero, double precio, LocalDateTime fechaSalida,
                  LocalDateTime fechaLlegada, PersonaContacto contacto, List<Equipaje> equipajes) {
        this.idBoleto = idBoleto;
        this.idRuta = idRuta;
        this.idHorario = idHorario;
        this.idTren = idTren;
        this.nombre = nombre;
        this.apellido = apellido;
        this.idPasajero = idPasajero;
        this.tipoIdentificacion = tipoIdentificacion;
        this.direccion = direccion;
        this.telefonos = telefonos;
        this.lugar = lugar;
        this.categoriaPasajero = categoriaPasajero;
        this.precio = precio;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.contacto = contacto;
        this.equipajes = equipajes;
        this.usado = false;
    }

    // Getters
    public String getIdBoleto() { return idBoleto; }
    public String getIdRuta() { return idRuta; }
    public String getIdHorario() { return idHorario; }
    public String getIdTren() { return idTren; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getIdPasajero() { return idPasajero; }
    public String getTipoIdentificacion() { return tipoIdentificacion; }
    public String getDireccion() { return direccion; }
    public List<String> getTelefonos() { return telefonos; }
    public String getLugar() { return lugar; }
    public String getCategoriaPasajero() { return categoriaPasajero; }
    public double getPrecio() { return precio; }
    public LocalDateTime getFechaSalida() { return fechaSalida; }
    public LocalDateTime getFechaLlegada() { return fechaLlegada; }
    public PersonaContacto getContacto() { return contacto; }
    public List<Equipaje> getEquipajes() { return equipajes; }
    public boolean isUsado() { return usado; }

    // Métodos para gestión de uso
    public void marcarBoletoUsado() {
        this.usado = true;
    }
}