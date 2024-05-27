package com.softulp.inmobiliaria.modelo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
public class Contrato implements Serializable {
    private int id_Contrato;
    private int inmuebleId;
    private int inquilinoId;
    private LocalDateTime fecha_Inicio;

    private LocalDateTime fecha_Fin;
    private double monto;
    private String estado;


    private Inmueble inmueble;

    private Inquilino inquilino;

    public Contrato() {
    }

    public Contrato(int id_Contrato, int inmuebleId, int inquilinoId, LocalDateTime fecha_Inicio, LocalDateTime fecha_Fin, double monto, String estado, Inmueble inmueble, Inquilino inquilino) {
        this.id_Contrato = id_Contrato;
        this.inmuebleId = inmuebleId;
        this.inquilinoId = inquilinoId;
        this.fecha_Inicio = fecha_Inicio;
        this.fecha_Fin = fecha_Fin;
        this.monto = monto;
        this.estado = estado;
        this.inmueble = inmueble;
        this.inquilino = inquilino;
    }

    public int getId_Contrato() {
        return id_Contrato;
    }

    public void setId_Contrato(int id_Contrato) {
        this.id_Contrato = id_Contrato;
    }

    public int getInmuebleId() {
        return inmuebleId;
    }

    public void setInmuebleId(int inmuebleId) {
        this.inmuebleId = inmuebleId;
    }

    public int getInquilinoId() {
        return inquilinoId;
    }

    public void setInquilinoId(int inquilinoId) {
        this.inquilinoId = inquilinoId;
    }

    public LocalDateTime getFecha_Inicio() {
        return fecha_Inicio;
    }

    public void setFecha_Inicio(LocalDateTime fecha_Inicio) {
        this.fecha_Inicio = fecha_Inicio;
    }

    public LocalDateTime getFecha_Fin() {
        return fecha_Fin;
    }

    public void setFecha_Fin(LocalDateTime fecha_Fin) {
        this.fecha_Fin = fecha_Fin;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    @Override
    public String toString() {
        return "Contrato{" +
                "id_Contrato=" + id_Contrato +
                ", inmuebleId=" + inmuebleId +
                ", inquilinoId=" + inquilinoId +
                ", fecha_Inicio=" + fecha_Inicio +
                ", fecha_Fin=" + fecha_Fin +
                ", monto=" + monto +
                ", estado='" + estado + '\'' +
                ", inmueble=" + inmueble +
                ", inquilino=" + inquilino +
                '}';
    }

    public static class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        @Override
        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return LocalDateTime.parse(json.getAsString(), formatter);
        }

    }
};

