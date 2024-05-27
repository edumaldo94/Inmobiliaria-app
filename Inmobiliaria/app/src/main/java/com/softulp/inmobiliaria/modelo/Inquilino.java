package com.softulp.inmobiliaria.modelo;

import java.io.Serializable;

public class Inquilino implements Serializable {
    private int id_Inquilino;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String telefono;
 //   private String estado;


    public Inquilino() {
    }

    public Inquilino(int id_Inquilino, String nombre, String apellido, String dni, String email, String telefono) {
        this.id_Inquilino = id_Inquilino;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
       // this.estado = estado;
    }

    public int getId_Inquilino() {
        return id_Inquilino;
    }

    public void setId_Inquilino(int id_Inquilino) {
        this.id_Inquilino = id_Inquilino;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    //public String getEstado() {
    //    return estado;
  //  }

   // public void setEstado(String estado) {
 //       this.estado = estado;
  //  }

    @Override
    public String toString() {
        return "Inquilino{" +
                "id_Inquilino=" + id_Inquilino +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
       //         ", estado='" + estado + '\'' +
                '}';
    }
}
