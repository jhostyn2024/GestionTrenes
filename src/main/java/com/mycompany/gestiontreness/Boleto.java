/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class Boleto {
    @JsonProperty("idBoleto")
    private String idBoleto;

    @JsonProperty("idRuta")
    private String idRuta;

    @JsonProperty("idHorario")
    private String idHorario;

    @JsonProperty("idTren")
    private String idTren;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("apellido")
    private String apellido;

    @JsonProperty("idPasajero")
    private String idPasajero;

    @JsonProperty("tipoIdentificacion")
    private String tipoIdentificacion;

    @JsonProperty("direccion")
    private String direccion;

    @JsonProperty("telefonos")
    private List<String> telefonos;

    @JsonProperty("lugar")
    private String lugar;

    @JsonProperty("categoriaPasajero")
    private String categoriaPasajero;

    @JsonProperty("precio")
    private double precio;

    @JsonProperty("fechaSalida")
    private LocalDateTime fechaSalida;

    @JsonProperty("fechaLlegada")
    private LocalDateTime fechaLlegada;

    @JsonProperty("contacto")
    private PersonaContacto contacto;

    @JsonProperty("equipajes")
    private List<Equipaje> equipajes;

    @JsonProperty("usado")
    private boolean usado;

    // Constructor sin argumentos requerido por Jackson
    public Boleto() {
    }

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

    // Setters
    public void setIdBoleto(String idBoleto) { this.idBoleto = idBoleto; }
    public void setIdRuta(String idRuta) { this.idRuta = idRuta; }
    public void setIdHorario(String idHorario) { this.idHorario = idHorario; }
    public void setIdTren(String idTren) { this.idTren = idTren; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setIdPasajero(String idPasajero) { this.idPasajero = idPasajero; }
    public void setTipoIdentificacion(String tipoIdentificacion) { this.tipoIdentificacion = tipoIdentificacion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setTelefonos(List<String> telefonos) { this.telefonos = telefonos; }
    public void setLugar(String lugar) { this.lugar = lugar; }
    public void setCategoriaPasajero(String categoriaPasajero) { this.categoriaPasajero = categoriaPasajero; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setFechaSalida(LocalDateTime fechaSalida) { this.fechaSalida = fechaSalida; }
    public void setFechaLlegada(LocalDateTime fechaLlegada) { this.fechaLlegada = fechaLlegada; }
    public void setContacto(PersonaContacto contacto) { this.contacto = contacto; }
    public void setEquipajes(List<Equipaje> equipajes) { this.equipajes = equipajes; }
    public void setUsado(boolean usado) { this.usado = usado; }

    // Métodos para gestión de uso
    public void marcarBoletoUsado() {
        this.usado = true;
    }
}