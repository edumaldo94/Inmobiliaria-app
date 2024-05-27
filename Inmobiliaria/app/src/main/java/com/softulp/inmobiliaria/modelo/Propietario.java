package com.softulp.inmobiliaria.modelo;

import java.io.Serializable;

public class Propietario implements Serializable {


    private int id_Propietario;
    private String nombre;
    private String apellido;
    private String dni;

    private String email;
    private String clave;
    private String telefono;
    private int estadoP;
    private String avatar;

    public Propietario() {
    }

    public Propietario(int id_Propietario, String nombre, String apellido, String dni, String email, String telefono) {
        this.id_Propietario = id_Propietario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;

    }

    public Propietario(int id_Propietario, String nombre, String apellido, String dni, String email, String clave, String telefono) {
        this.id_Propietario = id_Propietario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.clave = clave;
        this.telefono = telefono;

    }

    public int getId_Propietario() {
        return id_Propietario;
    }

    public void setId_Propietario(int id_Propietario) {
        this.id_Propietario = id_Propietario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        clave = clave;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        telefono = telefono;
    }

    public int getEstadoP() {
        return estadoP;
    }

    public void setEstadoP(int estadoP) {
        estadoP = estadoP;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        avatar = avatar;
    }

    @Override
    public String toString() {
        return apellido + ' ' + nombre;
    }
}
