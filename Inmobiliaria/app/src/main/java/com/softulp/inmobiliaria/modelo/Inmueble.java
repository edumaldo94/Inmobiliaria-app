package com.softulp.inmobiliaria.modelo;

import java.io.Serializable;

public class Inmueble implements Serializable {

    private int id_Inmuebles;
    private int propietarioId;
    private String latitud;
    private String longitud;
    private String ubicacion;
    private String direccion;
    private int ambientes;
    private String uso;
    private String tipo;
    private double precio;
    private boolean disponible;
    private int estadoIn;
    private String foto;
    private Propietario propietario;

    public Inmueble() {
    }

    public int getEstadoIn() {
        return estadoIn;
    }

    public void setEstadoIn(int estadoIn) {
        this.estadoIn = estadoIn;
    }

    public Inmueble(int propietarioId, String latitud, String longitud, String ubicacion, String direccion, int ambientes, String uso, String tipo, double precio, boolean disponible,int estadoIn , String foto) {
        this.propietarioId = propietarioId;
        this.latitud = latitud;
        this.longitud = longitud;
        this.ubicacion = ubicacion;
        this.direccion = direccion;
        this.ambientes = ambientes;
        this.uso = uso;
        this.tipo = tipo;
        this.precio = precio;
        this.disponible = disponible;
        this.estadoIn = estadoIn;
        this.foto = foto;
        //this.propietario = propietario;
    }

    public Inmueble(int id_Inmuebles, int propietarioId, String latitud, String longitud, String ubicacion, String direccion, int ambientes, String uso, String tipo, double precio, boolean disponible, String foto) {
        this.id_Inmuebles = id_Inmuebles;
        this.propietarioId = propietarioId;
        this.latitud = latitud;
        this.longitud = longitud;
        this.ubicacion = ubicacion;
        this.direccion = direccion;
        this.ambientes = ambientes;
        this.uso = uso;
        this.tipo = tipo;
        this.precio = precio;
        this.disponible = disponible;
        this.foto = foto;
       // this.propietario = propietario;
    }

    public int getId_Inmuebles() {
        return id_Inmuebles;
    }

    public void setId_Inmuebles(int id_Inmuebles) {
        this.id_Inmuebles = id_Inmuebles;
    }

    public int getPropietarioId() {
        return propietarioId;
    }

    public void setPropietarioId(int propietarioId) {
        this.propietarioId = propietarioId;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(int ambientes) {
        this.ambientes = ambientes;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        return "Inmueble{" +
                "id_Inmuebles=" + id_Inmuebles +
                ", propietarioId=" + propietarioId +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ambientes=" + ambientes +
                ", uso='" + uso + '\'' +
                ", tipo='" + tipo + '\'' +
                ", precio=" + precio +
                ", disponible=" + disponible +
                ", foto='" + foto + '\'' +
                ", propietario=" + propietario +
                '}';
    }
}